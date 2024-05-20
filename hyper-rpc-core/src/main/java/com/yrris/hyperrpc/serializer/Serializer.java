package com.yrris.hyperrpc.serializer;

import java.io.IOException;

/**
 * 序列化接口
 */
public interface Serializer {
    /**
     * 序列化
     * 将Java对象转化为可以进行传输的字节数组
     *
     * @param object
     * @param <T>
     * @return
     * @throws IOException
     */
    <T> byte[] serialize(T object) throws IOException;

    /**
     * 反序列化
     * 将字节数组活化为Java对象
     *
     * @param bytes
     * @param type
     * @param <T>
     * @return
     * @throws IOException
     */
    <T> T deserialize(byte[] bytes, Class<T> type) throws IOException;
}