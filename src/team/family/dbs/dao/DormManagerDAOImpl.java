package team.family.dbs.dao;

import team.family.dbs.bean.AbsenceRecord;
import team.family.dbs.bean.DormManager;
import team.family.dbs.util.DataBaseUtils;
import team.family.dbs.util.StringUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

//宿舍管理数据存储类。
public class DormManagerDAOImpl implements DormManagerDAO{
    @Override
    public DormManager isExists(String userName, String password) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String selectSql = "select * from t_dormmaster t1 inner join t_dorm on t1.dorm_id = t_dorm.dorm_id where dormmasterId = ? and password = ?;";
        PreparedStatement pre = conn.prepareStatement(selectSql);
        pre.setString(1,userName);
        pre.setString(2,password);
        ResultSet res = pre.executeQuery();
        if(res.next()){
            DormManager dormManager = new DormManager();
            dormManager.setDormmasterId(res.getString("dormmasterId"));
            dormManager.setUserName(res.getString("userName"));
            dormManager.setPassword(res.getString("password"));
            dormManager.setSex(res.getString("sex"));
            dormManager.setTel(res.getString("tel"));
            dormManager.setDorm_id(res.getInt("dorm_id"));
            dormManager.setDorm_name(res.getString("dorm_name"));
            dormManager.setIs_effective(res.getInt("is_effective"));
            DataBaseUtils.closeConn(conn);
            if(dormManager.getIs_effective()!=1){

                return dormManager;
            }
        }

