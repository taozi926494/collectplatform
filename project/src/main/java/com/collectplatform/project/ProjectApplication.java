package com.collectplatform.project;

import com.collectplatform.project.property.ScriptFileProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;





@EnableConfigurationProperties({
		ScriptFileProperties.class
})
@SpringBootApplication
@MapperScan("com.collectplatform.project.dao")

@ComponentScan(basePackages = {"com.collectplatform.project", "com.collectplatform.core.common"})
public class ProjectApplication {

	public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
	}

}