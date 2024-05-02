package com.aka.ams;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aka.ams.controllers.ArticleController;



@SpringBootApplication
public class AmsDataApplication {

	public static void main(String[] args) {
		new File(ArticleController.uploadDirectory).mkdir();
		
		SpringApplication.run(AmsDataApplication.class, args);
		System.out.println("توكلت على الله");
		

	}

}
