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
