package com.fkczxt.selclassserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fkczxt.selclassserver.mapper")
public class SelclassApplication {

	public static void main(String[] args) {
		SpringApplication.run(SelclassApplication.class, args);
	}

}
