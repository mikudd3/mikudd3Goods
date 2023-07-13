package com.mikudd3.service.serviceimpl;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mikudd3.common.R;
import com.mikudd3.dto.GoodsDto;
import com.mikudd3.entity.Category;
import com.mikudd3.entity.Goods;
import com.mikudd3.entity.Stock;
import com.mikudd3.mapper.GoodsMapper;
import com.mikudd3.service.CategoryService;
import com.mikudd3.service.GoodsDtoService;
import com.mikudd3.service.GoodsService;
import com.mikudd3.service.StockService;
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

public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private GoodsDtoService goodsDtoService;

    /**
     * 分页查询
     *
     * @param name
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public R getPage(String name, Integer currentPage, Integer pageSize) {
        Page<GoodsDto> goodsDtoPage = new Page<>(currentPage, pageSize);
        LambdaQueryWrapper<GoodsDto> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotEmpty(name), GoodsDto::getName, name);
        goodsDtoService.page(goodsDtoPage, wrapper);
        //返回page对象
        return R.success(goodsDtoPage);
    }

    /**
     * 添加商品
     *
     * @param goodsDto
     * @return
     */
    @Override
    public R saveWithCategory(GoodsDto goodsDto) {
        //1.根据分类名获取分类id
        Goods goods = getGoods(goodsDto);
        //报错到数据库
        this.save(goods);
        return R.success(goods);
    }

    /**
     * 根据id查询商品信息
     *
     * @param id
     * @return
     */
    @Override
    public R select(Long id) {
        Goods goods = this.getById(id);
        //根据分类id查询商品名字
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getId, goods.getCategoryId());
        Category category = categoryService.getOne(wrapper);
        GoodsDto goodsDto = getGoodsDto(goods, category);
        return R.success(goodsDto);
    }


    @Override
    public R updatewithCategory(GoodsDto goodsDto) {
        //1.根据分类名获取分类id
        Goods goods = getGoods(goodsDto);
        this.updateById(goods);
        return R.success(goods);
    }

    /**
     * 封装goods对象
     *
     * @param goodsDto
     * @return
     */
    private Goods getGoods(GoodsDto goodsDto) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getName, goodsDto.getCategoryName());
        Category category = categoryService.getOne(wrapper);
        //2.创建goods对象
        Goods goods = new Goods();
        goods.setId(goodsDto.getId());
        goods.setName(goodsDto.getName());
        goods.setPrice(goodsDto.getPrice());
        goods.setCurrentPrice(goodsDto.getCurrentPrice());
        goods.setImage(goodsDto.getImage());
        goods.setCategoryId(category.getId());
        goods.setDescription(goodsDto.getDescription());
        goods.setNumber(goodsDto.getNumber());
        goods.setPj(goodsDto.getPj());
        goods.setTotal(goodsDto.getTotal());
        return goods;
    }


    /**
     * 封装GoodsDto对象
     *
     * @param goods
     * @param category
     * @return
     */
    private GoodsDto getGoodsDto(Goods goods, Category category) {
        GoodsDto goodsDto = new GoodsDto();
        goodsDto.setId(goods.getId());
        goodsDto.setName(goods.getName());
        goodsDto.setPrice(goods.getPrice());
        goodsDto.setCurrentPrice(goods.getCurrentPrice());
        goodsDto.setImage(goods.getImage());
        goodsDto.setDescription(goods.getDescription());
        goodsDto.setCategoryName(category.getName());
        goodsDto.setNumber(goods.getNumber());
        goodsDto.setPj(goods.getPj());
        goodsDto.setTotal(goods.getTotal());
        return goodsDto;
    }

}
