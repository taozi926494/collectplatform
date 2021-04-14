package com.collectplatform.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xuxueli 2018-10-28 00:38:13
 */
@SpringBootApplication
@MapperScan("com.collectplatform.project.dao")
public class ProjectApplication {

	public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
	}

}