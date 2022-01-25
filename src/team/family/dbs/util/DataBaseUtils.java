package team.family.dbs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseUtils {
    /**
     * 获取数据库连接
     * @return
     * @throws Exception
     */
    public  static Connection getConn() throws Exception {
        //注册驱动。
        Class.forName(PropertiesUtil.getValue("jdbcName"));
        //获取连接。
        Connection con = DriverManager.getConnection(PropertiesUtil.getValue("dbUrl"), PropertiesUtil.getValue("dbUserName"), PropertiesUtil.getValue("dbPassword"));
        return con;
    }

    /**
     * 关闭数据库连接
     * @param con
     * @throws Exception
     */
    public static void closeConn(Connection con) throws Exception {
        if(con!=null) {
            con.close();
        }
    }
    public static void main(String args[]){
        Connection conn = null;
        try {
            conn = getConn();
            System.out.println(conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
