package com.mikudd3.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mikudd3.dto.StockDto;
import com.mikudd3.mapper.StockDtoMapper;
import com.mikudd3.service.StockDtoService;
import com.mikudd3.service.StockService;
import org.springframework.stereotype.Service;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@Service
public class StockDtoServiceImpl extends ServiceImpl<StockDtoMapper, StockDto> implements StockDtoService {
}
