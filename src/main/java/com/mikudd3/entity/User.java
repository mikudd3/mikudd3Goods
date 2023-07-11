package com.mikudd3.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @project: 用户
 * @author: mikudd3
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    //用户id
    private Integer id;
    //用户名
    private String username;
    //密码
    private String password;
    //性别 1，男性，0 女性
    private Integer sex;
    //电话号码
    private String phone;
    //身份证
    private String sfz;
    //邮箱
    private String email;
    //头像地址
    private String image;
    //账号状态
    private Integer status;
    @TableField(fill = FieldFill.INSERT)//插入时填充
    //注册时间
    private LocalDateTime createTime;
}