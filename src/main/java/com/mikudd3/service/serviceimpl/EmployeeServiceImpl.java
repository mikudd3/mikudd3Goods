package com.mikudd3.service.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mikudd3.common.R;
import com.mikudd3.entity.Employee;
import com.mikudd3.mapper.EmployeeMapper;
import com.mikudd3.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */

@Slf4j
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

    /**
     * 分页查询
     *
     * @param currentPage
     * @param pageSize
     * @param name
     * @return
     */
    @Override
    public R<Page> getPage(int currentPage, int pageSize, String name) {
        //1.创建page对象
        Page<Employee> page = new Page<>(currentPage, pageSize);
        //创建等值条件
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        //构造等值条件
        queryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);
        //进行查询
        this.page(page, queryWrapper);
        return R.success(page);
    }

    /**
     * 添加员工
     *
     * @param employee
     * @return
     */
    @Override
    public R add(Employee employee) {
        //1.先判断该用户是否已经存在
        //1.1根据用户名查询用户是否已经存在
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = this.getOne(wrapper);

        //2.如果用户存在
        if (emp != null) {
            //输出错误信息
            return R.error("该用户已经存在");
        }

        //3.用户不存在则将数据添加到数据库
        this.save(employee);
        return R.success(employee);
    }

    @Override
    public R delete(Long id) {
        //1.根据id查询用户
        Employee emp = this.getById(id);
        //2.判断员工是否被禁用
        if (emp.getStatus() == 0) {
            return R.error("删除失败，请先禁用员工");
        }
        //如果员工已经被禁用，则删除员工
        this.removeById(id);
        return R.success("删除成功");
    }
}
