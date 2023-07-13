package com.mikudd3.service.serviceimpl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mikudd3.common.R;
import com.mikudd3.dto.OrdersDto;
import com.mikudd3.entity.Goods;
import com.mikudd3.entity.Orders;
import com.mikudd3.entity.Stock;
import com.mikudd3.entity.User;
import com.mikudd3.mapper.OrdersDtoMapper;
import com.mikudd3.mapper.OrdersMapper;
import com.mikudd3.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private StockService stockService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrdersDtoService ordersDtoService;

    @Override
    public R getPage(String goodsName, Integer currentPage, Integer pageSize) {
        Page<OrdersDto> ordersDtoPage = new Page<>(currentPage, pageSize);
        LambdaQueryWrapper<OrdersDto> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotEmpty(goodsName), OrdersDto::getGoodsName, goodsName);
        ordersDtoService.page(ordersDtoPage, wrapper);
        //6.返回page对象
        return R.success(ordersDtoPage);
    }

    @Override
    public R saveDto(OrdersDto ordersDto) {
        //1.根据goodsName获取goods对象
        LambdaQueryWrapper<Goods> gqw = new LambdaQueryWrapper<>();
        gqw.eq(Goods::getName, ordersDto.getGoodsName());
        //进行查询
        Goods goods = goodsService.getOne(gqw);
        //2.根据goodsId查询stockCode
        LambdaQueryWrapper<Stock> sqw = new LambdaQueryWrapper<>();
        sqw.eq(Stock::getGoodsId, goods.getId());
        //进行查询
        List<Stock> list = stockService.list(sqw);
        Stock stock = list.get(0);
        //3.根据用户名查询用户
        LambdaQueryWrapper<User> uqw = new LambdaQueryWrapper<>();
        uqw.eq(User::getUsername, ordersDto.getUserName());
        //进行查询
        User user = userService.getOne(uqw);
        //4.封装orders对象
        Orders orders = new Orders();
        orders.setStockId(stock.getId());
        orders.setUserId(user.getId());
        //5.保存到数据库
        this.save(orders);
        //删除库存
        stockService.removeById(stock.getId());
        //卖出数量加1
        Long total = goods.getTotal();
        goods.setTotal(total + 1);
        //保存信息
        goodsService.updateById(goods);
        return R.success("添加成功");
    }

}
