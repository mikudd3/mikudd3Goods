package com.mikudd3.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mikudd3.common.R;
import com.mikudd3.dto.GoodsDto;
import com.mikudd3.entity.Goods;

public interface GoodsService extends IService<Goods> {

    /**
     * 分页查询
     *
     * @param name
     * @param currentPage
     * @param pageSize
     * @return
     */
    R getPage(String name, Integer currentPage, Integer pageSize);

    /**
     * 新增
     * @param goodsDto
     * @return
     */
    R saveWithCategory(GoodsDto goodsDto);

    /**
     * 根据id返回dto
     * @param id
     * @return
     */
    R select(Long id);

    /**
     * 更新数据
     * @param goodsDto
     * @return
     */
    R updatewithCategory(GoodsDto goodsDto);

}
