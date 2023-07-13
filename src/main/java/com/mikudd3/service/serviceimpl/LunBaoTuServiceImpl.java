package com.mikudd3.service.serviceimpl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mikudd3.common.R;
import com.mikudd3.entity.LunBaoTu;
import com.mikudd3.mapper.LunBaoTuMapper;
import com.mikudd3.service.LunBaoTuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@Service
public class LunBaoTuServiceImpl extends ServiceImpl<LunBaoTuMapper, LunBaoTu> implements LunBaoTuService {

    @Override
    public R getpage(Integer currentPage, Integer pageSize) {
        Page<LunBaoTu> lunBaoTuPage = new Page<>(currentPage, pageSize);
        this.page(lunBaoTuPage);
        return R.success(lunBaoTuPage);
    }
}
