package com.mikudd3.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mikudd3.dto.GoodsDto;
import com.mikudd3.mapper.GoodsDtoMapper;
import com.mikudd3.service.GoodsDtoService;
import org.springframework.stereotype.Service;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@Service
public class GoodsDtoServiceImpl extends ServiceImpl<GoodsDtoMapper, GoodsDto> implements GoodsDtoService {
}
