package com.learn.webmvc.dao;

import com.learn.webmvc.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author SuanCaiYv
 * @time 2020/3/15 下午5:36
 */
@Component
@Mapper
public interface UserMapper
{
    void insert(User user);

    User selectOne(String id);
}
