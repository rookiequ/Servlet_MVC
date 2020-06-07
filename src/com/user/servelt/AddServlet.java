package com.user.servelt;

import com.user.domain.Student;
import com.user.service.StudentService;
import com.user.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@WebServlet(name = "AddServlet",urlPatterns = {"/AddServlet"})
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        String sname = (String) request.getParameter("sname");
        String gender = (String) request.getParameter("gender");
        String phone = (String) request.getParameter("phone");
        String date  = (String) request.getParameter("birthday");
        String info = (String) request.getParameter("info");
        String[] hobbys = request.getParameterValues("hobby");
        String hobby = Arrays.toString(hobbys);
        try {
           Date birthday =  new SimpleDateFormat("yyyy-MM-dd").parse(date);
           Student student = new Student(sname,gender,phone,birthday,hobby,info);
           StudentService studentService = new StudentServiceImpl();
           studentService.insert(student);
           request.getRequestDispatcher("StudentListServlet").forward(request,response);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
