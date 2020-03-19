package com.learn.webflux.dao;

import com.learn.webflux.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author SuanCaiYv
 * @time 2020/3/15 下午5:52
 */
@Mapper
@Component
public interface UserMapper
{
    void insert(User user);

    User selectOne(String id);
}
