package com.mikudd3.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mikudd3.common.R;
import com.mikudd3.entity.Employee;

import javax.servlet.http.HttpServletRequest;

public interface EmployeeService extends IService<Employee> {
    //登录
    R login(HttpServletRequest request, Employee employee);

    //分页条件查询
    R<Page> getPage(int currentPage, int pageSize, String name);

    //添加员工
    R add(Employee employee);

    //删除
    R delete(Long id);
}
