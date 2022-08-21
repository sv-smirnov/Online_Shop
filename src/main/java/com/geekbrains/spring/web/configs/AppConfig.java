package com.geekbrains.spring.web.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@EnableAspectJAutoProxy
@PropertySource("secrets.properties")
public class AppConfig {
}
