package com.user.servelt;

import java.io.IOException;

@javax.servlet.annotation.WebServlet(name = "TestServlet",urlPatterns = {"/TestServlet"})
public class TestServlet extends javax.servlet.http.HttpServlet {
    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setAttribute("name", "tom");
        /** 请求转发，带数据*/
        request.getRequestDispatcher("index.jsp").forward(request,response);
        /** 重定向，不带数据*/
        //response.sendRedirect("index.jsp");
    }
}
