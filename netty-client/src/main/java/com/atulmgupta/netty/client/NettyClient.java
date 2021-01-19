package com.atulmgupta.netty.client;

import java.util.concurrent.ExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atulmgupta.netty.decoder.ResponseDataDecoder;
import com.atulmgupta.netty.encoder.RequestDataEncoder;
import com.atulmgupta.netty.handler.ClientHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.stereotype.Component;

@Component
public class NettyClient {
    private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);
    private boolean isRunning = false;
    public static final String SERVER_IP = "localhost";
    static int port = 9001;
    private ExecutorService executor = null;

    public void start() throws InterruptedException {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);

            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel) throws Exception {

                    channel.pipeline().addLast(new RequestDataEncoder(), new ResponseDataDecoder(),
                            new ClientHandler());

                }
            });
            ChannelFuture f = b.connect(SERVER_IP, port).sync();

            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }

    }
}
