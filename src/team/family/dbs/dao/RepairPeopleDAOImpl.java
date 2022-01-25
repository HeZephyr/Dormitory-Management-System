package team.family.dbs.dao;

import team.family.dbs.bean.RepairPeople;
import team.family.dbs.util.DataBaseUtils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RepairPeopleDAOImpl implements RepairPeopleDAO{
    @Override
    public RepairPeople isExists(String work_id, String password) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String sql = "select * from t_repairpeople where work_id = ? and password = ?;";
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setString(1,work_id);
        pre.setString(2,password);
        ResultSet res = pre.executeQuery();
        if(res.next()){
            RepairPeople repairPeople = new RepairPeople();
            repairPeople.setWork_id(res.getString("work_id"));
            repairPeople.setUserName(res.getString("userName"));
            repairPeople.setPassword(res.getString("password"));
            repairPeople.setIs_effective(res.getInt("is_effective"));
            DataBaseUtils.closeConn(conn);
            if(repairPeople.getIs_effective() == 0){
                return repairPeople;
            }else{
                return null;
            }
        }
        DataBaseUtils.closeConn(conn);
        return null;
    }


}
