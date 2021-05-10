package com.ml.blog.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Mr.ml
 * @date 2021/1/16
 */
@Component
public class SendMail {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送验证码
     * @param email
     * @return
     */
    public String sendEmailCode(String email) {
        SimpleMailMessage msg = new SimpleMailMessage();
        // 获取随机验证码
        String resultCode = EmailCodeUtils.achieveCode();

        // Eamil 的标题
        msg.setSubject("小站给您送上验证码");
        // Eamil 的内容
        msg.setText("欢迎注册小站，您本次操作的验证码为:" + resultCode + '\n' + "请您签收");
        // 发送者 Email
        msg.setFrom("1518195329@qq.com");
        // 发送日期
        msg.setSentDate(new Date());
        // 接收者 Email
        msg.setTo(email);
        // 发送
        javaMailSender.send(msg);

        return resultCode;
    }

}
