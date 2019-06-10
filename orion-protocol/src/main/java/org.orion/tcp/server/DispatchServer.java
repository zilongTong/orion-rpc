package org.orion.tcp.server;//package com.zhangmen.orion.server;
//
//
//import com.zhangmen.common.MonitorInfoBean;
//import com.zhangmen.common.RpcDecoder;
//import com.zhangmen.common.RpcRequest;
//import com.zhangmen.common.VMState;
//import com.zhangmen.orion.register.RegisterCenter;
//import com.zhangmen.orion.server.handler.ReceiveHandler;
//import com.zhangmen.utils.IPUtils;
//import com.zhangmen.utils.MonitorComponent;
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//import io.netty.util.concurrent.Future;
//import io.netty.util.concurrent.GenericFutureListener;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.stereotype.Component;
//
///**
// * @ClassName DispatchServer
// * @Author Leo
// * @Description //TODO
// * @Date: 2019/5/15 19:11
// **/
//@Slf4j
//@Component
//public class DispatchServer implements ApplicationContextAware, InitializingBean {
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        publish();
//    }
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//
//    }
//
//    public void publish() {
//        EventLoopGroup bossGroup = new NioEventLoopGroup();
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//        ServerBootstrap bootstrap = new ServerBootstrap();
//        try {
//            RegisterCenter.register(VMState.COLD, "0", IPUtils.getIpAddress());
//            bootstrap.group(bossGroup, workerGroup)
//                    .channel(NioServerSocketChannel.class)
//                    .childHandler(new ChannelInitializer<SocketChannel>() {
//                        @Override
//                        protected void initChannel(SocketChannel socketChannel) throws Exception {
//                            socketChannel.pipeline().addLast(new RpcDecoder(RpcRequest.class));
//                            socketChannel.pipeline().addLast(new ReceiveHandler());
//                        }
//                    });
//            String ip = IPUtils.getIpAddress();
//            ChannelFuture future = bootstrap.bind(ip, 8080).sync();
//            log.info("服务启动成功，等待客户端链接");
//            //ctx.close()后执行，然后关闭服务，理论上只有异常才会执行下面
//            future.addListener(new GenericFutureListener<Future<? super Void>>() {
//                @Override
//                public void operationComplete(Future<? super Void> future) throws Exception {
//                    System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
//                }
//            });
////            future.channel().closeFuture().sync();
//            log.info("服务端关闭--------------");
//        } catch (Exception e) {
//            log.error("服务启动失败", e);
//        } finally {
//            log.info("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
//            bossGroup.shutdownGracefully();
//            workerGroup.shutdownGracefully();
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
//        MonitorInfoBean monitorInfo = MonitorComponent.getMonitorInfoBean();
//        System.out.println("cpu占有率=" + monitorInfo.getCpuRatio());
//
//        System.out.println("可使用内存=" + monitorInfo.getTotalMemory());
//        System.out.println("剩余内存=" + monitorInfo.getFreeMemory());
//        System.out.println("最大可使用内存=" + monitorInfo.getMaxMemory());
//
//        System.out.println("操作系统=" + monitorInfo.getOsName());
//        System.out.println("总的物理内存=" + monitorInfo.getTotalMemorySize() + "kb");
//        System.out.println("剩余的物理内存=" + monitorInfo.getFreeMemory() + "kb");
//        System.out.println("已使用的物理内存=" + monitorInfo.getUsedMemory() + "kb");
//        System.out.println("线程总数=" + monitorInfo.getTotalThread() + "kb");
//    }
//}
