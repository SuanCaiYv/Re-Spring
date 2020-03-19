package com.learn.webmvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author SuanCaiYv
 * @time 2020/3/15 下午1:21
 */
@Configuration
@ComponentScan(basePackages = {"com.learn.webmvc"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
public class RootConfig
{
}
