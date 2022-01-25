package team.family.dbs.dao;

import team.family.dbs.bean.Student;
import team.family.dbs.util.DataBaseUtils;
import team.family.dbs.util.StringUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public List<Student> queryAllStudent() throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String selectSql = "select * from t_student inner join t_dorm on t_student.dorm_id = t_dorm.dorm_id;";
        PreparedStatement pre = conn.prepareStatement(selectSql);
        ResultSet res = pre.executeQuery();
        ArrayList<Student> list = new ArrayList<>();
        while (res.next()) {
            Student stu = new Student();
            stu.setStudentId(res.getString("studentId"));
            stu.setUserName(res.getString("userName"));
            stu.setPassword(res.getString("password"));
            stu.setSex(res.getString("sex"));
            stu.setNative_place(res.getString("native_place"));
            stu.setDorm_id(res.getInt("dorm_id"));
            stu.setDorm_name(res.getString("dorm_name"));
            stu.setAcademy(res.getString("academy"));
            stu.setTel(res.getString("tel"));
            stu.setMajor_and_class(res.getString("major_and_class"));
            stu.setIs_effective(res.getInt("is_effective"));
            stu.setRoom_id(res.getString("room_id"));
            if (stu.getIs_effective() != 1) {
                list.add(stu);
            }
        }
        return list;
    }

    @Override
    public Student queryStudentByUserNameAndPassword(String userName, String password) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String selectSql = "select * from t_student inner join t_dorm on t_student.dorm_id = t_dorm.dorm_id where studentId = ? and password = ?;";
        PreparedStatement pre = conn.prepareStatement(selectSql);
        pre.setString(1, userName);
        pre.setString(2, password);
        ResultSet res = pre.executeQuery();
        ArrayList<Student> list = new ArrayList<>();
        if (res.next()) {
            Student stu = new Student();
            stu.setStudentId(res.getString("studentId"));
            stu.setUserName(res.getString("userName"));
            stu.setPassword(res.getString("password"));
            stu.setSex(res.getString("sex"));
            stu.setNative_place(res.getString("native_place"));
            stu.setDorm_id(res.getInt("dorm_id"));
            stu.setDorm_name(res.getString("dorm_name"));
            stu.setAcademy(res.getString("academy"));
            stu.setTel(res.getString("tel"));
            stu.setMajor_and_class(res.getString("major_and_class"));
            stu.setIs_effective(res.getInt("is_effective"));
            stu.setRoom_id(res.getString("room_id"));
            if (stu.getIs_effective() != 1) {
                return stu;
            }
        }
        return null;
    }

    @Override
    public Student queryStudentByStudentId(String userName) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String selectSql = "select * from t_student inner join t_dorm on t_student.dorm_id = t_dorm.dorm_id where studentId = ?;";
        PreparedStatement pre = conn.prepareStatement(selectSql);
        pre.setString(1, userName);
        ResultSet res = pre.executeQuery();
        ArrayList<Student> list = new ArrayList<>();
        if (res.next()) {
            Student stu = new Student();
            stu.setStudentId(res.getString("studentId"));
            stu.setUserName(res.getString("userName"));
            stu.setPassword(res.getString("password"));
            stu.setSex(res.getString("sex"));
            stu.setNative_place(res.getString("native_place"));
            stu.setDorm_id(res.getInt("dorm_id"));
            stu.setDorm_name(res.getString("dorm_name"));
            stu.setAcademy(res.getString("academy"));
            stu.setTel(res.getString("tel"));
            stu.setMajor_and_class(res.getString("major_and_class"));
            stu.setIs_effective(res.getInt("is_effective"));
            stu.setRoom_id(res.getString("room_id"));
            if (stu.getIs_effective() != 1) {
                return stu;
            }
        }
        return null;
    }

    @Test
    public void testQuery() throws Exception {
        System.out.println(queryStudentByUserNameAndPassword("5720181423", "123456"));
    }

    @Override
    public boolean delStudentByStuNum(String studentId) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String delSql = "update t_student set is_effective = 1 where studentId = ?; ";
        PreparedStatement pre = conn.prepareStatement(delSql);
        pre.setString(1, studentId);
        int i = pre.executeUpdate();
        DataBaseUtils.closeConn(conn);
        if (i > 0) {
            System.out.println("删除学生成功!");
            return true;
        } else {
            System.out.println("删除学生失败");
            return false;
        }
    }

    @Test
    public void testXXX() throws Exception {
        delStudentByStuNum("324892384");
    }

    @Override
    public boolean updateStudentByStuId(Student stu) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String updateSql = "update t_student set userName = ?,password = ?,sex =?,tel = ?,native_place = ?,academy = ?,major_and_class = ?,dorm_id = ?,room_id = ?,is_effective = 0 where studentId = ?;";
        PreparedStatement pre = conn.prepareStatement(updateSql);

        pre.setString(1, stu.getUserName());
        pre.setString(2, stu.getPassword());
        pre.setString(3, stu.getSex());
        pre.setString(4, stu.getTel());
        pre.setString(5, stu.getNative_place());
        pre.setString(6, stu.getAcademy());
        pre.setString(7, stu.getMajor_and_class());
        pre.setInt(8, stu.getDorm_id());
        pre.setString(9, stu.getRoom_id());

        pre.setString(10, stu.getStudentId());

        int i = pre.executeUpdate();
        DataBaseUtils.closeConn(conn);
        if (i > 0) {
            System.out.println("学生数据更新成功!");
            return true;
        } else {
            System.out.println("学生数据更新失败");
            return false;
        }
    }

    @Test
    public void testUpdate() throws Exception {
        Student student = new Student();
        student.setStudentId("5720181423");
        student.setUserName("李厚宇");
        student.setDorm_id(6);
        if (updateStudentByStuId(student)) {
            System.out.println("???");
        }

    }


    @Override
    public boolean addStudent(Student stu) throws Exception {
        Connection conn = DataBaseUtils.getConn();
        String insertSql = "insert into t_student values(?,?,?,?,?,?,?,?,?,?,?);";
        PreparedStatement pre = conn.prepareStatement(insertSql);
        pre.setString(1, stu.getStudentId());
        pre.setString(2, stu.getUserName());
        pre.setString(3, stu.getPassword());
        pre.setString(4, stu.getSex());
        pre.setString(5, stu.getTel());
        pre.setString(6, stu.getNative_place());
        pre.setString(7, stu.getAcademy());
        pre.setString(8, stu.getMajor_and_class());
        pre.setInt(9, stu.getDorm_id());
        pre.setString(10, stu.getRoom_id());
        pre.setInt(11, 0);

        int i = pre.executeUpdate();
        DataBaseUtils.closeConn(conn);
        if (i > 0) {
            System.out.println("插入新的学生成功!");
            return true;
        } else {
            System.out.println("插入新的学生失败");
            return false;
        }

    }

    @Override
    public List<Student> queryAllStudentByCondition(Student stu) throws Exception {
        List<Student> studentList = new ArrayList<Student>();
        StringBuffer sb = new StringBuffer("select * from t_student t1 inner join t_dorm on t1.dorm_id = t_dorm.dorm_id");
        if (StringUtil.isNotEmpty(stu.getUserName())) {
            sb.append(" where t1.userName like '%" + stu.getUserName() + "%'");
        } else if (StringUtil.isNotEmpty(stu.getStudentId())) {
            sb.append(" where t1.studentId like '%" + stu.getStudentId() + "%'");
        } else if (StringUtil.isNotEmpty(stu.getDorm_name())) {
            sb.append(" where t_dorm.dorm_name like '%" + stu.getDorm_name() + "%'");
        } else if (StringUtil.isNotEmpty(stu.getAcademy())) {
            sb.append(" where t1.academy like '%" + stu.getAcademy() + "%'");
        }
        if (stu.getDorm_id() != 0) {
            sb.append(" and t1.dorm_id=" + stu.getDorm_id());
        }
        if (StringUtil.isNotEmpty(stu.getAcademy())) {
            sb.append(" and t1.academy = '" + stu.getAcademy() + "'");
        }

        System.out.println(sb);
        Connection conn = DataBaseUtils.getConn();
        PreparedStatement pre = conn.prepareStatement(sb.toString());
        ResultSet res = pre.executeQuery();
        while (res.next()) {
            Student student = new Student();
            student.setStudentId(res.getString("studentId"));
            student.setUserName(res.getString("userName"));
            student.setPassword(res.getString("password"));
            student.setSex(res.getString("sex"));
            student.setNative_place(res.getString("native_place"));
            student.setDorm_id(res.getInt("dorm_id"));
            student.setDorm_name(res.getString("dorm_name"));
            student.setAcademy(res.getString("academy"));
            student.setTel(res.getString("tel"));
            student.setMajor_and_class(res.getString("major_and_class"));
            student.setIs_effective(res.getInt("is_effective"));
            student.setRoom_id(res.getString("room_id"));
            if (student.getIs_effective() == 0) {
                studentList.add(student);
            }
        }
        return studentList;
    }
}
