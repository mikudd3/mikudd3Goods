package com.mikudd3.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mikudd3.common.R;
import com.mikudd3.entity.Category;

public interface CategoryService extends IService<Category> {
    /**
     * 分页查询
     *
     * @param name
     * @param currentPage
     * @param pageSize
     * @return
     */
    R getPage(String name, Integer currentPage, Integer pageSize);
}
