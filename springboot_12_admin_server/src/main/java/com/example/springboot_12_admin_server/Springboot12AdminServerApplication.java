package com.example.springboot_12_admin_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
public class Springboot12AdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot12AdminServerApplication.class, args);
	}

}
