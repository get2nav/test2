package com.demo.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//// @ComponentScan("com.demo.main")
//// or change the package to com.demo
// @ComponentScan(basePackageClasses = HelloController.class)
//// @ComponentScan(basePackages = "com.demo.controller")
public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(com.demo.main.App.class, args);

	}

}
