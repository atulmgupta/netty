package com.atulmgupta.netty.handler;

import com.atulmgupta.netty.model.RequestData;
import com.atulmgupta.netty.model.ResponseData;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {
    private static final String TAG = "ClientHandler";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        for (int i =0; i<5000;i++ ) {
            RequestData msg = new RequestData();
            log.info("Message sent for {} ", i++);
            msg.setIntValue(i);
            msg.setStringValue(
                    "all work and no play makes jack a dull boy");
            ChannelFuture future = ctx.writeAndFlush(msg);
        }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println((ResponseData) msg);
        ctx.close();
    }

}
