# 网约车Bug

   1、OpenFeign远程调用GET方法的接口时，如果将参数放到请求体中，会自动转成POST方法。

##### Api-passenger

###### remote

```java
//这里在调用接口的时候 将参数放到了请求体中 OpenFeign会直接封装成POST方法
//使得在服务无法被调用
@RequestMapping(method=RequestMethod.GET,value="/user/")
ResponseResult getUserByPhone(@RequestBody VerificationDTO verificationDTO)
```

##### passenger-user-service

###### controller

```java
//这里一开始使用的是将请求放到请求体中
@GetMapping("/user/")
public ResponseResult getUserByPhone(@RequestBody VerificationDTO verificationDTO ）
```

这里就会导致调用不到接口 更改方法是Get方法把参数放到URL 不要放到请求体中。

```java
@GetMapping("/user/{phone}")
public ResponseResult getUserByPhone(@PathVariable("phone") String phone)
```

或者在pom中引入 version=10.10.1

```java
  <dependency>
            <groupId>io.github.openfeign</groupId>
            <artifactId>feign-httpclient</artifactId>
        </dependency>
```

### Spring boot 的Transactional

这个Transactional不能回滚事务过程中redis的操作 但是可以回滚mysql的

### 并发问题

#### 1、订单服务如果遇到并发问题 需要加sychronized锁

但是只能解决单机问题 如果在集群中 锁只能锁jvm级别的 所以在集群中无法满足一致性

N个集群就可能会导致N个同步数据

##### 解决1：用redis的setnx

SET if Not Exists：

引入redisson依赖

```java
<dependency>
    <groupId>org.redisson</groupId>
    <artifactId>redisson</artifactId>
    <version>3.17.7</version>
</dependency>
```

![](C:\Users\19855\AppData\Roaming\msbmarkdown\images\2023-04-11-22-02-08-image.png)

但是出现了死锁

##### 解决2：用seata
