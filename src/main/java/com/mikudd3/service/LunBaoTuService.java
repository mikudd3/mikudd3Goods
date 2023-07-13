package com.mikudd3.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mikudd3.common.R;
import com.mikudd3.entity.LunBaoTu;

public interface LunBaoTuService extends IService<LunBaoTu> {
    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    R getpage(Integer currentPage, Integer pageSize);
}
