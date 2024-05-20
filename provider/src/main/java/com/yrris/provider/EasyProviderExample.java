package com.yrris.provider;

import com.yrris.common.service.UserService;
import com.yrris.hyperrpc.RpcApplication;
import com.yrris.hyperrpc.config.RpcConfig;
import com.yrris.hyperrpc.registry.LocalRegistry;
import com.yrris.hyperrpc.server.HttpServer;
import com.yrris.hyperrpc.server.VertxHttpServer;

/**
 * 简单服务提供者实例
 */
public class EasyProviderExample {
    public static void main(String[] args) {
        // RPC 框架初始化
        RpcApplication.init();
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        //注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);
        //提供web服务
        HttpServer server =new VertxHttpServer();
        server.doStart(rpcConfig.getServerPort());
    }
}