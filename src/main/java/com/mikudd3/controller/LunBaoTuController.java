package com.mikudd3.controller;

import com.mikudd3.common.R;
import com.mikudd3.entity.LunBaoTu;
import com.mikudd3.service.LunBaoTuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@RestController
@RequestMapping("/lbt")
public class LunBaoTuController {

    @Autowired
    private LunBaoTuService lunBaoTuService;

    @PostMapping("/page")
    public R getPage(@RequestBody Map<String, Object> map) {
        //获取当前页
        Integer currentPage = (Integer) map.get("currentPage");
        //获取当前页大小
        Integer pageSize = (Integer) map.get("pageSize");
        return lunBaoTuService.getpage(currentPage, pageSize);
    }


    @GetMapping("/selectById")
    public R selectById(@RequestParam("id") Long id) {
        LunBaoTu lunBaoTu = lunBaoTuService.getById(id);
        return R.success(lunBaoTu);
    }

    @PutMapping("/update")
    public R update(@RequestBody LunBaoTu lunBaoTu) {
        lunBaoTuService.updateById(lunBaoTu);
        return R.success("修改成功");
    }

    @GetMapping("/getAll")
    public R getAll() {
        List<LunBaoTu> list = lunBaoTuService.list();
        return R.success(list);
    }
}
