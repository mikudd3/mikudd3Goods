package com.mikudd3.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("orders")
public class Orders {
    private Long id;
    @TableField(fill = FieldFill.INSERT)//插入时填充
    private String numbers;
    //商品id
    private Long goodsId;
    //库存代码
    @TableField("stockCode")
    private String stockCode;
    //用户id
    private Long userId;
    @TableField(fill = FieldFill.INSERT, value = "createTime")//插入时填充
    private LocalDateTime createTime;
}
