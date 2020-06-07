package com.user.servelt;

import com.user.domain.PageBean;
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

@WebServlet(name = "SearchStudentPageServlet", urlPatterns = {"/SearchStudentPageServlet"})
public class SearchStudentPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO 查询的分页情况
        request.setCharacterEncoding("utf-8");
        String sname = (String) request.getParameter("sname");
        String gender = (String) request.getParameter("sgender");
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        System.out.println(currentPage);
        StudentService studentService = new StudentServiceImpl();
        try {
            PageBean pageBean = studentService.searchStudentByPage(sname, gender, currentPage);
            request.setAttribute("pageBean", pageBean);
            request.getRequestDispatcher("list_page.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
