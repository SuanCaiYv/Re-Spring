package com.learn.webflux.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;

/**
 * @author SuanCaiYv
 * @time 2020/3/15 下午7:28
 */
@RestController
public class DevController
{
    @RequestMapping(value = "/index")
    public Flux<String> f(ServerWebExchange exchange)
    {
        var map = exchange.getFormData();
        map.subscribe(System.out::println);
        return Flux.just("text");
    }
}
