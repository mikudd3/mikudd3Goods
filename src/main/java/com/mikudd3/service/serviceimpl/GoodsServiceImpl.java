package com.mikudd3.service.serviceimpl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mikudd3.entity.Goods;
import com.mikudd3.mapper.GoodsMapper;
import com.mikudd3.service.GoodsService;
import org.springframework.stereotype.Service;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@Service

public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
}
