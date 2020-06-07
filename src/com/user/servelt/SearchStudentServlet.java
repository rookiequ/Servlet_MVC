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
import java.util.List;

@WebServlet(name = "SearchStudentServlet",urlPatterns = {"/SearchStudentServlet"})
public class SearchStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String sname = (String) request.getParameter("sname");
        String gender = (String) request.getParameter("sgender");
        StudentService studentService = new StudentServiceImpl();
        try {
            List<Student> studentList = studentService.searchStudents(sname, gender);
            request.setAttribute("list", studentList);
            request.getRequestDispatcher("list_page.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
