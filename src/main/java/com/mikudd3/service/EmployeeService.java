package com.mikudd3.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mikudd3.common.R;
import com.mikudd3.entity.Employee;

import javax.servlet.http.HttpServletRequest;

public interface EmployeeService extends IService<Employee> {
    //登录
    R login(HttpServletRequest request, Employee employee);
}
