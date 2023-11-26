package com.example.springboot_10_mail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.springboot_10_mail.service.SendMailService;

import jakarta.mail.internet.MimeMessage;

@Service
public class SendMailServiceImpl implements SendMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    String from = "[from]@gmail.com";
    String to = "[to]@gmail.com";
    String subject = "標題測試";
    String text = "<img src='https://p2.bahamut.com.tw/B/2KU/62/c5f3dd027c2903f595b3315ad21ngha5.JPG'/><a href='https://www.youtube.com/'>點我<a>";

    @Override
    public void sendMail() {
        // SimpleMailMessage message = new SimpleMailMessage();
        // message.setFrom(from);
        // message.setTo(to);
        // message.setSubject(subject);
        // message.setText(text);
        // javaMailSender.send(message);
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            // MimeMessageHelper 第二個參數 true 表示支援多個附件，false 表示不支援多個附件，預設為 false
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            // 第二個參數 true 表示支援 HTML，false 表示不支援 HTML，預設為 false
            helper.setText(text,true);

            ClassPathResource fileResource1 = new ClassPathResource("image/pic1.jpeg");
            ClassPathResource fileResource2 = new ClassPathResource("image/pic2.png");
            helper.addAttachment(fileResource1.getFilename(), fileResource1);
            helper.addAttachment(fileResource2.getFilename(), fileResource2);

            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
