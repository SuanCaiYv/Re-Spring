package com.learn.webflux.handler;

import com.learn.webflux.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


/**
 * @author SuanCaiYv
 * @time 2020/3/16 下午2:09
 */
@Component
public class DevHandler
{
    @Autowired
    private UserMapper userMapper;

    public Mono<ServerResponse> testVal(ServerRequest request)
    {
        System.out.println(request.pathVariable("val"));
        return ServerResponse.ok().body(Mono.just("text"), String.class);
    }

    public Mono<ServerResponse> testMsg(ServerRequest request)
    {
        return ServerResponse.ok().body(Mono.just("text"), String.class);
    }

    public Mono<ServerResponse> testNewIns(ServerRequest request)
    {
        Mono<MultiValueMap<String, String>> multiValueMapMono = request.formData();
        return multiValueMapMono.flatMap(map -> {
            System.out.println(map.getFirst("id"));
            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue("get it!"));
        });
    }
}
