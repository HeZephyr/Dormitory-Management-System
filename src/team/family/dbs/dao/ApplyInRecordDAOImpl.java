package team.family.dbs.dao;

import team.family.dbs.bean.ApplyInRecord;
import team.family.dbs.util.DataBaseUtils;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ApplyInRecordDAOImpl implements ApplyInRecordDAO{
    @Override
    public List<ApplyInRecord> getAllVisitRecord() throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String sql = "select * from t_applyInRecord t1 inner join t_dorm t2 on t1.dest_visit_dorm_id = t2.dorm_id where t1.is_effective = 0";
        PreparedStatement pre = conn.prepareStatement(sql);
        ResultSet res = pre.executeQuery();
        ArrayList<ApplyInRecord> applyInRecords = new ArrayList<>();
        while(res.next()){
            ApplyInRecord record = new ApplyInRecord();
            record.setApplyId(res.getInt("applyId"));
            record.setApplyName(res.getString("applyName"));
            record.setDest_visit_dorm_name(res.getString("dorm_name"));
            record.setDest_visit_dorm_id(res.getInt("dest_visit_dorm_id"));
            record.setVisitDest(res.getString("visitDest"));

            Timestamp date = res.getTimestamp("visit_time");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String until_time = sdf.format(date);
            record.setVisit_time(until_time);

            record.setIs_effective(res.getInt("is_effective"));

            if(record.getIs_effective() == 0){
                applyInRecords.add(record);
            }
        }
        DataBaseUtils.closeConn(conn);
        return applyInRecords;
    }

    @Override
    public int addVisitRecord(ApplyInRecord applyInRecord) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String sql = "insert into t_applyInRecord(applyName,dest_visit_dorm_id,visitDest,visit_time,is_effective) values(?,?,?,?,0); ";
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setString(1,applyInRecord.getApplyName());
        pre.setInt(2,applyInRecord.getDest_visit_dorm_id());
        pre.setString(3,applyInRecord.getVisitDest());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(applyInRecord.getVisit_time());
        java.util.Date date = sdf.parse(applyInRecord.getVisit_time());
//        java.sql.Date until_time = new java.sql.Date(date.getTime());
        Timestamp until_time = new Timestamp(date.getTime());
        pre.setTimestamp(4,until_time);
        int i = pre.executeUpdate();
        DataBaseUtils.closeConn(conn);
        return i;
    }

    @Override
    public int delVisitRecord(int applyId) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String sql = "update t_applyInRecord set is_effective = 1 where applyId = ? ";
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setInt(1,applyId);
        int i = pre.executeUpdate();
        DataBaseUtils.closeConn(conn);
        return i;
    }
}
