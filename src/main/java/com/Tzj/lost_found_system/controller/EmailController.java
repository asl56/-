package com.Tzj.lost_found_system.controller;

import com.Tzj.lost_found_system.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@Slf4j
@RestController
public class EmailController {

    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/email")
    public Result commonEmail(String email) {

        //创建简单邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        //谁发的
        message.setFrom(from);
        //谁要接收
        message.setTo(email);
        //邮件标题
        message.setSubject("验证码");
        //生成验证码
        StringBuilder verificationCode = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            int number = random.nextInt(10);
            verificationCode.append(number);
        }
        //设置邮件内容
        message.setText(verificationCode.toString());
        try {
            mailSender.send(message);
            return Result.success(verificationCode.toString());
        } catch (MailException e) {
            e.printStackTrace();
            return Result.error("error");
        }
    }
}
