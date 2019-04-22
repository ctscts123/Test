package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Dog;
import com.example.demo.pojo.User;

@RestController
public class TestController {
	@Autowired
	private User user;
	@Autowired
	private Dog dog;

	@RequestMapping("/hello")
	public String hello(){
		return "你好！ springboot hello";
	}
	
	@RequestMapping("/getUser")
	public String getUser() {
		
		return user.toString();
	}
	
	@RequestMapping("/getDog")
	public String getDog() {
		return dog.toString();
	}
	
}













