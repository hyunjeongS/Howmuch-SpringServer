package com.example.project.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Result {
	   private String name;
	   private String username;
	   private String phonenumber;
	   private double width;
	   private double height;
	   private String cost;
}
