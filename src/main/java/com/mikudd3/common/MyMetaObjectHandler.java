package com.mikudd3.common;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @project: 自定义原对象处理器
 * @author: mikudd3
 * @version: 1.0
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 插入时自动填充
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {

        metaObject.setValue("createTime", LocalDateTime.now());
    }

    /**
     * 更新时自动填充
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
    }
}
