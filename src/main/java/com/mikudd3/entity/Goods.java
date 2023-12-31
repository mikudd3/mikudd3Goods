package com.mikudd3.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: 商品
 * @author: mikudd3
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("goods")
public class Goods {
    //主键
    private Long id;
    //商品名字
    private String name;
    //价格
    private Double price;
    //现在价格
    @TableField(value = "currentPrice")
    private Double currentPrice;
    //图片
    private String image;
    //商品描述
    private String description;
    //类别id
    private Long categoryId;
    //库存数量
    private Integer number;
    //评价
    private Double pj;
    //卖出数量
    private Long total;
}
