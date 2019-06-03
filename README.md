# orion-rpc
## 掌门教育自研微服务框架，借鉴springCLoud全家桶的微服务设计思想

#### 支持可插拔的客户端服务端注解

#### 支持三种负载设计：
    1.@ZmTarget注解定向负载 
    2.客户端自定义负载策略 balanceStrategy,继承统一的接口规范(这个用来做灰度soeasy,缺点就是开发者需要关注注册中心，不符合中间件屏蔽底层细节的初衷)
    3.默认的负载均衡策略：
			随机算法
			轮询算法  
			一致性hash算法，支持可配置虚拟节点数   

#### 支持可插拔的注册中心
 		 						
			orion-register-zk-starter   	   	cp 		
			orion-register-etcd-starter   		cp		
			orion-register-nacos-starter   		ap	
			orion-register-eureka-starter   	ap		
                           

#### 支持自定义熔断策略     //TODO

#### 支持可插拔的HTTP编码器和解码器

#### 支持HTTP请求和响应的压缩

#### 支持链路请求和响应的监控    //TODO

#### 支持多重网络通信协议
			http
				支持okhttp
				apacheClient  //TODO
			tcp   
				支持客户端socket长连接（本地缓存socket，实现socket的LRU算法）
				支持protostuff二进制序列化 编解码
				支持字节码沾包拆包
				支持空闲策略的心跳
				支持服务降级mock，服务隔离，服务治理 //TODO
