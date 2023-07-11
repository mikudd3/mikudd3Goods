package com.mikudd3.service.serviceimpl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mikudd3.entity.Category;
import com.mikudd3.mapper.CategoryMapper;
import com.mikudd3.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
