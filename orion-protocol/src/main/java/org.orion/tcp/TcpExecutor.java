package org.orion.tcp;

import lombok.extern.slf4j.Slf4j;
import org.orion.core.RpcRequest;
import org.orion.okhttp.LoadBalanceTemplateAdaptor;

/**
 * @ClassName TcpExecutor
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/5/22 10:55
 **/
@Slf4j
public class TcpExecutor extends LoadBalanceTemplateAdaptor<RpcRequest> {


//    @Override
//    public String loadBalance(RpcRequest request) {
//        return super.loadBalance(request);
//    }
//
//    @Override
//    public String call(RpcRequest request, String ip) {
//        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
//        try {
////            String ip = discovery.discovery(VMState.IDLE);
//            int port = 8080;
//            Bootstrap b = new Bootstrap();
//            b.group(eventLoopGroup)
//                    .channel(NioSocketChannel.class)
//                    .remoteAddress(new InetSocketAddress("", port))
//                    .handler(new ChannelInitializer<SocketChannel>() {
//                        @Override
//                        protected void initChannel(SocketChannel socketChannel) throws Exception {
//                            socketChannel.pipeline().addLast(new RpcEncoder(RpcRequest.class)) // 将 RPC 请求进行编码（为了发送请求）
//                                    .addLast(new RpcDecoder(RpcResponse.class)) // 将 RPC 响应进行解码（为了处理响应）
//                                    .addLast(new ProxyHandler());
//                        }
//                    });
//            ChannelFuture future = b.connect().sync();
////                    byte[] byteBuff = SerializeProtoStuff.serialize(RpcRequest.class, request);
////                    future.channel().writeAndFlush(Unpooled.copiedBuffer(byteBuff));
//            future.channel().writeAndFlush(request.getParameters());
//
//            //主线程应用程序会一直等待，直到channel关闭
//            future.channel().closeFuture().sync();
//        } catch (Exception e) {
//            System.out.println(e.getStackTrace());
//        }
//        return null;
//    }

}
