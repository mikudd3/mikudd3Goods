package com.mikudd3.controller;

import com.mikudd3.common.R;
import com.mikudd3.dto.GoodsDto;
import com.mikudd3.service.GoodsService;
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
@RequestMapping("goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;


    @PostMapping("/page")
    public R page(@RequestBody Map<String, Object> map) {
        Integer currentPage = (Integer) map.get("currentPage");
        Integer pageSize = (Integer) map.get("pageSize");
        String name = (String) map.get("name");
        //调用方法
        return goodsService.getPage(name, currentPage, pageSize);
    }

    @PostMapping("/add")
    public R add(@RequestBody GoodsDto goodsDto) {
        return goodsService.saveWithCategory(goodsDto);
    }

    @GetMapping("/selectById")
    public R selectById(@RequestParam("id") Long id) {
        return goodsService.select(id);
    }

    /**
     * 更新信息
     *
     * @param goodsDto
     * @return
     */
    @PostMapping("/update")
    public R update(@RequestBody GoodsDto goodsDto) {
        return goodsService.updatewithCategory(goodsDto);
    }


    /**
     * 删除
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public R delete(@RequestParam("id") Long id) {
        goodsService.removeById(id);
        return R.success("删除成功");
    }
}
