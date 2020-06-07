package com.user.service;

import com.user.domain.PageBean;
import com.user.domain.Student;

import java.sql.SQLException;
import java.util.List;

/**
 * 学生业务逻辑处理规范
 */
public interface StudentService {

    /**
     * 查询所有学生信息
     *
     * @return
     */
    List<Student> findAll() throws SQLException;

    /**
     * 添加学生信息
     * @param student
     */
    void insert(Student student) throws SQLException;

    /**
     * 根据id删除学生信息
     * @param sid
     */
    void delete(int sid) throws SQLException;

    /**
     * 根据sid查询学生信息
     * @param sid
     */
    Student findStudentById(int sid) throws SQLException;

    /**
     * 根据sid修改学生信息
     * @param student
     */
    void update(Student student) throws SQLException;

    /**
     * 根据学生的姓名或性别查询学生
     * @param sname
     * @param gender
     * @return
     */
    List<Student> searchStudents(String sname, String gender) throws SQLException;

    /**
     * 根据页码查询当页的学生信息
     * @param currentPage
     * @return
     */
    PageBean findStudentByPage(int currentPage) throws SQLException;

    /**
     * 根据性别和姓名查询结果分页版
     * @param sname
     * @param gender
     * @param currentPage
     * @return
     */
    PageBean searchStudentByPage(String sname, String gender, int currentPage) throws SQLException;
}
