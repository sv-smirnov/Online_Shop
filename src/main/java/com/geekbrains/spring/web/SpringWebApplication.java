package com.geekbrains.spring.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringWebApplication {
	// Домашнее задание:
	// Вспомнить\разобраться с кодом
	// Реализовать методы Cart, которые мы не реализовали на уроке

	public static void main(String[] args) {
		SpringApplication.run(SpringWebApplication.class, args);
	}
}
