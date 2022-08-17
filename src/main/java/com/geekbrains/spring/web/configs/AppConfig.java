package com.geekbrains.spring.web.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySource("secrets.properties")
public class AppConfig {
}
