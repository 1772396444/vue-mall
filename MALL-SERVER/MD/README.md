# server
### 本项目基于JDK 1.8，严格准守阿里 codding 规范 (Alibaba Java Coding Guide)
### POST请求携带Body 请求头请设置为 application/json
### Swagger 准则，请求参数将定义为 FROM 实体，回传参数将遵循阿里 DTO 规范

### MD 存放数据结构，本应使用思维导图处理，但是受限，这里更新规范 txt 文件

### 技术选型
+-------------------------------------------------------------+
| Spring Cloud Alibaba          基于阿里微服务框架                |
| Nacos                         sca官方服务治理中心              |
+-------------------------------------------------------------+

### 模块配置
+-----------------------------------------------------------------------------+
| mall-entity                      实体类                                      |
| mall-repository                 数据库操作(DAO)                              |
| mall-common                      公共的拦截器定义以及一些统一的类配置信息初始化      |
| mall-gateway                     服务网关                                     |
+-----------------------------------------------------------------------------+

### 微服务基于模块化拆分，所以 mall-entity mall-repository mall-config 每个微服务都会引用到
### 项目打包需使用 maven-compiler-plugin 插件，打包命令 mvn package -Dmaven.test.skip=true