package com.mikudd3.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ordersDto")
public class OrdersDto {
    //订单主键
    private Long id;
    //订单号
    private String numbers;
    //商品名字
    @TableField("goodsName")
    private String goodsName;
    //cdk
    @TableField("stockCode")
    private String stockCode;
    //用户名
    @TableField("userName")
    private String userName;
    @TableField("createTime")
    private LocalDateTime createTime;
}
