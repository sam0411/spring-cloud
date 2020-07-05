package com.fil.ap.sl.consumer.ribbon.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("ribbon")
public class EurekaRibbonController {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
    private RestTemplate restTemplate;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String invokeByEurekaRibbon() throws Exception {

		String result = restTemplate.getForEntity(
				"http://spring-cloud-provider-hello-world/producer/nodes/register", 
				String.class
			).getBody();
		
		logger.info(result);
		
		return result;
	}
}