        return null;
    }
    @Test
    public void testEx() throws Exception {
        System.out.println(isExists("000001", "123456"));
    }

    @Override
    public List<DormManager> queryAllDormManager() throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String selectSql = "select * from t_dormmaster inner join t_dorm on t_dormmaster.dorm_id = t_dorm.dorm_id;";
        PreparedStatement pre = conn.prepareStatement(selectSql);
        ResultSet res = pre.executeQuery();
        List<DormManager> dormManagers = new ArrayList<>();
        while(res.next()){
            DormManager dormManager = new DormManager();
            dormManager.setDormmasterId(res.getString("dormmasterId"));
            dormManager.setUserName(res.getString("userName"));
            dormManager.setPassword(res.getString("password"));
            dormManager.setSex(res.getString("sex"));
            dormManager.setTel(res.getString("tel"));
            dormManager.setDorm_id(res.getInt("dorm_id"));
            dormManager.setDorm_name(res.getString("dorm_name"));
            dormManager.setIs_effective(res.getInt("is_effective"));
            if(dormManager.getIs_effective()!=1){
                dormManagers.add(dormManager);
            }
        }
        DataBaseUtils.closeConn(conn);
        return dormManagers;
    }

    @Override
    public List<DormManager> queryAllDormManagerByCondition(DormManager dormManager) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        StringBuffer selectSql = new StringBuffer("select * from t_dormmaster t1 inner join t_dorm t2 on t1.dorm_id = t2_id");
        if(StringUtil.isNotEmpty(dormManager.getUserName())){
            selectSql.append("where t1.userName like '%" + dormManager.getUserName() + "%';");
        }else{
            selectSql.append("where t1.dormmasterId like '%" + dormManager.getDormmasterId() + "%';");
        }
        PreparedStatement pre = conn.prepareStatement(selectSql.toString());
        ResultSet res = pre.executeQuery();
        List<DormManager> dormManagers = new ArrayList<DormManager>();
        while(res.next()){
            dormManager = new DormManager();
            dormManager.setDormmasterId(res.getString("dormmasterId"));
            dormManager.setUserName(res.getString("userName"));
            dormManager.setPassword(res.getString("password"));
            dormManager.setSex(res.getString("sex"));
            dormManager.setTel(res.getString("tel"));
            dormManager.setDorm_id(res.getInt("dorm_id"));
            dormManager.setDorm_name(res.getString("dorm_name"));
            dormManager.setIs_effective(res.getInt("is_effective"));
            if(dormManager.getIs_effective() != 1){
                dormManagers.add(dormManager);
            }
        }
        DataBaseUtils.closeConn(conn);
        return dormManagers;
    }

    @Test
    public void testDorm1() throws Exception {
        System.out.println(queryAllDormManager());
    }


    @Override
    public boolean delDormManagerByDormMasterId(String DormMasterId) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String delSql = "update t_dormmaster set is_effective = 1 where dormmasterId = ?";
        PreparedStatement pre = conn.prepareStatement(delSql);
        pre.setString(1,DormMasterId);
        int i = pre.executeUpdate();
        DataBaseUtils.closeConn(conn);
        if(i > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int dormManagerCount() throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String selectSql = "select * from t_dormmaster inner join t_dorm on t_dormmaster.dorm_id = t_dorm.dorm_id;";
        PreparedStatement pre = conn.prepareStatement(selectSql);
        ResultSet res = pre.executeQuery();
        List<DormManager> dormManagers = new ArrayList<>();
        while(res.next()){
            DormManager dormManager = new DormManager();
            dormManager.setDormmasterId(res.getString("dormmasterId"));
            dormManager.setUserName(res.getString("userName"));
            dormManager.setPassword(res.getString("password"));
            dormManager.setSex(res.getString("sex"));
            dormManager.setTel(res.getString("tel"));
            dormManager.setDorm_id(res.getInt("dorm_id"));
            dormManager.setDorm_name(res.getString("dorm_name"));
            dormManager.setIs_effective(res.getInt("is_effective"));
            if(dormManager.getIs_effective()!=1){
                dormManagers.add(dormManager);
            }
        }
        DataBaseUtils.closeConn(conn);
        return dormManagers.size();
    }

    @Override
    public int addDormManager(DormManager dormManager) throws Exception {
        if(isExists(dormManager.getDormmasterId(),dormManager.getPassword()) != null){
            System.out.println("该宿管已经存在!");
            return 0;
        }
        Connection conn = DataBaseUtils.getConn();
        String insertSql = "insert into t_dormmaster values(?,?,?,?,?,?,?)";
        PreparedStatement pre = conn.prepareStatement(insertSql);
        pre.setString(1,dormManager.getDormmasterId());
        pre.setString(2,dormManager.getUserName());
        pre.setString(3,dormManager.getPassword());
        pre.setString(4,dormManager.getSex());
        pre.setString(5,dormManager.getTel());
        pre.setInt(6,dormManager.getDorm_id());
        pre.setInt(7,0);
        int i = pre.executeUpdate();
        DataBaseUtils.closeConn(conn);
        return i;
    }

    @Override
    public int updateDormManager(DormManager dormManager) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String insertSql = "UPDATE `t_dormmaster` SET userName = ?,`password` = ?, sex = ?, tel = ?, dorm_id = ?, is_effective = 0 WHERE `dormmasterId` = ?;";
        PreparedStatement pre = conn.prepareStatement(insertSql);
        pre.setString(1,dormManager.getUserName());
        pre.setString(2,dormManager.getPassword());
        pre.setString(3,dormManager.getSex());
        pre.setString(4,dormManager.getTel());
        pre.setInt(5,dormManager.getDorm_id());
        pre.setString(6,dormManager.getDormmasterId());
        int i = pre.executeUpdate();
        DataBaseUtils.closeConn(conn);
        return i;
    }

    @Override
    public List<AbsenceRecord> queryAllAbsenceRecordByDorm_id(int dorm_id, AbsenceRecord record) throws Exception {
        List<AbsenceRecord> recordList = new ArrayList<AbsenceRecord>();
        StringBuffer sb = new StringBuffer("select * from t_absencerecord t1 inner join t_dorm ON t1.`dorm_id` = t_dorm.`dorm_id` where t1.dorm_id = ?");
        if(StringUtil.isNotEmpty(record.getStudentId())) {
            sb.append(" and t1.studentId like '%"+record.getStudentId()+"%' ");
        } else if(StringUtil.isNotEmpty(record.getName())) {
            sb.append(" and t1.name like '%"+record.getName()+"%' ");
        }else if(StringUtil.isNotEmpty(record.getRoom_id())){
            sb.append("and t1.room_id like '%" + record.getDorm_id() + "%' ");
        }
        if(record.getDorm_id()!=0) {
            sb.append(" and t1.dorm_id="+record.getDorm_id());
        }
        if(StringUtil.isNotEmpty(record.getAcademy())){
            sb.append(" and t1.academy='"+record.getAcademy()+"'");
        }
        System.out.println("执行的SQL语句:"+ sb.toString());
        Connection conn = DataBaseUtils.getConn();
        PreparedStatement pre = conn.prepareStatement(sb.toString());
        pre.setInt(1,dorm_id);
        ResultSet res = pre.executeQuery();
        while(res.next()) {
            AbsenceRecord recordNew = new AbsenceRecord();
            recordNew.setRecordId(res.getInt("recordId"));
            recordNew.setStudentId(res.getString("studentId"));
            recordNew.setName(res.getString("name"));
            recordNew.setAcademy(res.getString("academy"));
            recordNew.setMajor_and_class(res.getString("major_and_class"));
            recordNew.setDorm_id(res.getInt("dorm_id"));
            recordNew.setDorm_name(res.getString("dorm_name"));
            recordNew.setRoom_id(res.getString("room_id"));

            Date date = res.getDate("absenceTime");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String absenceTime = sdf.format(date);
            recordNew.setAbsenceTime(absenceTime);
            recordNew.setRemark(res.getString("remark"));
            recordList.add(recordNew);
        }
        return recordList;
    }
}
