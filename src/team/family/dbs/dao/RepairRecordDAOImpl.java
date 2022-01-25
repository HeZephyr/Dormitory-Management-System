package team.family.dbs.dao;

import team.family.dbs.bean.RepairRecord;
import team.family.dbs.util.DataBaseUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RepairRecordDAOImpl implements RepairRecordDAO{

    @Override
    public List<RepairRecord> getAllNotDealRecord() throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String sql = "select * from t_repairRecord where is_deal = 0;";
        PreparedStatement pre = conn.prepareStatement(sql);
        ArrayList<RepairRecord> repairRecords = new ArrayList<>();
        ResultSet res = pre.executeQuery();
        while(res.next()){
            RepairRecord record = new RepairRecord();
            record.setUserName(res.getString("userName"));
            record.setRoom_id(res.getString("room_id"));
            record.setRepair_remark(res.getString("repair_remark"));
            Date date1 = res.getDate("record_time");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String record_time = sdf.format(date1);
            record.setRecord_time(record_time);
            record.setRecord_id(res.getInt("record_id"));
            record.setIs_deal(res.getInt("is_deal"));
            repairRecords.add(record);
        }
        return repairRecords;

    }

    @Override
    public int delRepairRecord(int record_id) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String sql = "update t_repairRecord set is_deal = 1 where record_id = ?;";
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setInt(1,record_id);
        int i = pre.executeUpdate();
        DataBaseUtils.closeConn(conn);
        return i;
    }

    @Override
    public List<RepairRecord> getAllDealRecord() throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String sql = "select * from t_repairRecord;";
        PreparedStatement pre = conn.prepareStatement(sql);
        ArrayList<RepairRecord> repairRecords = new ArrayList<>();
        ResultSet res = pre.executeQuery();
        while(res.next()){
            RepairRecord record = new RepairRecord();
            record.setUserName(res.getString("userName"));
            record.setRoom_id(res.getString("room_id"));
            record.setRepair_remark(res.getString("repair_remark"));
            Date date1 = res.getDate("record_time");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String record_time = sdf.format(date1);
            record.setRecord_time(record_time);
            record.setRecord_id(res.getInt("record_id"));
            record.setIs_deal(res.getInt("is_deal"));
            repairRecords.add(record);
        }
        return repairRecords;
    }

    @Override
    public int addRepairRecord(RepairRecord record) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String insertSql = "insert into t_repairrecord(userName,room_id,record_time,repair_remark,is_deal) values(?,?,?,?,?);";
        PreparedStatement pre = conn.prepareStatement(insertSql);
        pre.setString(1,record.getUserName());
        pre.setString(2,record.getRoom_id());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date1 = sdf.parse(record.getRecord_time());
        System.out.println("date1 = " + date1);
        Date record_time = new Date(date1.getTime());
        pre.setDate(3,record_time);
        pre.setString(4,record.getRepair_remark());
        pre.setInt(5,0);

        int i = pre.executeUpdate();
        DataBaseUtils.closeConn(conn);
        return i;
    }
}
