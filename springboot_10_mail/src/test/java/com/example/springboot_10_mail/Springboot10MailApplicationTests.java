package com.example.springboot_10_mail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.springboot_10_mail.service.SendMailService;

@SpringBootTest
class Springboot10MailApplicationTests {

	@Autowired
	private SendMailService sendMailService;

	@Test
	void contextLoads() {
		sendMailService.sendMail();
	}

}
