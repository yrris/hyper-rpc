package com.yrris.consumer;

import com.yrris.common.model.User;
import com.yrris.common.service.UserService;
import com.yrris.hyperrpc.RpcApplication;
import com.yrris.hyperrpc.config.RpcConfig;
import com.yrris.hyperrpc.proxy.ServiceProxyFactory;
import com.yrris.hyperrpc.utils.ConfigUtils;

/**
 * 简单服务消费者实例
 */
public class EasyConsumerExample {
    public static void main(String[] args) {
        RpcConfig rpcConfig = ConfigUtils.loadConfig(RpcConfig.class,"rpc");
        System.out.println(rpcConfig);
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
        //测试mock功能
        int i = userService.getNumber();
        System.out.println("number : "+i);
    }
}
