package team.family.dbs.service;

import team.family.dbs.bean.Student;
import team.family.dbs.dao.StudentDAO;
import team.family.dbs.dao.StudentDAOImpl;

import java.util.List;

public class StudentServiceImpl implements StudentService{
    StudentDAO studentDAO = new StudentDAOImpl();
    @Override
    public Student isExistsStudent(String userName, String password) {
        try {
            return studentDAO.queryStudentByUserNameAndPassword(userName,password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Student isExistsStudent(String userName) {
        try {
            return studentDAO.queryStudentByStudentId(userName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean delStudentByStudentId(String studentId) {
        try {
            return studentDAO.delStudentByStuNum(studentId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateStudentByStudentId(Student stu) {
        try {
            return studentDAO.updateStudentByStuId(stu);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public List<Student> queryAllStudent() {
        try {
            return studentDAO.queryAllStudent();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addStudent(Student stu) {
        try {
            return studentDAO.addStudent(stu);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public List<Student> queryAllStudentByCondition(Student stu) {
        try {
            return studentDAO.queryAllStudentByCondition(stu);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
