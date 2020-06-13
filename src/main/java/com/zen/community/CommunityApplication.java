package com.zen.community;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 开启 Mapper 接口 自动扫描
@MapperScan("com.zen.community.mapper")
@SpringBootApplication
public class CommunityApplication {

  public static void main(String[] args) {
    // Spring 通过 ioc容器 来管理 Bean，
    // 只要在 SpringBootApplication 同一级或下一级目录下 带注解的组件都会加载进来
    SpringApplication.run(CommunityApplication.class, args);
  }

}
