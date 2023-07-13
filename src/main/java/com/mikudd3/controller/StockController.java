package com.mikudd3.controller;

import com.mikudd3.common.R;
import com.mikudd3.dto.StockDto;
import com.mikudd3.entity.Category;
import com.mikudd3.service.StockService;
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
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/page")
    public R page(@RequestBody Map<String, Object> request) {
        //获取当前页
        Integer currentPage = (Integer) request.get("currentPage");
        //获取当前页大小
        Integer pageSize = (Integer) request.get("pageSize");
        //获取查询条件
        String goodsName = (String) request.get("goodsName");
        return stockService.getPage(goodsName, currentPage, pageSize);
    }


    @PostMapping("/add")
    public R add(@RequestBody StockDto stockDto) {
        log.info("添加员工");
        return stockService.saveWithGoods(stockDto);
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @GetMapping("/selectById")
    public R selectById(@RequestParam("id") Long id) {
        return stockService.selectDto(id);
    }

    /**
     * 更新信息
     *
     * @param stockDto
     * @return
     */
    @PostMapping("/update")
    public R update(@RequestBody StockDto stockDto) {
        return stockService.updatewithGoods(stockDto);
    }


    /**
     * 删除
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public R delete(@RequestParam("id") Long id) {
        stockService.removeById(id);
        return R.success("删除成功");
    }

}
