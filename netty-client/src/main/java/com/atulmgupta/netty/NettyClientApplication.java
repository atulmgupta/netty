package com.atulmgupta.netty;

import com.atulmgupta.netty.client.NettyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class NettyClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(NettyClientApplication.class, args);
	}

	@Autowired
	private NettyClient nettyClient;

	@EventListener(ApplicationReadyEvent.class)
	public void start() throws InterruptedException {
		nettyClient.start();
	}

}
