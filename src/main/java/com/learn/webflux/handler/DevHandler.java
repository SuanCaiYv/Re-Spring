package com.learn.webflux.handler;

import com.alibaba.fastjson.JSON;
import com.learn.webflux.dao.UserMapper;
import com.learn.webflux.pojo.User;
import com.learn.webflux.result.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.function.Consumer;
import java.util.function.Function;


/**
 * @author SuanCaiYv
 * @time 2020/3/16 下午2:09
 */
@Component
public class DevHandler
{
    @Autowired
    private UserMapper userMapper;

    public Mono<ServerResponse> loginIn(ServerRequest request)
    {
        Flux<String> stringFlux = request.body(BodyExtractors.toFlux(String.class));
        stringFlux.subscribe(System.out::println);
        Mono<MultiValueMap<String, String>> multiValueMapMono = request.formData();
        Consumer<MultiValueMap<String, String>> consumer = (map) -> {
            String id = map.getFirst("id");
            String password = map.getFirst("password");
            User user = userMapper.selectOne(id);
            System.out.println(id+" : "+password);
            if (user != null && user.getPassword().equals(password)) {
                ResultBean<User> resultBean = new ResultBean<>();
                resultBean.setMsg("success");
                resultBean.setData(user);
                String json = JSON.toJSONString(resultBean);
            }
            else {
                ResultBean<Void> resultBean = new ResultBean<>();
                resultBean.setCode(ResultBean.ERROR);
                resultBean.setMsg("error");
                String json = JSON.toJSONString(resultBean);
            }
        };
        Scheduler scheduler = Schedulers.newBoundedElastic(12, 10000, "no-name");
        multiValueMapMono.
                subscribeOn(scheduler)
                .subscribe(consumer);
        return ServerResponse.ok().body(Mono.just("text"), String.class);
    }

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
        var map = request.formData();
        Function<MultiValueMap<String, String>, Mono<ServerResponse>> function = stringStringMultiValueMap -> {
            System.out.println(stringStringMultiValueMap.getFirst("id"));
            return ServerResponse.ok().body(Mono.just("get it!"), String.class);
        };
        return map.flatMap(function);
    }
}
