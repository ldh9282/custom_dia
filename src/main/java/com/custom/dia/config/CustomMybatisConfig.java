package com.custom.dia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.custom.dia.cmmn.interceptor.CustomSqlLoggingInterceptor;

@Configuration
public class CustomMybatisConfig {

	@Bean
	public CustomSqlLoggingInterceptor customSqlLoggingInterceptor() {
		return new CustomSqlLoggingInterceptor();
	}
	
}
