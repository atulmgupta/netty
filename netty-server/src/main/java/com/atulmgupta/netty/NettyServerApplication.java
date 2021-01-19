package com.atulmgupta.netty;

import com.atulmgupta.netty.server.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class NettyServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NettyServerApplication.class, args);
    }

    @Autowired
    private NettyServer nettyServer;

    @EventListener(ApplicationReadyEvent.class)
    public void start() throws Exception {
        nettyServer.start();
    }

}
