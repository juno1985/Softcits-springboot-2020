package org.softcits.cn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("org.softcits.cn.mapper")
@SpringBootApplication
public class SoftcitsSpringboot2020Application {

	public static void main(String[] args) {
		SpringApplication.run(SoftcitsSpringboot2020Application.class, args);
	}

}
