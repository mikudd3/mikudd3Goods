package com.mikudd3.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mikudd3.dto.OrdersDto;
import com.mikudd3.mapper.OrdersDtoMapper;
import com.mikudd3.service.OrdersDtoService;
import org.springframework.stereotype.Service;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@Service
public class OrdersDtoServiceImpl extends ServiceImpl<OrdersDtoMapper, OrdersDto> implements OrdersDtoService {
}
