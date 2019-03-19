package com.cvinicisudr.service;

import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
@EnableHystrix
public class HystrixService {

	@HystrixCommand(fallbackMethod = "getFallbackMessage", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000") })
	public String getMessage() {
		return this.getSuperSlowMessage();
	}

	private String getSuperSlowMessage() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "slow return";
	}

	private String getFallbackMessage() {
		return "fallback message";
	}

}
