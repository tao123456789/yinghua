package com.yinghua.core.utils.userUtil;

import com.yinghua.core.domain.bo.UserBO;
import com.yinghua.core.feign.RedisFeignService;
import com.yinghua.core.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
public class UserUtil {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Resource
    RedisFeignService redisFeignService;

    @Resource
    UserMapper userMapper;

    public UserBO getCurrentUserInfo(){
        System.out.println("---------------------------------------------------------------------");
        System.out.println(httpServletRequest.getHeader("token"));
        String token = httpServletRequest.getHeader("token");

        UserBO userBO=new UserBO();

        if(token==null){
            System.out.println("[common服务]无token，请重新登录");
        }
        try {
            int userid = Integer.parseInt(redisFeignService.getCacheObject(token));
            userBO = userMapper.GetUserByUserId(userid);
            System.out.println("当前token用户：" + userBO.getUserName());
        }catch (Exception e){
            System.out.println("getCurrentUserInfo获取当前用户失败");
        }
        System.out.println("---------------------------------------------------------------------");
        return userBO;
    }
}
