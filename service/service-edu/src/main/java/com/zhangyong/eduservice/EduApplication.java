package com.zhangyong.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 17616
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.zhangyong"})
public class EduApplication {

	public static void main(String[] args) {
		SpringApplication.run(EduApplication.class, args);
	}

}