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
        //1.创建等值条件,先根据输入的商品名查询商品的信息
        List<Goods> goods = null;
        if (StringUtils.isNotEmpty(goodsName)) {
            LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
            wrapper.like(Goods::getName, goodsName);
            goods = goodsService.list(wrapper);
        }
        //2.根据查询到的商品，获取商品的id
        List<Long> ids = null;
        if (goods != null) {
            for (Goods good : goods) {
                ids.add(good.getId());
            }
        }
        //3.根据获取到的id构造查询条件
        LambdaQueryWrapper<Stock> queryWrapper = new LambdaQueryWrapper<>();
        if (ids != null) {
            queryWrapper.in(Stock::getGoodsId, ids);
        }
        //进行分页查询
        Page<Stock> page = new Page<>(currentPage, pageSize);
        this.page(page, queryWrapper);
        //将stock分页后数据放入到stockDto中
        List<Stock> records = page.getRecords();
        //将page里面的内容拷贝到stockDtoPage，除了records属性
        BeanUtils.copyProperties(page, stockDtoPage, "records");
        List<StockDto> stockDtoList = new ArrayList<>();

        for (Stock stock : records) {
            LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Goods::getId, stock.getGoodsId());
            Goods one = goodsService.getOne(wrapper);
            StockDto stockDto = new StockDto(stock.getId(), one.getName(), stock.getCode());
            stockDtoList.add(stockDto);
        }
        //将集合放入到stockDtoPage中
        stockDtoPage.setRecords(stockDtoList);

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
        return null;
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
}
