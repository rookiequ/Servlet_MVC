package com.user.dao.impl;

import com.user.dao.StudentDAO;
import com.user.domain.Student;
import com.user.utils.JDBCUtil02;
import com.user.utils.TextUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {


    QueryRunner queryRunner = new QueryRunner(JDBCUtil02.getDataSource());

    @Override
    public List<Student> findAll() throws SQLException {

        List<Student> studentList = queryRunner.query("select * from stu", new BeanListHandler<Student>(Student.class));
        for (Student student : studentList) {
            System.out.println(student.toString());
        }
        return studentList;
    }

    @Override
    public void insert(Student student) throws SQLException {
        queryRunner.update("insert into stu values(null,?,?,?,?,?,?)",
                student.getSname(),
                student.getGender(),
                student.getPhone(),
                student.getBirthday(),
                student.getHobby(),
                student.getInfo());
    }

    @Override
    public void delete(int sid) throws SQLException {
        queryRunner.update("delete from stu where sid = ?", sid);
    }

    @Override
    public Student findStudentById(int sid) throws SQLException {
        Student student = (Student) queryRunner.query("select * from stu where sid = ?",new BeanHandler<Student>(Student.class), sid);
        return student;
    }

    @Override
    public void update(Student student) throws SQLException {
        queryRunner.update("update stu set sname=?, gender=? , phone=? ,birthday=?, hobby=?, info=? where sid=?",
                student.getSname(),
                student.getGender(),
                student.getPhone(),
                student.getBirthday(),
                student.getHobby(),
                student.getInfo(),
                student.getSid());
    }



    @Override
    public List<Student> searchStudents(String sname, String gender) throws SQLException {

        String sql = "select * from stu where 1=1 ";
        List<String> list = new ArrayList<String>();
        if (!TextUtils.isEmpty(sname)){
            sql = sql + " and sname like ?";
            list.add("%"+sname+"%");
        }
        if (!TextUtils.isEmpty((gender))){
            sql = sql + " and gender = ?";
            list.add(gender);
        }
        List<Student> studentList = queryRunner.query(sql, new BeanListHandler<Student>(Student.class),list.toArray());
        return studentList;
    }

    @Override
    public List<Student> findStudentByPage(int currentPage) throws SQLException {
        //select * from stu limit ? offset ?;
        //5 0  第1页 （1-1）* 5
        //5 5  第2页 （2-1）* 5
        //5 10  第3页 （3-1）* 5
        //pageSize 第currentPage页  （currentPage-1) * 5
        int offset = (currentPage - 1) * PAGE_SIZE;
        return queryRunner.query("select * from stu limit ? offset ?", new BeanListHandler<Student>(Student.class), PAGE_SIZE, offset);
    }

    @Override
    public int findCount() throws SQLException {
        Long count = (Long) queryRunner.query("select count(*) from stu",new ScalarHandler());
        return count.intValue();
    }

    @Override
    public List<Student> searchStudentByPage(int currentPage,String sname, String gender) throws SQLException {
        String sql = "select * from stu where 1=1 ";
        List<Object> list = new ArrayList<Object>();
        if (!TextUtils.isEmpty(sname)){
            sql = sql + " and sname like ?";
            list.add("%"+sname+"%");
        }
        if (!TextUtils.isEmpty((gender))){
            sql = sql + " and gender = ?";
            list.add(gender);
        }
        sql = "select * from (" + sql + ") as a limit ? offset ?";
        int offset = (currentPage - 1) * PAGE_SIZE;
        list.add(PAGE_SIZE);
        list.add(offset);
        List<Student> studentList = queryRunner.query(sql, new BeanListHandler<Student>(Student.class), list.toArray());
        return studentList;
    }

    @Override
    public int searchCount(String sname, String gender) throws SQLException {
        String sql = "select count(*) from stu where 1=1 ";
        List<String> list = new ArrayList<String>();
        if (!TextUtils.isEmpty(sname)){
            sql = sql + " and sname like ?";
            list.add("%"+sname+"%");
        }
        if (!TextUtils.isEmpty((gender))){
            sql = sql + " and gender = ?";
            list.add(gender);
        }

        Long count = (Long) queryRunner.query(sql, new ScalarHandler(), list);
        return count.intValue();
    }
}
