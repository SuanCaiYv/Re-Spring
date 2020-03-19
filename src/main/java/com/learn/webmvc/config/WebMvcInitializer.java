package com.learn.webmvc.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author SuanCaiYv
 * @time 2020/3/15 下午1:17
 */
public class WebMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer
{
    /**
     *
     * @return 返回空也可以
     */
    @Override
    protected Class<?>[] getRootConfigClasses()
    {
        return new Class<?>[] {RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses()
    {
        return new Class<?>[] {WebMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings()
    {
        return new String[] {"/"};
    }
}
