package com.mikudd3.controller;

import com.mikudd3.common.R;
import com.mikudd3.dto.OrdersDto;
import com.mikudd3.dto.StockDto;
import com.mikudd3.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@RestController
@Slf4j
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;


    @PostMapping("/page")
    public R page(@RequestBody Map<String, Object> request) {
        //获取当前页
        Integer currentPage = (Integer) request.get("currentPage");
        //获取当前页大小
        Integer pageSize = (Integer) request.get("pageSize");
        //获取查询条件
        String goodsName = (String) request.get("goodsName");
        return ordersService.getPage(goodsName, currentPage, pageSize);
    }

    /**
     * 添加订单
     *
     * @param ordersDto
     * @return
     */
    @PostMapping("/add")
    public R add(@RequestBody OrdersDto ordersDto) {
        return ordersService.saveDto(ordersDto);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public R delete(@RequestParam("id") Long id) {
        ordersService.removeById(id);
        return R.success("删除成功");
    }
}
