package com.example.demo.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Cat {
	private Integer id;
	private String catName;
	private Integer age;
	
	
}



