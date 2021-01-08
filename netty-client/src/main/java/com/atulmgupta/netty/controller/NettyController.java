package com.atulmgupta.netty.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/netty/")
public class NettyController {

	private static final Logger log = LoggerFactory.getLogger(NettyController.class);

	@GetMapping(value = "/test")
	public String testMethod() {
		log.info("test method called @ "+System.currentTimeMillis());
		return "Netty Service is up @ " + System.currentTimeMillis();
	}

}
