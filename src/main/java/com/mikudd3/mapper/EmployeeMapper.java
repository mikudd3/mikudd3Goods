package com.mikudd3.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mikudd3.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
