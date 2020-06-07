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

@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        int sid = Integer.parseInt(request.getParameter("sid"));
        String sname = (String) request.getParameter("sname");
        String gender = (String) request.getParameter("gender");
        String phone = (String) request.getParameter("phone");
        String date = (String) request.getParameter("birthday");
        String info = (String) request.getParameter("info");
        String[] h = request.getParameterValues("hobby");
        String hobbys = "";
        for(int i=0;i<h.length;i++){
            if(i!=h.length-1){
                hobbys=hobbys+h[i]+",";
            }else {
                hobbys=hobbys+h[i];
            }
        }
        try {
            Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            Student student = new Student(sid,sname,gender,phone,birthday,hobbys,info);
            student.toString();
            StudentService studentService = new StudentServiceImpl();
            studentService.update(student);
            request.getRequestDispatcher("StudentListServlet").forward(request,response);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
