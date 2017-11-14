package com.medidonate;

import org.hibernate.usertype.UserCollectionType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.medidonate.controller.UserController;


@SpringBootApplication
@ComponentScan(basePackages="com.medidonate")
@EnableJpaRepositories(basePackages = {"com.medidonate"})
@EntityScan(basePackages = {"com.medidonate"})
@ComponentScan(basePackages = {"com.medidonate"}, basePackageClasses = UserController.class)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
