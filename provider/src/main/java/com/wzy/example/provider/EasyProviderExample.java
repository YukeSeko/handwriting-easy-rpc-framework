package com.wzy.example.provider;

import com.wzy.example.service.UserService;
import com.wzy.rpc.registry.LocalRegistry;
import com.wzy.rpc.server.HttpServer;
import com.wzy.rpc.server.VertxHttpServer;

/**
 * 简易服务提供者示例
 */
public class EasyProviderExample {
    public static void main(String[] args) {
        //注册服务
        LocalRegistry.register(UserService.class.getName(),UserServiceImpl.class);

        //提供服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
