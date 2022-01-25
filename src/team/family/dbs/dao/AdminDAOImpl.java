package team.family.dbs.dao;

import team.family.dbs.bean.Admin;
import team.family.dbs.util.DataBaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDAOImpl implements AdminDAO{
    @Override
    public Admin isExistsAdmin(String userName,String password){
        Connection conn = null;
        try {
            conn = DataBaseUtils.getConn();
            String selectSql = "select * from t_admin where adminId = ? and password = ?;";
            PreparedStatement pre = conn.prepareStatement(selectSql);
            pre.setString(1,userName);
            pre.setString(2,password);
            ResultSet res = pre.executeQuery();

            if(res.next()){
                Admin admin = new Admin();
                admin.setAdminId(res.getString("adminId"));
                admin.setUserName(res.getString("userName"));
                admin.setPassword(res.getString("password"));
                return admin;
            }else{
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DataBaseUtils.closeConn(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public int updateAdmin(Admin admin) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String updateSql = "update t_admin set adminId = ?,userName = ?,password = ?;";
        PreparedStatement pre = conn.prepareStatement(updateSql);
        pre.setString(1,admin.getAdminId());
        pre.setString(2,admin.getUserName());
        pre.setString(3,admin.getPassword());
        int i = pre.executeUpdate();
        DataBaseUtils.closeConn(conn);
        return i;
    }
}

