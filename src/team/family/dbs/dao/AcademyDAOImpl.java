package team.family.dbs.dao;

import team.family.dbs.bean.Academy;
import team.family.dbs.util.DataBaseUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AcademyDAOImpl implements  AcademyDAO{
    @Override
    public List<Academy> querryAllAcademy() throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String selectSql = "select * from t_academy;";
        PreparedStatement pre = conn.prepareStatement(selectSql);
        ResultSet res = pre.executeQuery();
        ArrayList<Academy> academies = new ArrayList<>();
        while(res.next()){
            Academy academy = new Academy();
            academy.setAcademy_id(res.getInt("academy_id"));
            academy.setAcademy(res.getString("academy"));
            academy.setIs_effective(res.getInt("is_effective"));
            if(academy.getIs_effective() == 0){
                academies.add(academy);
            }
        }
        return academies;
    }
}
