package com.mikudd3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: cdk的dto
 * @author: mikudd3
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockDto {

    //cdk订单
    private Long id;

    //商品名字
    private String goodsName;


    //cdk
    private String cdk;

}
