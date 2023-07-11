package com.mikudd3.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mikudd3.common.R;
import com.mikudd3.entity.Employee;
import com.mikudd3.mapper.EmployeeMapper;
import com.mikudd3.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    /**
     * 登录方法
     *
     * @param employee
     * @return
     */
    @Override
    public R login(HttpServletRequest request, Employee employee) {
        //1.根据用户名查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        //创建等值条件
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        //进行查询
        Employee emp = this.getOne(queryWrapper);

        //2如果用户不存在
        if (emp == null) {
            return R.error("用户不存在");
        }

        //3.如果用户存在，则比对密码
        if (!employee.getPassword().equals(emp.getPassword())) {
            //如果密码错误
            return R.error("密码错误");
        }

        //4.密码正确，则将用户放到session域中
        request.getSession().setAttribute("emp", emp);
        return R.success(emp);
    }
}
