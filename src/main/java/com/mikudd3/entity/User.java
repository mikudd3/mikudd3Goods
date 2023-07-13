package com.mikudd3.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("user")
public class User {

    //用户id
    private Long id;
    //用户名
    private String username;
    //密码
    private String password;
    private String sex;
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
    @TableField(fill = FieldFill.INSERT,value = "createTime")//插入时填充
    //注册时间
    private LocalDateTime createTime;
}
