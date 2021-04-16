package com.collectplatform.project;

import com.collectplatform.project.property.FileProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;




@EnableConfigurationProperties({
		FileProperties.class
})
@SpringBootApplication
@MapperScan("com.collectplatform.project.dao")
//@EnableFeignClients(basePackages = {"com.collectplatform.core.common.feign"})
//@EnableDiscoveryClient
public class ProjectApplication {

	public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
	}

}