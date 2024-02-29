package com.wzy.example.consumer;

import com.wzy.example.model.User;
import com.wzy.example.service.UserService;

/**
 * 简易服务消费者示例
 */
public class EasyConsumerExample {
    public static void main(String[] args) {
        //todo 需要获取UserService的实现类对象
        UserService userService = null;
        User user = new User();
        user.setName("wzy");

        //调用
        User newUser = userService.getUser(user);
        if (newUser!=null){
            System.out.println(newUser.getName());
        }else {
            System.out.println("user == null");
        }
    }
}
