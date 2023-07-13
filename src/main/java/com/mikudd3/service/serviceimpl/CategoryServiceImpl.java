package com.mikudd3.service.serviceimpl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mikudd3.common.R;
import com.mikudd3.entity.Category;
import com.mikudd3.mapper.CategoryMapper;
import com.mikudd3.service.CategoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Override
    public R getPage(String name, Integer currentPage, Integer pageSize) {
        Page<Category> page = new Page<>(currentPage, pageSize);
        //创建等值条件
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        //构造等值条件
        wrapper.like(StringUtils.isNotEmpty(name), Category::getName, name);
        this.page(page, wrapper);
        return R.success(page);
    }
}
