package com.mikudd3.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mikudd3.common.R;
import com.mikudd3.dto.OrdersDto;
import com.mikudd3.entity.Orders;

public interface OrdersService extends IService<Orders> {

    //分页查询
    R getPage(String goodsName, Integer currentPage, Integer pageSize);

    //添加订单
    R saveDto(OrdersDto ordersDto);
}
