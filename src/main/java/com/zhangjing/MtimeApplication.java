package com.zhangjing;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAutoConfiguration
@ComponentScan
@MapperScan(basePackages = "com.zhangjing.dao")
@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
public class MtimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MtimeApplication.class, args);
	}

}
