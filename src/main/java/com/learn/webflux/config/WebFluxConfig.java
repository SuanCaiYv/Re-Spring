package com.learn.webflux.config;

import com.learn.webflux.handler.DevHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.ViewResolverRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.freemarker.FreeMarkerViewResolver;

/**
 * @author SuanCaiYv
 * @time 2020/3/15 下午5:58
 */
@Configuration
@EnableWebFlux
@ComponentScan(basePackages = {"com.learn.webflux"})
public class WebFluxConfig implements WebFluxConfigurer
{
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry)
    {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver("/WEB-INF/views/html/", ".html");
        registry.viewResolver(viewResolver);
    }

    @Bean
    public CorsWebFilter corsWebFilter()
    {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.applyPermitDefaultValues();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsWebFilter(source);
    }

    @Autowired
    private DevHandler devHandler;

    @Bean
    public RouterFunction<?> routerFunctionA()
    {
        RouterFunction<ServerResponse> routerFunction = RouterFunctions.route()
                .POST("/loginIn", RequestPredicates.all(), devHandler::loginIn)
                .path("/test", (builder) -> {
                    builder.GET("/{val}", RequestPredicates.all(), devHandler::testVal)
                            .GET("", RequestPredicates.all(), devHandler::testMsg)
                            .POST("/newIns", RequestPredicates.contentType(MediaType.APPLICATION_FORM_URLENCODED), devHandler::testNewIns);
                })
                .build();
        return routerFunction;
    }

}
