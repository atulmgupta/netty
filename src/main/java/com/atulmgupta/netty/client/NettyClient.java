package com.atulmgupta.netty.client;

import com.atulmgupta.netty.client.codec.TimeStampDecoder;
import com.atulmgupta.netty.client.codec.TimeStampEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {

    public static final String SERVER_IP = "localhost";

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(workerGroup);
        b.channel(NioSocketChannel.class);

        b.handler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel channel) throws Exception {
                channel.pipeline().addLast(new TimeStampEncoder(), new TimeStampDecoder(), new ClientHandler());
            }
        });
        b.connect(SERVER_IP, 19000);

    }
}
