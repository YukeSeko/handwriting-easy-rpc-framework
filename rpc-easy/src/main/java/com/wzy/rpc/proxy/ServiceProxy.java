package com.wzy.rpc.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.wzy.rpc.model.RpcRequest;
import com.wzy.rpc.model.RpcResponse;
import com.wzy.rpc.serializer.JdkSerializer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 服务代理（JDK动态代理）
 * @author 王灼宇
 * @Since 2024/3/1 10:31
 */
public class ServiceProxy implements InvocationHandler {
    /**
     *调用代理
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //指定序列化器
        JdkSerializer jdkSerializer = new JdkSerializer();

        //构造请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();

        try {
            //序列化
            byte[] bodyBytes = jdkSerializer.serialize(rpcRequest);

            //todo 这里地址写死了，需要使用注册中心和服务服务发现机制解决
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080").body(bodyBytes).execute()) {
                byte[] result = httpResponse.bodyBytes();
                RpcResponse rpcResponse = jdkSerializer.deserialize(result, RpcResponse.class);
                return rpcResponse.getData();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
