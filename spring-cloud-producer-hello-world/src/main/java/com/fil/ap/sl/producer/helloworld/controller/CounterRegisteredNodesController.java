package com.fil.ap.sl.producer.helloworld.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("nodes")
public class CounterRegisteredNodesController {

	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
    private Registration registration;
	
	@Autowired
	private DiscoveryClient client;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String checkRegisteredNodes() throws Exception {

		String serviceId = registration.getServiceId();
		
		List<ServiceInstance> list = client.getInstances(serviceId);
		
		String result = "Service ID = " + serviceId + ", No. of Nodes = " + list.size();
		logger.info(result);
		
		for(int i = 0; i < list.size(); i++) {
			
			ServiceInstance instance = list.get(i);
			
			String host = instance.getUri().getHost();
			int port = instance.getUri().getPort();
			String path = instance.getUri().getPath();
			
			String info = "\t Node No. = " + i + ", host = " + host + ", port = " + port + ", path = " + path;
			logger.info(info);
			
			result = result + "\n" + info;
		}
		

		return result;
	}
}