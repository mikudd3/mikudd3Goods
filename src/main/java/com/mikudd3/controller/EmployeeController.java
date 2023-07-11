package com.mikudd3.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mikudd3.common.R;
import com.mikudd3.entity.Employee;
import com.mikudd3.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

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
        return employeeService.login(request, employee);
    }


    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @PostMapping("/page")
    public R<Page> page(@RequestBody Map<String, Object> request) {
        //获取当前页
        Integer currentPage = (Integer) request.get("currentPage");
        //获取当前页大小
        Integer pageSize = (Integer) request.get("pageSize");
        //获取查询条件
        String name = (String) request.get("name");
        return employeeService.getPage(currentPage, pageSize, name);
    }


    /**
     * 添加员工
     *
     * @param employee
     * @return
     */
    @PostMapping("/add")
    public R add(@RequestBody Employee employee) {
        log.info("添加员工");
        return employeeService.add(employee);
    }

    /**
     * 根据id查询员工
     *
     * @param id
     * @return
     */
    @GetMapping("/selectById")
    public R selectById(@RequestParam("id") Long id) {
        Employee emp = employeeService.getById(id);
        return R.success(emp);
    }

    /**
     * 更新信息
     *
     * @param employee
     * @return
     */
    @PostMapping("/update")
    public R update(@RequestBody Employee employee) {
        employeeService.updateById(employee);
        return R.success("更新成功");
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public R delete(@RequestParam("id") Long id) {
        return employeeService.delete(id);
    }

}
