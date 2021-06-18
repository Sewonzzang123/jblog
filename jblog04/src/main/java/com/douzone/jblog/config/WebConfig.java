package com.douzone.jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.douzone.config.web.FileUploadConfig;
import com.douzone.config.web.MessageConfig;
import com.douzone.config.web.MvcConfig;
import com.douzone.config.web.SecurityConfig;

@Configuration
@EnableAspectJAutoProxy //<aop:aspectj-autoproxy />
@ComponentScan({"com.douzone.jblog.controller, com.douzone.jblog.exception"})
@Import({FileUploadConfig.class, MessageConfig.class, MvcConfig.class, SecurityConfig.class})
public class WebConfig {

}
