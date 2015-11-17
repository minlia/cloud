# minlia cloud

Just another open source micro-service modern style architecture and solid software framework
A spring full stack based system architecture, powered by spring boot, spring cloud,spring data, spring data jpa etc.




# 敏捷、便利、优雅 minlia cloud

一个为微服务架构而生的，敏捷、便利、优雅的基础架构及框架。
基于spring全栈式架构。由spring boot、spring cloud、spring data、spring data jpa等前沿技术强力驱动。

如何运行

启动顺序

infrastructure 基础架构部分

1. 先启动discovery-server服务发现
2. 再启动config-server中央配置服务
3. 再启动gateway-server集成网关服务


```
cd ./infrastructure
gradle build -x test

cd discovery-server
gradle bootRun -x test
cd ..

cd config-server
gradle bootRun -x test
cd ..

cd config-server
gradle bootRun -x test
cd ..

cd gateway-server
gradle bootRun -x test
cd ..

```

solution应用部分

直接启动usercenter应用

```
cd solution/usercenter
gradle bootRun -x test

```

