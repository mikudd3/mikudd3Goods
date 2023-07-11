package com.mikudd3.service.serviceimpl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mikudd3.entity.Orders;
import com.mikudd3.mapper.OrdersMapper;
import com.mikudd3.service.OrdersService;
import org.springframework.stereotype.Service;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
}
