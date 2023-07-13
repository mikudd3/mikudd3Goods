package com.mikudd3.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mikudd3.common.R;
import com.mikudd3.dto.GoodsDto;
import com.mikudd3.entity.Goods;
import com.mikudd3.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/getHot")
    public R getHot() {
        Page<Goods> goodsPage = new Page<>(1, 5);
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("total");
        goodsService.page(goodsPage, wrapper);
        return R.success(goodsPage);
    }

    @GetMapping("/getGoodgame")
    public R getGoodgame() {
        Page<Goods> goodsPage = new Page<>(1, 5);
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("pj");
        goodsService.page(goodsPage, wrapper);
        return R.success(goodsPage);
    }

    @GetMapping("/getDazhegame")
    public R getDazhegame() {
        Page<Goods> goodsPage = new Page<>(1, 5);
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("currentprice");
        goodsService.page(goodsPage, wrapper);
        return R.success(goodsPage);
    }

    @GetMapping("/getHotlist")
    public R getHotList() {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("total");
        List<Goods> list = goodsService.list(wrapper);
        return R.success(list);
    }

    @GetMapping("/getGoodgamelist")
    public R getGoodgameList() {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("pj");
        List<Goods> list = goodsService.list(wrapper);
        return R.success(list);
    }

    @GetMapping("/getDazhegamelist")
    public R getDazhegameList() {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("currentprice");
        List<Goods> list = goodsService.list(wrapper);
        return R.success(list);
    }

}
