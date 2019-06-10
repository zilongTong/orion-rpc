package org.orion.tcp.server;//package com.zhangmen.orion.server.handler;
//
//
//import com.zhangmen.common.RpcRequest;
//import com.zhangmen.common.VMState;
//import com.zhangmen.orion.register.RegisterCenter;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.ChannelHandlerContext;
//
//import io.netty.channel.SimpleChannelInboundHandler;
//import io.netty.util.CharsetUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * <一句话功能简述>
// * <功能详细描述>
// *
// * @author :仓颉
// * @see: [相关类/方法]（可选）
// * @since [产品/模块版本] （可选）
// */
//@Slf4j
//public class ReceiveHandler extends SimpleChannelInboundHandler<RpcRequest> {
//
//    private Map<String, Object> handlerMap = new HashMap();
//
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, RpcRequest rpcRequest) throws Exception {
//        System.out.println("ReceiveHandler-----" + rpcRequest.toString());
//        System.out.println("1111111111" + rpcRequest.getClassName());
//        Object result = new Object();
//        ctx.writeAndFlush(Unpooled.copiedBuffer(result.toString(), CharsetUtil.UTF_8));
//    }
//
//
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        System.out.println(cause.getMessage());
//        ctx.close();
//    }
//}
