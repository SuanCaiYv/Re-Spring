package com.learn.webmvc.controller;

import com.learn.webmvc.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SuanCaiYv
 * @time 2020/3/15 下午1:20
 */
@RestController
public class DevController
{
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/index")
    public String f()
    {
        System.out.println(userMapper.selectOne("2508826394@qq.com").getName());
        return "text";
    }

    @RequestMapping(value = "/test/newIns")
    public String f1(@RequestParam("id") String id)
    {
        System.out.println(id);
        return "text";
    }
}
