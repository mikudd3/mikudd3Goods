package com.mikudd3.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@Controller
@RequestMapping("/checkCode")
public class CheckCodeController {

    @GetMapping("/code")
    public void makeCode(HttpServletRequest request, HttpServletResponse response) {
        // 创建一个验证码  长，宽，字符数，干扰元素个数
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(100, 50, 4, 10);
        // 将验证码放到session中以供前端获取展示
        String code = lineCaptcha.getCode();//真实验证码
        request.getSession().setAttribute("checkCodeGen", code);
        // 用流写出去
        try {
            lineCaptcha.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
