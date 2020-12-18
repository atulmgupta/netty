package com.atulmgupta.netty;

import com.atulmgupta.netty.client.codec.TimeStampDecoder;
import com.atulmgupta.netty.client.codec.TimeStampEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyServer {
    private static Logger logger = LoggerFactory.getLogger(NettyServer.class);

    private static final int HTTP_PORT = 19000;

    public static void main(String[] args) throws Exception {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boosGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);

        // ===========================================================
        // 1. define a separate thread pool to execute handlers with
        //    slow business logic. e.g database operation
        // ===========================================================
        final EventExecutorGroup group = new DefaultEventExecutorGroup(1500); //thread pool of 1500

        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("idleStateHandler", new IdleStateHandler(0, 0, 5)); // add with name
                pipeline.addLast(new TimeStampEncoder()); // add without name, name auto generated
                pipeline.addLast(new TimeStampDecoder()); // add without name, name auto generated

                //===========================================================
                // 2. run handler with slow business logic
                //    in separate thread from I/O thread
                //===========================================================
                pipeline.addLast(group, "serverHandler", new ServerHandler());
            }
        });

        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.bind(HTTP_PORT).sync();
        logger.info("netty server on port: {}", HTTP_PORT);
    }
}
