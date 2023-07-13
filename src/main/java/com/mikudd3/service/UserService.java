package com.mikudd3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mikudd3.common.R;
import com.mikudd3.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService extends IService<User> {

    /**
     * 登录
     *
     * @param user
     * @return
     */
    R login(User user, HttpServletRequest request);

    /**
     * 注册方法
     *
     * @param user
     * @param checkCode
     * @param request
     * @return
     */
    R register(User user, String checkCode, HttpServletRequest request);

    /**
     * 忘记密码功能
     *
     * @param user
     * @param checkCode
     * @param request
     * @return
     */
    R forgetPassword(User user, String checkCode, HttpServletRequest request);

    /**
     * 分页查询
     * @param user
     * @param currentPage
     * @param pageSize
     * @return
     */
    R getPage(User user, Integer currentPage, Integer pageSize);

    /**
     * 添加用户
     * @param user
     * @return
     */
    R add(User user);

    /**
     * 删除用户
     * @param id
     * @return
     */
    R delete(Long id);

}
