package com.atulmgupta.netty.handler;

import com.atulmgupta.netty.model.RequestData;
import com.atulmgupta.netty.model.ResponseData;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProcessingHandler extends ChannelInboundHandlerAdapter {
	private ByteBuf tmp;

	@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) 
      throws Exception {

        RequestData requestData = (RequestData) msg;
        log.info("Request message Recieved {} ",requestData.getIntValue());
        ResponseData responseData = new ResponseData();
        responseData.setIntValue(requestData.getIntValue() * 2);
        ChannelFuture future = ctx.writeAndFlush(responseData);
        System.out.println(requestData.getIntValue());
    }
}
