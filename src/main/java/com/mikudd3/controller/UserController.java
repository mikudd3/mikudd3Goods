package com.mikudd3.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mikudd3.common.R;
import com.mikudd3.dto.UserWithOrders;
import com.mikudd3.entity.request.UserRequest;
import com.mikudd3.entity.User;
import com.mikudd3.service.UserService;
import com.mikudd3.service.UserWithOrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @project: 用户controller
 * @author: mikudd3
 * @version: 1.0
 */

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserWithOrdersService userWithOrdersService;


    @PostMapping("/login")
    public R login(@RequestBody User user, HttpServletRequest request) {
        return userService.login(user, request);
    }


    @PostMapping("register")
    public R register(@RequestBody UserRequest userRequest, HttpServletRequest request) {
        User user = userRequest.getUser();
        String checkCode = userRequest.getCheckCode();
        //调用service方法
        return userService.register(user, checkCode, request);
    }


    @PostMapping("/forgetPassword")
    public R forgetPassword(@RequestBody UserRequest userRequest, HttpServletRequest request) {
        User user = userRequest.getUser();
        String checkCode = userRequest.getCheckCode();
        //调用service方法
        return userService.forgetPassword(user, checkCode, request);
    }

    @PostMapping("/page")
    public R page(@RequestBody UserRequest userRequest) {
        log.info(userRequest.toString());
        //获取user
        User user = userRequest.getUser();
        //获取当前页
        Integer currentPage = userRequest.getCurrentPage();
        //获取每页展示数量
        Integer pageSize = userRequest.getPageSize();
        //调用方法
        return userService.getPage(user, currentPage, pageSize);
    }

    @PostMapping("/add")
    public R add(@RequestBody User user) {
        userService.save(user);
        return R.success("添加成功");
    }

    @GetMapping("/selectById")
    public R selectById(@RequestParam("id") Long id) {
        User user = userService.getById(id);
        return R.success(user);
    }

    /**
     * 更新信息
     *
     * @param user
     * @return
     */
    @PostMapping("/update")
    public R update(@RequestBody User user) {
        userService.updateById(user);
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
        return userService.delete(id);
    }

    /**
     * 从session中找对象
     *
     * @param request
     * @return
     */
    @GetMapping("/getUser")
    public R getUser(HttpServletRequest request) {
        Long id = (Long) request.getSession().getAttribute("user");
        if (id == null) {
            return R.error("你还没有登录，请登录");
        }
        log.info("所登录的账号的id为：{}", id);
        //根据id查询数据
        User user = userService.getById(id);
        return R.success(user);
    }


    @DeleteMapping("/zhuxiao")
    public R zhuXiao(@RequestParam("id") Long id) {
        userService.removeById(id);
        return R.success("注销成功");
    }

    @PostMapping("/getUserOrders")
    public R getUserOrders(@RequestBody Map<String, Object> map) {
        String username = (String) map.get("username");
        Integer currentPage = (Integer) map.get("currentPage");
        Integer pageSize = (Integer) map.get("pageSize");
        //根据用户名查询订单数量
        Page<UserWithOrders> page = new Page<>(currentPage, pageSize);
        LambdaQueryWrapper<UserWithOrders> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserWithOrders::getUsername, username);
        userWithOrdersService.page(page, wrapper);
        return R.success(page);
    }
}
