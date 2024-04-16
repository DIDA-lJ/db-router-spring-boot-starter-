# db-router-spring-boot-starter
## 简介
开发一个基于 HashMap 核心设计原理，使用哈希散列+扰动函数的方式，把数据散列到多个库表中的组件，并验证使用。
## 更新日志
- 9月22日，新增数据库路由组件开发工程 db-router-spring-boot-starter 这是一个自研的分库分表组件。主要用到的技术点包括：散列算法、数据源切换、AOP切面、SpringBoot Starter 开发等
- 9月22日，完善分库中表信息，user_take_activity、user_take_activity_count、user_strategy_export_001~004，用于测试验证数据库路由组件
- 9月30日，基于Mybatis拦截器对数据库路由分表使用方式进行优化，减少用户在使用过程中需要对数据库语句进行硬编码处理
  
## 数据库路由组件原理
### 数据库路由组件流程图
主要通过借鉴 HashMap 进行哈希散列 + 扰动函数计算的原理，进行对应的路由计算
![image](https://github.com/DIDA-lJ/db-router-spring-boot-starter-/assets/97254796/46f3e97e-9dde-4471-815f-03224e6614da)
### 数据库路由计算原理图
先利用哈希函数进行数据库路由的计算，再有对应的节点映射到对应的库表
![image](https://github.com/DIDA-lJ/db-router-spring-boot-starter-/assets/97254796/1830b538-333a-4e34-90a6-c2766bde2c58)


## 使用方法
### 数据库连接池支持
- 实现分库全局配置
配置mini-db-router.jdbc.datasource.global全局属性,如果db没有这个属性,则取全局,
否则取db中的配置。同时支持嵌套配置，如下pool.maximum-pool-size，若db中有pool,但没有pool.maximum-pool-size也会取全局值。
```
mini-db-router:
  jdbc:
    datasource:
      global:
        type-class-name: com.zaxxer.hikari.HikariDataSource
        pool:
            maximum-pool-size: 50
```
也可以对特定分库进行单独配置,如下:
```
mini-db-router:
  jdbc:
    datasource:
      db00:
        driver-class-name: com.mysql.jdbc.Driver
        url: jdbc:mysql://${basehost}:3306/lottery?useUnicode=true&characterEncoding=utf8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&serverTimezone=UTC&useSSL=true
        username: root
        password: root
        type-class-name: com.alibaba.druid.pool.DruidDataSource
        pool:
          initialSize: 20
          minIdle: 20
          maxActive: 20
          maxWait: 60000
```
- 支持数据库连接池
可以通过global下或者db下的type-class-name指定数据库连接池实现，如Druid、HikariCP,并在pool属性下配置对应连接池的私有属性(无论使用中划线格式的属性或者驼峰都兼容)
