package com.cvinicisudr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cvinicisudr.service.HystrixService;
import com.netflix.discovery.EurekaClient;

@RequestMapping("/hello")
@RestController
public class HelloController {
	
	@Value("${spring.application.name}")
    private String appName;
	
    @Lazy
    @Autowired
    private EurekaClient eurekaClient;
	
	
    @Autowired
	private HystrixService hystrixService;
	
	@GetMapping
	public String hello() {
        return hystrixService.getMessage();
    }

}
