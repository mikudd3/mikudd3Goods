package com.mikudd3.service.serviceimpl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mikudd3.entity.User;
import com.mikudd3.mapper.UserMapper;
import com.mikudd3.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
