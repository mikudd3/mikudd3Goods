package com.mikudd3.dto;

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
@TableName("userwithorders")
public class UserWithOrders {

    //订单号
    private String numbers;
    //用户名
    @TableField("username")
    private String username;
    //电话
    private String phone;
    //商品名
    @TableField("goodsName")
    private String goodsName;
    //cdk
    private String code;
    //创建时间
    @TableField("createTime")
    private LocalDateTime createTime;
}
