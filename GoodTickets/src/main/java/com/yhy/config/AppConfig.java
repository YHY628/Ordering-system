package com.yhy.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy // 开启自动代理
@ComponentScan(basePackages = {"com.yhy.service"})
public class AppConfig {

}
