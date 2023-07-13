package com.mikudd3.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mikudd3.dto.UserWithOrders;
import com.mikudd3.mapper.UserWithOrdersMapper;
import com.mikudd3.service.UserWithOrdersService;
import org.springframework.stereotype.Service;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@Service
public class UserWithOrdersServiceImpl extends ServiceImpl<UserWithOrdersMapper, UserWithOrders> implements UserWithOrdersService {
}
