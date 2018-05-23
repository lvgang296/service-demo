package com.springboot.manager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@PropertySource("classpath:dubbo/dubbo.properties")
@ImportResource("classpath:dubbo/dubbo.xml")
public class DubboConfig {
}
