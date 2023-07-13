package com.mikudd3.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: 轮播图
 * @author: mikudd3
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("lunbaotu")
public class LunBaoTu {

    private Long id;
    private String url;
    private String title;
    private String color;
}
