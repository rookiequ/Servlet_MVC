package com.user.dao;

import com.user.domain.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {

    //每页的数据数量
    public  static int PAGE_SIZE = 5;

    /**
     * 查询所有学生信息
     * @return
     */
    List<Student> findAll() throws SQLException;

    /**
     * 添加学生信息
     * @param student
     */
    void insert(Student student) throws SQLException;

    /**
     * 删除一名学生信息
     * @param sid
     */
    void delete(int sid) throws SQLException;

    /**
     * 根据sid查询一名学生信息
     * @param sid
     * @return
     */
    Student findStudentById(int sid) throws SQLException;

    /**
     * 根据sid更新学生信息
     * @param student
     */
    void update(Student student) throws SQLException;

    /**
     * 根据姓名和性别查询学生
     * @param sname
     * @param gender
     * @return
     */
    List<Student> searchStudents(String sname, String gender) throws SQLException;

    /**
     * 根据页码查询学生
     * @param currentPage
     * @return
     */
    List<Student> findStudentByPage(int currentPage) throws SQLException;

    /**
     * 获取总记录数
     * @return
     */
    int findCount() throws SQLException;

    /**
     * 根据性别和姓名查询学生信息分页版
     * @param currentPage
     * @return
     */
    List<Student> searchStudentByPage(int currentPage, String sname, String gender) throws SQLException;

    /**
     * 获取符合查询条件的总人数
     * @return
     */
    int searchCount(String sname, String gender) throws SQLException;
}
