package org.moviefusion.swagger;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Movie Fusion API",
        version = "1.0",
        description = "API documentation for MovieFusion Application"
    )
)
public class SwaggerConfigs {

	public static void openSwaggerUI() {
		
			String url = "http://localhost:8080/swagger-ui.html";
			
	        String os = System.getProperty("os.name").toLowerCase();
	        try {
	            if (os.contains("win")) { 
	                new ProcessBuilder("cmd", "/c", "start chrome " + url).start();	    
	            }
	           
	        } catch (IOException e) {   
	            e.printStackTrace();
	        }			
	}
}
