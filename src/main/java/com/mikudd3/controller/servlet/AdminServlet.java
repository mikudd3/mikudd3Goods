package com.mikudd3.controller.servlet;

import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/admin/*")
public class AdminServlet extends HttpServlet {


//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String uri = req.getRequestURI();
//        if (uri.endsWith("selectByPageAndCondition")) {
//            System.out.println("selectByPageAndCondition");
//            this.selectByPageAndCondition(req, resp);
//        } else if (uri.endsWith("delete")) {
//            System.out.println("delete");
//            this.delete(req, resp);
//        } else if (uri.endsWith("add")) {
//            System.out.println("add");
//            this.add(req, resp);
//        } else if (uri.endsWith("update")) {
//            System.out.println("update");
//            this.update(req, resp);
//        } else if (uri.endsWith("selectById")) {
//            System.out.println("selectById");
//            this.selectById(req, resp);
//        }
//    }
//
//    private UserService service = new UserServiceImpl();
//
//    /**
//     * 查询全部
//     *
//     * @param request
//     * @param response
//     * @throws ServletException
//     * @throws IOException
//     */
//    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("进入分页查询");
//        //接收参数 当前页码，展示数据
//        String currPage = request.getParameter("currentPage");
//        System.out.println(currPage);
//        String pageSize = request.getParameter("pageSize");
//        System.out.println(pageSize);
//        // 获取请求体数据
//        BufferedReader br = request.getReader();
//        String params = br.readLine();
//        // 将JSON字符串转为Java对象
//        EmployeeServiceImpl employee = JSON.parseObject(params, EmployeeServiceImpl.class);
//        System.out.println(employee);
//        //查询数据
//        PageBean<EmployeeServiceImpl> pageBean = service.selectByPage(Integer.parseInt(currPage), Integer.parseInt(pageSize), employee.getUsername());
//        //2. 将集合转换为JSON数据   序列化
//        String jsonString = JSON.toJSONString(pageBean);
//        System.out.println(jsonString);
//        //测试
//        System.out.println(1);
//        //3. 响应数据
//        response.setContentType("text/json;charset=utf-8");
//        response.getWriter().write(jsonString);
//    }
//
//    /**
//     * 删除
//     *
//     * @param request
//     * @param response
//     * @throws ServletException
//     * @throws IOException
//     */
//    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //获取id
//        String id = request.getParameter("id");
//        System.out.println(id);
//
//        //执行删除
//        service.deleteById(Integer.valueOf(id));
//    }
//
//
//    /**
//     * 添加
//     *
//     * @param request
//     * @param response
//     * @throws ServletException
//     * @throws IOException
//     */
//    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //获取数据
//        BufferedReader br = request.getReader();
//        String prams = br.readLine();
//        //获取对象
//        EmployeeServiceImpl employee = JSON.parseObject(prams, EmployeeServiceImpl.class);
//        System.out.println(employee);
//
//        //先根据用户判断用户名是否已经存在
//        if (service.selectByName(employee.getUsername()) != null) {
//            response.getWriter().write("userHadExist");
//            return;
//        }
//
//        // 如果用户不存在，则添加到数据库
//        service.add(employee);
//        response.getWriter().write("success");
//
//    }
//
//    /**
//     * 修改
//     *
//     * @param request
//     * @param response
//     * @throws ServletException
//     * @throws IOException
//     */
//    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //获取数据
//        BufferedReader br = request.getReader();
//        String prams = br.readLine();
//        //获取对象
//        EmployeeServiceImpl employee = JSON.parseObject(prams, EmployeeServiceImpl.class);
//        System.out.println(employee);
//
//        service.update(employee);
//
//    }
//
//    /**
//     * 根据员工查询数据
//     *
//     * @param request
//     * @param response
//     * @throws ServletException
//     * @throws IOException
//     */
//    public void selectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //获取id
//        String id = request.getParameter("id");
//        System.out.println(id);
//
//        //根据id返回对象信息
//        EmployeeServiceImpl employee = service.selectById(Integer.valueOf(id));
//        System.out.println(employee);
//
//        //2. 将集合转换为JSON数据   序列化
//        String jsonString = JSON.toJSONString(employee);
//        System.out.println(jsonString);
//        //测试
//        System.out.println(1);
//        //3. 响应数据
//        response.setContentType("text/json;charset=utf-8");
//        response.getWriter().write(jsonString);
//
//
//    }
}
