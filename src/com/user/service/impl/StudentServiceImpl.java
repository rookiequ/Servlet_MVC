package com.user.service.impl;

import com.user.dao.StudentDAO;
import com.user.dao.impl.StudentDAOImpl;
import com.user.domain.PageBean;
import com.user.domain.Student;
import com.user.service.StudentService;

import java.sql.SQLException;
import java.util.List;


public class StudentServiceImpl implements StudentService {

    StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    public List<Student> findAll() throws SQLException {

        return studentDAO.findAll();
    }

    @Override
    public void insert(Student student) throws SQLException {
        studentDAO.insert(student);
    }

    @Override
    public void delete(int sid) throws SQLException {
        studentDAO.delete(sid);
    }

    @Override
    public Student findStudentById(int sid) throws SQLException {
        return studentDAO.findStudentById(sid);
    }

    @Override
    public void update(Student student) throws SQLException {
        studentDAO.update(student);
    }

    @Override
    public List<Student> searchStudents(String sname, String gender) throws SQLException {
        return studentDAO.searchStudents(sname, gender);
    }

    @Override
    public PageBean findStudentByPage(int currentPage) throws SQLException {
        PageBean<Student> pageBean = new PageBean<Student>();
        int pageSize = StudentDAO.PAGE_SIZE;
        //设置pagebean每页显示多少
        pageBean.setPageSize(pageSize);
        List<Student> studentList = studentDAO.findStudentByPage(currentPage);
        //设置pagebean的学生集合
        pageBean.setList(studentList);
        //设置当前页
        pageBean.setCurrentPage(currentPage);
        //总页数和总记录数
        int totalCount = studentDAO.findCount();
        //设置总页数
        pageBean.setTotalSize(totalCount);
        //计算总页数
        int totalPage = totalCount%pageSize==0 ? (totalCount/pageSize) : (totalCount/pageSize)+1;
        //设置总页数
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }

    @Override
    public PageBean searchStudentByPage(String sname, String gender, int currentPage) throws SQLException {
        PageBean<Student> pageBean = new PageBean<Student>();
        //设置pagebean的属性
        int pageSize = StudentDAO.PAGE_SIZE;
        pageBean.setPageSize(StudentDAO.PAGE_SIZE);
        pageBean.setCurrentPage(currentPage);
        pageBean.setList(studentDAO.searchStudentByPage(currentPage, sname, gender));
        int totalSize = studentDAO.searchCount(sname, gender);
        pageBean.setTotalSize(totalSize);
        int totalPage = totalSize%pageSize==0 ? (totalSize/pageSize) : (totalSize/pageSize)+1;
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }
}
