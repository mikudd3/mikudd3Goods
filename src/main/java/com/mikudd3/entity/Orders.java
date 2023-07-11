package com.mikudd3.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @project: 订单
 * @author: mikudd3
 * @version: 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    private Long id;
    private Long numbers;
    private String goodsId;
    private String userId;
    @TableField(fill = FieldFill.INSERT)//插入时填充
    private LocalDateTime createTime;
}
