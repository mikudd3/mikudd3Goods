package com.mikudd3.controller;

import com.mikudd3.common.R;
import com.mikudd3.entity.Category;
import com.mikudd3.entity.Employee;
import com.mikudd3.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */

@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/page")
    public R page(@RequestBody Map<String, Object> request) {
        //获取当前页
        Integer currentPage = (Integer) request.get("currentPage");
        //获取当前页大小
        Integer pageSize = (Integer) request.get("pageSize");
        //获取查询条件
        String name = (String) request.get("name");
        return categoryService.getPage(name, currentPage, pageSize);
    }


    @PostMapping("/add")
    public R add(@RequestBody Category category) {
        log.info("添加员工");
        categoryService.save(category);
        return R.success("添加成功");
    }

    @GetMapping("/selectById")
    public R selectById(@RequestParam("id") Long id) {
        Category category = categoryService.getById(id);
        return R.success(category);
    }

    /**
     * 更新信息
     *
     * @param category
     * @return
     */
    @PostMapping("/update")
    public R update(@RequestBody Category category) {
        categoryService.updateById(category);
        return R.success("更新成功");
    }


    /**
     * 删除
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public R delete(@RequestParam("id") Long id) {
        categoryService.removeById(id);
        return R.success("删除成功");
    }
}
