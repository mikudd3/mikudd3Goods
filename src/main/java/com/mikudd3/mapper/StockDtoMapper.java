package com.mikudd3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mikudd3.dto.StockDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockDtoMapper extends BaseMapper<StockDto> {
}
