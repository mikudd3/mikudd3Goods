package com.mikudd3.controller.servlet;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
//    private UserService service = new UserServiceImpl();
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //获取数据
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        System.out.println(username);
//        EmployeeServiceImpl employee = service.selectByNameAndPassword(username, password);
//        if (employee == null) {
////            request.getRequestDispatcher("/pages/login/login.jsp").forward(request, response);
//            response.getWriter().write("0");
//        } else {
//            if (employee.getIsAdmin() == 1) {
//                response.getWriter().write("1");
////                request.getRequestDispatcher("/pages/admin/emplist.html").forward(request, response);
//            } else {
//                response.getWriter().write("2");
////                request.getRequestDispatcher("/pages/index.html").forward(request, response);
//            }
//        }

//        //获取请求体数据
//        BufferedReader br = request.getReader();
//        String params = br.readLine();
//        //将json数据转为Java对象
//        EmployeeServiceImpl employee = JSON.parseObject(params, EmployeeServiceImpl.class);
//        System.out.println(employee);
//        //调用service查询
//        EmployeeServiceImpl u = service.selectByNameAndPassword(employee.getUsername(), employee.getPassword());
//        System.out.println(u);
//        if (u != null) {
//            System.out.println(111);
//            //判断是否为管理员
//            if (employee.getIsAdmin() == 1) {
//                //登录成功，跳转到info界面
//
//            } else {
//
//            }
//        } else {
//
//        }

//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        this.doGet(request, response);
//    }
}
