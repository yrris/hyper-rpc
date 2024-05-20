package com.yrris.hyperrpc.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.yrris.hyperrpc.RpcApplication;
import com.yrris.hyperrpc.config.RpcConfig;
import com.yrris.hyperrpc.model.RpcRequest;
import com.yrris.hyperrpc.model.RpcResponse;
import com.yrris.hyperrpc.serializer.JdkSerializer;
import com.yrris.hyperrpc.serializer.Serializer;
import com.yrris.hyperrpc.serializer.SerializerFactory;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDK动态代理 实现服务代理
 */
public class ServiceProxy implements InvocationHandler {
    /**
     * 调用代理
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 通过序列化器工厂返回需要的序列化器,传入配置文件中指定的序列化器
        Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());

        // 构造请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        try {
            // 序列化
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            // 发送请求
            // todo 暂时硬编码了地址（需要注册中心和服务发现来解决）
            try (HttpResponse httpResponse = HttpRequest.post("http://"+RpcApplication.getRpcConfig().getServerHost()+":"+RpcApplication.getRpcConfig().getServerPort())
                    .body(bodyBytes)
                    .execute()) {
                byte[] result = httpResponse.bodyBytes();
                // 反序列化
                RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
                return rpcResponse.getData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}