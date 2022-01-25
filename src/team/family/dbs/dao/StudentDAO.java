package team.family.dbs.dao;

import team.family.dbs.bean.Student;

import java.util.List;

public interface StudentDAO {
    /**
     * 查询并返回所有学生的信息
     * @return
     */
    List<Student> queryAllStudent() throws Exception;

    Student queryStudentByUserNameAndPassword(String userName,String password) throws Exception;
    Student queryStudentByStudentId(String userName) throws Exception;
    /**
     * 删除学生通过学号
     * @return
     */
    boolean delStudentByStuNum(String studentId) throws Exception;

    /**
     * 通过学号修改学生信息
     * @return
     */
    boolean updateStudentByStuId(Student stu) throws Exception;

    /**
     * 增加一位学生
     * @return
     */
    boolean addStudent(Student stu) throws Exception;

    /**
     * 有条件的返回学生集合
     * @param stu
     * @return
     */
    List<Student> queryAllStudentByCondition(Student stu) throws Exception;

}
