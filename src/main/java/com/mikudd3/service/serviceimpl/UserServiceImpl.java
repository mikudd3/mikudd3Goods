package com.mikudd3.service.serviceimpl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.injector.methods.SelectById;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mikudd3.common.R;
import com.mikudd3.entity.User;
import com.mikudd3.mapper.UserMapper;
import com.mikudd3.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @Override
    public R login(User user, HttpServletRequest request) {
        //1.根据输入用户的姓名查询数据库
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        User u = this.getOne(queryWrapper);

        //如果查询到的用户为空，则表示该用户不存在
        if (u == null) {
            //该用户不存在
            return R.error("用户不存在");
        }

        //如果用户存在,比对密码
        if (!u.getPassword().equals(user.getPassword())) {
            //如果密码不正确
            return R.error("密码不正确");
        }
        //如果密码正确，则将用户的id放置到session域中
        request.getSession().setAttribute("user", user.getId());
        return R.success(user);
    }

    /**
     * 注册
     *
     * @param user
     * @param checkCode
     * @param request
     * @return
     */
    @Override
    public R register(User user, String checkCode, HttpServletRequest request) {
        //log.info("user对象为：", user);
        //1.获取session域中的验证码
        String codeGen = (String) request.getSession().getAttribute("checkCodeGen");
        //2.进行验证码比对
        if (!codeGen.equalsIgnoreCase(checkCode)) {
            //验证码错误，输出错误信息
            return R.error("验证码输入错误");
        }
        //3.先使用用户名查询数据库
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        //等值条件
        wrapper.eq(User::getUsername, user.getUsername());
        //进行查询
        User u = this.getOne(wrapper);
        //判断u是否为空，为空则用户已存在
        if (u != null) {
            return R.error("用户已存在");
        }
        //用户不存在在创建用户,将用户保存到数据库中
        this.save(user);
        return R.success(user);
    }

    /**
     * 忘记密码逻辑
     *
     * @param user
     * @param checkCode
     * @param request
     * @return
     */
    @Override
    public R forgetPassword(User user, String checkCode, HttpServletRequest request) {
        //1.获取session域中的验证码
        String codeGen = (String) request.getSession().getAttribute("checkCodeGen");
        //2.进行验证码比对
        if (!codeGen.equalsIgnoreCase(checkCode)) {
            //验证码错误，输出错误信息
            return R.error("验证码输入错误");
        }

        //3.使用用户名查询数据库
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        //等值条件
        wrapper.eq(User::getUsername, user.getUsername());
        //进行查询
        User u = this.getOne(wrapper);

        //判断用户是否为空
        if (u == null) {
            return R.error("用户不存在，请先注册");
        }

        //比对密码，是否与原密码一致
        if (u.getPassword().equals(user.getPassword())) {
            return R.error("新密码不能与原密码一致");
        }

        //更新信息
        user.setId(u.getId());
        this.updateById(user);

        return R.success(user);
    }

    /**
     * 分页查询
     *
     * @param user
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public R getPage(User user, Integer currentPage, Integer pageSize) {
//        log.info(user.toString());
        //创建pageBean对象
        Page<User> page = new Page<>(currentPage, pageSize);
        //创建构造器
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        //创建等值条件
        if (user != null) {
            wrapper.like(StringUtils.isNotEmpty(user.getUsername()), User::getUsername, user.getUsername())
                    .like(StringUtils.isNotEmpty(user.getPhone()), User::getPhone, user.getPhone())
                    .like(StringUtils.isNotEmpty(user.getEmail()), User::getEmail, user.getEmail());
        }
        //进行查询
        this.page(page, wrapper);

        return R.success(page);
    }

    @Override
    public R add(User user) {
        //1.根据用户名查询用户
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        //创建等值条件
        wrapper.eq(User::getUsername, user.getUsername());
        //进行查询
        User u = this.getOne(wrapper);
        if (u != null) {
            return R.error("该用户已存在");
        }
        //用户不存在则添加进数据库
        this.save(user);
        return R.success(user);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @Override
    public R delete(Long id) {
        //根据id查询用户
        User u = this.getById(id);
        //判断用户是否被禁用
        if (u.getStatus() == 0) {
            return R.error("删除失败，请先禁用用户");
        }

        //如果用户为禁用用户，则将其删除
        this.removeById(id);
        return R.success("删除成功");
    }


}
