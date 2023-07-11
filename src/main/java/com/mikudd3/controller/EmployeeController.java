package com.mikudd3.controller;

import com.mikudd3.common.R;
import com.mikudd3.entity.Employee;
import com.mikudd3.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */

@Slf4j
@RestController
@RequestMapping("/admin")
//@EnableWebMvc
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     *
     * @param employee
     * @return
     */
//    @PostMapping("/login")
    @PostMapping("/login")
    public R login(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("进入登录");
        return employeeService.login(request,employee);
    }


}
