package com.atulmgupta.netty.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atulmgupta.netty.decoder.RequestDecoder;
import com.atulmgupta.netty.encoder.ResponseDataEncoder;
import com.atulmgupta.netty.handler.ProcessingHandler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

public class ServerInitializer extends ChannelInitializer<Channel> {
	private static final Logger logger = LoggerFactory.getLogger(ServerInitializer.class);
	@Override
	protected void initChannel(Channel ch) throws Exception {
		logger.info("Initializing Pipeline");
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new RequestDecoder());
		pipeline.addLast(new ResponseDataEncoder());
		pipeline.addLast(new ProcessingHandler());
	}

}
