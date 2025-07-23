package org.moviefusion;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieFusionAPI { // class name changed
	private static boolean swaggerOpened = true;
	public static void main(String[] args) {
		
		if(swaggerOpened) 
		{
			SpringApplication.run(MovieFusionAPI.class, args);
//			SwaggerConfigs.openSwaggerUI();
			swaggerOpened = false;
		} 
	}
} 
  