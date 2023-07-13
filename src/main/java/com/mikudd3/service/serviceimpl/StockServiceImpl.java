package com.mikudd3.service.serviceimpl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mikudd3.common.R;
import com.mikudd3.dto.StockDto;
import com.mikudd3.entity.Goods;
import com.mikudd3.entity.Stock;
import com.mikudd3.mapper.StockMapper;
import com.mikudd3.service.GoodsService;
import com.mikudd3.service.StockDtoService;
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
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private StockDtoService stockDtoService;

    /**
     * 分页查询
     *
     * @param goodsName
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public R getPage(String goodsName, Integer currentPage, Integer pageSize) {
        Page<StockDto> stockDtoPage = new Page<>(currentPage, pageSize);
        LambdaQueryWrapper<StockDto> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotEmpty(goodsName), StockDto::getGoodsName, goodsName);
        stockDtoService.page(stockDtoPage, wrapper);
        return R.success(stockDtoPage);
    }

    @Override
    public R saveWithGoods(StockDto stockDto) {
        //根据商品名查询相关商品
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Goods::getName, stockDto.getGoodsName());
        //进行查询
        Goods goods = goodsService.getOne(wrapper);
        //如果商品不存在
        if (goods == null) {
            return R.error("该商品不存在，请先添加商品");
        }
        //如果商品存在，则将其添加到数据库
        Stock stock = new Stock();
        stock.setCode(stockDto.getCdk());
        stock.setGoodsId(goods.getId());
        //添加到数据库
        this.save(stock);
        //商品数量加一
        Integer num = goods.getNumber() + 1;
        goods.setNumber(num);
        goodsService.save(goods);
        return R.success("添加成功");
    }

    @Override
    public R selectDto(Long id) {
        //根据id查询库存数据
        Stock stock = this.getById(id);
        //根据商品id查询商品名字
        Goods goods = goodsService.getById(stock.getGoodsId());
        //将其合并到dto中
        StockDto stockDto = new StockDto(stock.getId(), goods.getName(), stock.getCode());
        return R.success(stockDto);
    }


    @Override
    public R updatewithGoods(StockDto stockDto) {
        //根据商品姓名查询商品的id
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Goods::getName, stockDto.getGoodsName());
        Goods goods = goodsService.getOne(wrapper);
        //判断商品是否存在
        if (goods == null) {
            //该商品不存在，无法修改
            return R.error("商品不存在，无法修改");
        }
        //创建一个stock对象
        Stock stock = new Stock(stockDto.getId(), goods.getId(), stockDto.getCdk());
        //更新数据库信息
        this.updateById(stock);
        return R.success("修改成功");
    }

    @Override
    public R removeWithGoods(Long id) {
        //根据id查询库存
        Stock stock = this.getById(id);
        //根据goods_id查询goods
        Goods goods = goodsService.getById(stock.getGoodsId());
        //商品数量减一
        Integer number = goods.getNumber();
        goods.setNumber(number - 1);
        //保存商品
        goodsService.save(goods);
        //删除库存
        this.removeById(id);
        return null;
    }
}
