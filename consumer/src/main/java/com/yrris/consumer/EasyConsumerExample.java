package com.yrris.consumer;

import com.yrris.common.model.User;
import com.yrris.common.service.UserService;
import com.yrris.hyperrpc.proxy.ServiceProxyFactory;

/**
 * 简单服务消费者实例
 */
public class EasyConsumerExample {
    public static void main(String[] args) {
        // 动态代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User yrris = new User();
        yrris.setName("yrris");
        User user = userService.getUser(yrris);
        if(user!=null){
            System.out.println(user.getName());
        }else{
            System.out.println("user is null");
        }
    }
}
