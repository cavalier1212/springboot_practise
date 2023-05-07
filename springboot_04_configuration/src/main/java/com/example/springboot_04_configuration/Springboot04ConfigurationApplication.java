package com.example.springboot_04_configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.springboot_04_configuration.config.ServerConfig;

@SpringBootApplication
// @EnableConfigurationProperties({ServerConfig.class})
// @EnableConfigurationProperties 會把 ServerConfig 實體化 ，會與ServerConfig @Component 衝突，不能實體化兩個Bean
// 使用@EnableConfigurationProperties 比較好統一管理Bean
public class Springboot04ConfigurationApplication {

	@Bean
	@ConfigurationProperties(prefix = "datasource")
	public DruidDataSource dataSource(){
		DruidDataSource ds = new DruidDataSource();
		return ds;
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Springboot04ConfigurationApplication.class, args);
		System.out.println(ctx.getBean(ServerConfig.class));
		System.out.println(ctx.getBean(DruidDataSource.class).getDriverClassName());
	}

}
