package team.family.dbs.service;

import team.family.dbs.bean.Student;

import java.util.List;

public interface StudentService {
    /**
     * 判断是否存在用户，存在则返回
     */
    Student isExistsStudent(String userName, String password);

    Student isExistsStudent(String userName);

    /**
     * 通过学号将学生数据删除
     * @return
     */
    boolean delStudentByStudentId(String studentId);

    /**
     * 更新学生对象
     * @param stu
     * @return
     */
    boolean updateStudentByStudentId(Student stu);


    /**
     * 查询所有学生对象，返回一个集合
     * @return
     */
    List<Student> queryAllStudent();

    /**
     * 添加一名学生 ，返回boolean
     * @param stu
     * @return
     */
    boolean addStudent(Student stu);

    /**
     * 根据学生对象的值进行返回学生集合
     * @param stu
     * @return
     */
    List<Student> queryAllStudentByCondition(Student stu);

}
