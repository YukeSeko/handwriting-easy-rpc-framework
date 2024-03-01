package com.wzy.rpc.proxy;

import java.lang.reflect.Proxy;

/**
 * 服务代理工厂（用于创建代理对象）
 * @author 王灼宇
 * @Since 2024/3/1 10:43
 */
public class ServiceProxyFactory {

    /**
     * 根据服务类获取代理对象
     * @param serviceClass
     * @return
     * @param <T>
     */
    public static <T> T getProxy(Class<T> serviceClass){
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy()
        );
    }
}
