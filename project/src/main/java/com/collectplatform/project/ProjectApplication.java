package com.collectplatform.project;

import com.collectplatform.project.property.FileProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
=======
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
>>>>>>> 1dec0cd3c619c97f647bbf21fc0e564304835922




@EnableConfigurationProperties({
		FileProperties.class
})
@SpringBootApplication
@MapperScan("com.collectplatform.project.dao")
<<<<<<< HEAD
//@EnableFeignClients(basePackages = {"com.collectplatform.core.common.feign"})
//@EnableDiscoveryClient
=======
@ComponentScan(basePackages = {"com.collectplatform.project", "com.collectplatform.core.common"})
>>>>>>> 1dec0cd3c619c97f647bbf21fc0e564304835922
public class ProjectApplication {

	public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
	}

}