package com.atulmgupta.netty.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

@Component
public class NettyServer {
	private static Logger logger = LoggerFactory.getLogger(NettyServer.class);

	private  final int HTTP_PORT = 9001;

	public void start() throws Exception {
		NioEventLoopGroup bossGroup = new NioEventLoopGroup();
		NioEventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap bootstrap = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup);
			bootstrap.channel(NioServerSocketChannel.class)
					.childHandler(new ServerInitializer())
					.option(ChannelOption.SO_BACKLOG, 6000)
					.childOption(ChannelOption.SO_KEEPALIVE, true);

			ChannelFuture httpChannel = bootstrap.bind(HTTP_PORT).sync();
			logger.info("netty server on port: {}", HTTP_PORT);
			httpChannel.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}

		
	}
}
