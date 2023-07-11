package com.mikudd3.service.serviceimpl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mikudd3.entity.Stock;
import com.mikudd3.mapper.StockMapper;
import com.mikudd3.service.StockService;
import org.springframework.stereotype.Service;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {
}
