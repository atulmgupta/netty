package com.atulmgupta.netty.client.codec;

import com.atulmgupta.netty.LoopBackTimeStamp;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import java.util.List;

public class TimeStampEncoder extends MessageToByteEncoder<LoopBackTimeStamp> {
    @Override
    protected void encode(ChannelHandlerContext ctx, LoopBackTimeStamp msg, ByteBuf out) throws Exception {
        out.writeBytes(msg.toByteArray());
    }
}
