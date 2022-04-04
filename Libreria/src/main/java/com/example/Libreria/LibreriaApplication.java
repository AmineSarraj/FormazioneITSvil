package com.example.Libreria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({  
	@PropertySource(value= "file:${catalina.home}/conf/configurazioni/easybook/application.properties")
})
@EnableEurekaClient
public class LibreriaApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(LibreriaApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LibreriaApplication.class);
	}

}
