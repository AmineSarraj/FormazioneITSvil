package com.example.spring.zuul.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.example.filters.PreFilter;

@SpringBootApplication
@EnableZuulProxy
public class LibreriaGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibreriaGatewayApplication.class, args);
	}
	@Bean
	  public PreFilter simpleFilter() {
	    return new PreFilter();
	  }

}
