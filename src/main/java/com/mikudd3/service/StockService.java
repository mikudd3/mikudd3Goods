package com.mikudd3.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mikudd3.common.R;
import com.mikudd3.dto.StockDto;
import com.mikudd3.entity.Stock;

public interface StockService extends IService<Stock> {

    /**
     * 分页查询
     *
     * @param goodsName
     * @param currentPage
     * @param pageSize
     * @return
     */
    R getPage(String goodsName, Integer currentPage, Integer pageSize);

    /**
     * 添加库存
     *
     * @param stockDto
     * @return
     */
    R saveWithGoods(StockDto stockDto);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    R selectDto(Long id);

    /**
     * 更新信息
     *
     * @param stockDto
     * @return
     */
    R updatewithGoods(StockDto stockDto);

}
