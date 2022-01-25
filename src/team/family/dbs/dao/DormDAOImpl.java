package team.family.dbs.dao;

import team.family.dbs.bean.Dorm;
import team.family.dbs.bean.PageBean;
import team.family.dbs.util.DataBaseUtils;
import team.family.dbs.util.StringUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DormDAOImpl implements DormDAO{
    @Override
    public List<Dorm> queryAllDorm() throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String selectSql = "select * from t_dorm;";
        PreparedStatement pre = conn.prepareStatement(selectSql);
        List<Dorm> dorms = new ArrayList<>();
        ResultSet res = pre.executeQuery();
        while(res.next()){
            Dorm dorm = new Dorm();
            dorm.setDorm_id(res.getInt("dorm_id"));
            dorm.setDorm_name(res.getString("dorm_name"));
            dorm.setRemark(res.getString("remark"));
            dorm.setIs_effective(res.getInt("is_effective"));
            if(dorm.getIs_effective() == 0){
                dorms.add(dorm);
            }
        }

        return dorms;
    }

    @Override
    public List<Dorm> queryAllDormCondition(PageBean page, Dorm dorm) throws Exception {//因为只有通过宿舍楼名字来进行搜索，所以只要考虑宿舍楼名字搜索的情况
        List<Dorm> dormBuildList = new ArrayList<Dorm>();
        StringBuffer sb = new StringBuffer("select * from t_dorm t1");
        if(StringUtil.isNotEmpty(dorm.getDorm_name())) {
            sb.append(" where t1.dorm_name like '%"+dorm.getDorm_name()+"%'");
        }
        if(page != null) {
            sb.append(" limit "+page.getStart()+","+page.getPageSize());
        }
        Connection conn = DataBaseUtils.getConn();
        PreparedStatement pre = conn.prepareStatement(sb.toString());
        ResultSet res = pre.executeQuery();
        while(res.next()) {
            Dorm dormNew = new Dorm();
            dormNew.setDorm_id(res.getInt("dorm_id"));
            dormNew.setDorm_name(res.getString("dorm_name"));
            dormNew.setRemark(res.getString("remark"));
            dormNew.setIs_effective(res.getInt("is_effective"));
            if(dormNew.getIs_effective() == 0){
                dormBuildList.add(dormNew);
            }
        }
        DataBaseUtils.closeConn(conn);
        return dormBuildList;
    }
    @Test
    public void testQuerryAllDorm() throws Exception {
        Dorm dorm = new Dorm();
        PageBean page = new PageBean(1,100);
        System.out.println(queryAllDormCondition(page, dorm));
    }

    @Override
    public int updateDormByDorm_id(Dorm dorm) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String updateSql = "update t_dorm set dorm_name = ?,remark = ? where dorm_id = ?;";
        PreparedStatement pre = conn.prepareStatement(updateSql);
        pre.setString(1,dorm.getDorm_name());
        pre.setString(2,dorm.getRemark());
        pre.setInt(3,dorm.getDorm_id());
        int i = pre.executeUpdate();
        DataBaseUtils.closeConn(conn);
        return i;
    }

    @Override
    public int delDormByDorm_id(int dorm_id) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String selectSql = "update t_dorm set is_effective = 1 where dorm_id = ?;";
        PreparedStatement pre = conn.prepareStatement(selectSql);
        pre.setInt(1,dorm_id);
        int i = pre.executeUpdate();
        return i;
    }

    @Override
    public String selectDorm_nameByDorm_id(int dorm_id) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String selectSql = "select dorm_name from t_dorm where dorm_id = ?;";
        PreparedStatement pre = conn.prepareStatement(selectSql);
        pre.setInt(1,dorm_id);
        ResultSet res = pre.executeQuery();
        if(res.next()) {
            DataBaseUtils.closeConn(conn);
            return res.getString("dorm_name");
        }
        return null;
    }

    @Override
    public int selectDorm_idByDorm_Name(String dorm_name) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String selectSql = "select dorm_id from t_dorm where  dorm_name= ?;";
        PreparedStatement pre = conn.prepareStatement(selectSql);
        pre.setString(1,dorm_name);
        ResultSet res = pre.executeQuery();
        if(res.next()) {
            DataBaseUtils.closeConn(conn);
            return res.getInt("dorm_id");
        }
        return -1;
    }

    @Override
    public boolean isHaveRoomInDorm(int dorm_id) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String selectSql = "select * from t_dorm t1 inner join t_room t2  on t1.dorm_id =t2.belong_dormId  where  dorm_id = ?;";
        PreparedStatement pre = conn.prepareStatement(selectSql);
        pre.setInt(1,dorm_id);
        ResultSet res = pre.executeQuery();
        if(res.next()) {
            Dorm dorm = new Dorm();
            dorm.setDorm_id(res.getInt("dorm_id"));
            dorm.setDorm_name(res.getString("dorm_name"));
            dorm.setRemark(res.getString("remark"));
            dorm.setIs_effective(res.getInt("is_effective"));
            DataBaseUtils.closeConn(conn);
            if(dorm.getIs_effective() == 0){
                System.out.println("该宿舍楼对象："+ dorm);
                return true;
            }else{
                return false;
            }
        }
        return false;

    }

    @Override
    public int addDorm(Dorm dorm) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        if(isHaveRoomInDorm(dorm.getDorm_id())){
            //说明该数据库中已经存在该对象。
            return 0;
        }
        String insertSql = "insert into t_dorm values(?,?,?,?);";
        PreparedStatement pre = conn.prepareStatement(insertSql);
        pre.setInt(1,dorm.getDorm_id());
        pre.setString(2,dorm.getDorm_name());
        pre.setString(3,dorm.getRemark());
        pre.setInt(4,dorm.getIs_effective());
        int i = pre.executeUpdate();
        DataBaseUtils.closeConn(conn);
        return i;
    }

    @Override
    public Dorm queryDorm(int dorm_id) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String selectSql = "select * from t_dorm where dorm_id = ?;";
        PreparedStatement pre = conn.prepareStatement(selectSql);
        pre.setInt(1,dorm_id);
        ResultSet res = pre.executeQuery();
        if(res.next()){
            Dorm dorm = new Dorm();
            dorm.setDorm_id(res.getInt("dorm_id"));
            dorm.setDorm_name(res.getString("dorm_name"));
            dorm.setRemark(res.getString("remark"));
            dorm.setIs_effective(res.getInt("is_effective"));
            if(dorm.getIs_effective() == 0){
                return dorm;
            }else{
                return null;
            }
        }
        return null;
    }


    @Test
    public void testSelectDorm_nameByDorm_id() throws Exception {
        System.out.println(selectDorm_nameByDorm_id(6));
    }
}
