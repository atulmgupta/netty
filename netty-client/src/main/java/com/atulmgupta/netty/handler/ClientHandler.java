package com.atulmgupta.netty.handler;

import com.atulmgupta.netty.model.RequestData;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.UUID;

@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {
    private static final String TAG = "ClientHandler";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        for (int i =1; i<50000;i++ ) {
            MDC.put("peid", UUID.randomUUID().toString());
            RequestData msg = new RequestData();
            log.info("Message sent for {} ", i++);
            msg.setIntValue(i);
            msg.setStringValue(
                    "all work and no play makes jack a dull boy");
            log.info("Message Post Start @ {}",System.currentTimeMillis());
            ChannelFuture future = ctx.writeAndFlush(msg);
            log.info("Message Post Completed @ {}",System.currentTimeMillis());
        }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.close();
    }

}
