package team.family.dbs.dao;

import team.family.dbs.bean.ChangeRoomRecord;
import team.family.dbs.util.DataBaseUtils;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ChangeRoomRecordDAOImpl implements ChangeRoomRecordDAO{
    @Override
    public List<ChangeRoomRecord> getAllNotDealRecord() throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String sql = "select * from t_changeRoomRecord t1 where t1.isAgree not in(1,2);";
        PreparedStatement pre = conn.prepareStatement(sql);
        ArrayList<ChangeRoomRecord> changeRoomRecords = new ArrayList<>();
        ResultSet res = pre.executeQuery();
        while(res.next()){
            ChangeRoomRecord record = new ChangeRoomRecord();
            record.setRecord_id(res.getInt("record_id"));
            record.setApplyStudentId(res.getString("applyStudentId"));
            record.setApplyUserName(res.getString("applyUserName"));
            record.setOldRoom_id(res.getString("oldRoom_id"));
            record.setNewRoom_id(res.getString("newRoom_id"));
            record.setApplyReason(res.getString("applyReason"));
            record.setIsAgree(res.getInt("isAgree"));

            Date datetime = res.getDate("record_time");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String record_time = sdf.format(datetime);
            record.setRecord_time(record_time);
            changeRoomRecords.add(record);
        }
        DataBaseUtils.closeConn(conn);
        return changeRoomRecords;
    }

    @Override
    public List<ChangeRoomRecord> getSelfRecord(String studentId) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String sql = "select * from t_changeRoomRecord t1 where applyStudentId = ?;";
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setString(1,studentId);
        ArrayList<ChangeRoomRecord> changeRoomRecords = new ArrayList<>();
        ResultSet res = pre.executeQuery();
        while(res.next()){
            ChangeRoomRecord record = new ChangeRoomRecord();
            record.setRecord_id(res.getInt("record_id"));
            record.setApplyStudentId(res.getString("applyStudentId"));
            record.setApplyUserName(res.getString("applyUserName"));
            record.setOldRoom_id(res.getString("oldRoom_id"));
            record.setNewRoom_id(res.getString("newRoom_id"));
            record.setApplyReason(res.getString("applyReason"));
            record.setIsAgree(res.getInt("isAgree"));

            Date datetime = res.getDate("record_time");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String record_time = sdf.format(datetime);
            record.setRecord_time(record_time);
            changeRoomRecords.add(record);
        }
        DataBaseUtils.closeConn(conn);
        return changeRoomRecords;

    }

    @Override
    public int delRecord(int record_id,int result) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String sql = "update t_changeRoomRecord set isAgree = ? where record_id = ?;";
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setInt(1,result);
        pre.setInt(2,record_id);
        int i = pre.executeUpdate();
        DataBaseUtils.closeConn(conn);
        return i;
    }

    @Override
    public int addRecord(ChangeRoomRecord record) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String sql = "insert into t_changeRoomRecord(applyStudentId,applyUserName,oldRoom_id,newRoom_id,record_time,applyReason,isAgree) values(?,?,?,?,?,?,0);";
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setString(1,record.getApplyStudentId());
        pre.setString(2,record.getApplyUserName());
        pre.setString(3,record.getOldRoom_id());
        pre.setString(4,record.getNewRoom_id());
        System.out.println("在dao文件里面的" + record.getRecord_time());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        java.util.Date parse = sdf.parse(record.getRecord_time());
        Timestamp timestamp = new Timestamp(parse.getTime());
//        Date record_time = new Date(parse.getTime());

        pre.setTimestamp(5,timestamp);

        pre.setString(6,record.getApplyReason());


        int i = pre.executeUpdate();
        DataBaseUtils.closeConn(conn);
        return i;
    }

    @Override
    public ChangeRoomRecord getStudentIdByRecord_id(int record_id) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String sql = "select * from t_changeroomrecord t1 where t1.record_id = ?;";
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setInt(1,record_id);
        ResultSet res = pre.executeQuery();
        if(res.next()){
            ChangeRoomRecord record = new ChangeRoomRecord();
            record.setRecord_id(res.getInt("record_id"));
            record.setApplyStudentId(res.getString("applyStudentId"));
            record.setApplyUserName(res.getString("applyUserName"));
            record.setOldRoom_id(res.getString("oldRoom_id"));
            record.setNewRoom_id(res.getString("newRoom_id"));
            record.setApplyReason(res.getString("applyReason"));
            record.setIsAgree(res.getInt("isAgree"));


            Timestamp record_time1 = res.getTimestamp("record_time");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String record_time = sdf.format(record_time1);
            record.setRecord_time(record_time);
            DataBaseUtils.closeConn(conn);
            return record;
        }
        DataBaseUtils.closeConn(conn);
        return null;
    }
}
