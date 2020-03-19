package com.learn.webflux.config;

import org.springframework.web.server.adapter.AbstractReactiveWebInitializer;

/**
 * @author SuanCaiYv
 * @time 2020/3/15 下午6:10
 */
public class WebFluxInitializer extends AbstractReactiveWebInitializer
{
    @Override
    protected Class<?>[] getConfigClasses()
    {
        return new Class<?>[] {WebFluxConfig.class};
    }
}
