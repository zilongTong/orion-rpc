# orion-rpc
掌门教育自研微服务框架
支持可插拔的客户端服务端注解
支持三种负载设计：
@ZmTarget注解定向负载 (这个是项目需要，有被点业务绑架的需求，架构服务业务嘛)
客户端自定义负载策略 balanceStrategy,继承统一的接口规范(这个用来做灰度soeasy,缺点就是开发者需要关注注册中心，不符合中间件屏蔽底层细节的初衷)
默认的负载均衡策略：
随机算法
轮询算法   //TODO
一致性hash算法，支持可配置虚拟节点数   //TODO

zmlearn-register-zk-starter   	   cp 	高	
zmlearn-register-redis-starter   	ap? (这玩意也能做注册中心?我这里只是上报节点状态，严格意义不算)  	高	
zmlearn-register-etcd-starter   	cp	高	TODO
zmlearn-register-nacos-starter   	ap	也没用过咱也不敢瞎说	TODO
zmlearn-register-eureka-starter   	ap	低	TODO
                           

支持自定义熔断策略（正常、关闭、半开？大佬们有没有更好的设计？）     //TODO
支持可插拔的HTTP编码器和解码器（应用层协议的编解码很简单，不像传输层）
支持HTTP请求和响应的压缩
支持链路请求和响应的监控    //TODO
支持多重网络通信协议
http
支持okhttp
apacheClient  //TODO
tcp   //复杂度较高，项目中已注释
支持客户端socket长连接（本地缓存socket，实现socket的LRU算法）
支持protostuff二进制序列化 编解码
支持字节码沾包拆包
支持空闲策略的心跳
支持服务降级mock，服务隔离，服务治理 //TODO
