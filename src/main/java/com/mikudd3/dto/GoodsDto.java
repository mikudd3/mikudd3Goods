package com.mikudd3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsDto {

    //主键
    private Long id;
    //商品名字
    private String name;
    //价格
    private Double price;
    //售价
    private Double currentPrice;
    //图片
    private String image;
    //商品描述
    private String description;
    //类别名
    private String categoryName;
    //库存数量
    private Integer number;
    //评价
    private Double pj;
    //卖出数量
    private Long total;
}
