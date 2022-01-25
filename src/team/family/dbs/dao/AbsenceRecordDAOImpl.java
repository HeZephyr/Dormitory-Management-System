package team.family.dbs.dao;

import team.family.dbs.bean.AbsenceRecord;
import team.family.dbs.bean.Student;
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

public class AbsenceRecordDAOImpl implements AbsenceRecordDAO{
    @Override
    public List<AbsenceRecord> querryAllAcademyByCondition(AbsenceRecord record) throws Exception {
        List<AbsenceRecord> recordList = new ArrayList<AbsenceRecord>();
        StringBuffer sb = new StringBuffer("select * from t_absencerecord t1 inner join t_dorm ON t1.`dorm_id` = t_dorm.`dorm_id`");
        if(StringUtil.isNotEmpty(record.getStudentId())) {
            sb.append(" where t1.studentId like '%"+record.getStudentId()+"%' ");
        } else if(StringUtil.isNotEmpty(record.getName())) {
            sb.append(" where t1.name like '%"+record.getName()+"%' ");
        }else if(StringUtil.isNotEmpty(record.getRoom_id())){
            sb.append("where t1.room_id like '%" + record.getDorm_id() + "%' ");
        }
        if(record.getDorm_id()!=0) {
            sb.append(" and t1.dorm_id="+record.getDorm_id());
        }
//        if(StringUtil.isNotEmpty(record.getAbsenceTime().toString())) {
//            sb.append(" and t1.absenceTime="+record.getAbsenceTime());
//        }
        if(StringUtil.isNotEmpty(record.getAcademy())){
            sb.append(" and t1.academy='"+record.getAcademy()+"'");
        }
        System.out.println("执行的SQL语句:"+ sb.toString());
        Connection conn = DataBaseUtils.getConn();
        PreparedStatement pre = conn.prepareStatement(sb.toString());
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
//            System.out.println("时间对象：" + absenceTime);
            recordList.add(recordNew);
        }
        return recordList;
    }

    @Override
    public int delAbsenceRecord(int record_id) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String delSql = "delete  from t_absencerecord where recordId = ?;";
        PreparedStatement pre = conn.prepareStatement(delSql);
        pre.setInt(1,record_id);
        int i = pre.executeUpdate();
        return i;
    }

    @Override
    public int updateAbsenceRecord(AbsenceRecord record) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String updateSql = "update t_absencerecord set studentId = ?,name = ?,academy =?,major_and_class= ?,dorm_id=?,room_id=?,absenceTime=?,remark=? where recordId = ?;";
        PreparedStatement pre = conn.prepareStatement(updateSql);
        pre.setString(1,record.getStudentId());
        String studentId = record.getStudentId();
        StudentDAO studentDAO = new StudentDAOImpl();
        Student student = studentDAO.queryStudentByStudentId(studentId);
        pre.setString(2,student.getUserName());
        pre.setString(3,student.getAcademy());
        pre.setString(4,student.getMajor_and_class());
        pre.setInt(5,student.getDorm_id());
        pre.setString(6,student.getRoom_id());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf.parse(record.getAbsenceTime());
        System.out.println(date);
        Date date1 = new Date(date.getTime());
        pre.setDate(7,date1);
        pre.setString(8,record.getRemark());
        pre.setInt(9,record.getRecordId());
        int i = pre.executeUpdate();
        DataBaseUtils.closeConn(conn);
        return i;
    }

    @Override
    public int addAbsenceRecord(AbsenceRecord record) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String insertSql = "insert into t_absencerecord(studentId,name,academy,major_and_class,dorm_id,room_id,absenceTime,remark) values(?,?,?,?,?,?,?,?)";
        PreparedStatement pre = conn.prepareStatement(insertSql);
        pre.setString(1,record.getStudentId());
        String studentId = record.getStudentId();
        StudentDAO studentDAO = new StudentDAOImpl();
        Student student = studentDAO.queryStudentByStudentId(studentId);
        pre.setString(2,student.getUserName());
        pre.setString(3,student.getAcademy());
        pre.setString(4,student.getMajor_and_class());
        pre.setInt(5,student.getDorm_id());
        pre.setString(6,student.getRoom_id());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf.parse(record.getAbsenceTime());
//        System.out.println(date);
        Date date1 = new Date(date.getTime());
        pre.setDate(7,date1);//妙
        pre.setString(8,record.getRemark());
        int i = pre.executeUpdate();
        DataBaseUtils.closeConn(conn);
        return i;
    }

    @Override
    public AbsenceRecord getAbsenceRecord(int record_id) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String selectSql = "select * from t_absencerecord t1 inner join t_dorm  t2 on t1.dorm_id = t2.dorm_id  where recordId = ?;";
        PreparedStatement pre = conn.prepareStatement(selectSql);
        pre.setInt(1,record_id);
        ResultSet res = pre.executeQuery();
        if(res.next()) {
            AbsenceRecord recordNew = new AbsenceRecord();
            recordNew.setRecordId(res.getInt("recordId"));
            recordNew.setStudentId(res.getString("studentId"));
            recordNew.setName(res.getString("name"));
            recordNew.setAcademy(res.getString("academy"));
            recordNew.setMajor_and_class(res.getString("major_and_class"));
            recordNew.setDorm_id(res.getInt("dorm_id"));
            recordNew.setDorm_name(res.getString("dorm_name"));
            recordNew.setRoom_id(res.getString("room_id"));
            recordNew.setRemark(res.getString("remark"));

            Date date = res.getDate("absenceTime");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String absenceTime = sdf.format(date);
            recordNew.setAbsenceTime(absenceTime);
//            System.out.println("时间对象：" + absenceTime);
            return recordNew;
        }
        return null;
    }

    @Override
    public List<AbsenceRecord> getAbsenceRecordListByStudentId(String studentId) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String selectSql= "select * from t_absencerecord inner join t_dorm on t_absencerecord.dorm_id = t_dorm.dorm_id where t_absencerecord.studentId = ?;";
        PreparedStatement pre = conn.prepareStatement(selectSql);
        pre.setString(1,studentId);
        ResultSet res = pre.executeQuery();
        ArrayList<AbsenceRecord> absenceRecords = new ArrayList<>();
        while(res.next()){
            AbsenceRecord record = new AbsenceRecord();
            record.setRecordId(res.getInt("recordId"));
            record.setStudentId(res.getString("studentId"));
            record.setName(res.getString("name"));
            record.setAcademy(res.getString("academy"));
            record.setDorm_id(res.getInt("dorm_id"));
            record.setDorm_name(res.getString("dorm_name"));
            record.setRemark(res.getString("remark"));
            Date date = res.getDate("absenceTime");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String absenceTime = sdf.format(date);
            record.setAbsenceTime(absenceTime);
            record.setMajor_and_class(res.getString("major_and_class"));
            absenceRecords.add(record);

        }
        return absenceRecords;
    }


    @Test
    public  void testX1() throws Exception {
        System.out.println(getAbsenceRecord(2));
    }


    @Test
    public void testQueryCondition() throws Exception {
        AbsenceRecord record = new AbsenceRecord();
        List<AbsenceRecord> recordList = querryAllAcademyByCondition(record);
        System.out.println(recordList);
    }

}
