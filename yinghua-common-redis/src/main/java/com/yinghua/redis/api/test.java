package com.yinghua.redis.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class test {
    @PostMapping("test")
    public String test(){
        return "123231";
    }
}
