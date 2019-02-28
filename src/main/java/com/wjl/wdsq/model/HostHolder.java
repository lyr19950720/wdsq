package com.wjl.wdsq.model;

import org.springframework.stereotype.Component;

@Component
public class HostHolder {
    //许多人同时访问，用java的线程本地变量，看起来是一个，但每个线程都有一个拷贝，通过公共接口访问
    //每个Thread的对象都有一个ThreadLocalMap，当创建一个ThreadLocal的时候，就会将该ThreadLocal对象添加到该Map中
    //，其中键就是ThreadLocal，值可以是任意类型。
    private static  ThreadLocal<User> users = new ThreadLocal<User>();

    //Map<Thread,User> 当前是哪个线程就访问谁的，每个线程有自己的变量
    public User getUser()
    {
        return users.get();
    }
    public  void setUser(User user){
        users.set(user);
    }
    public void clear(){
        users.remove();
    }
}
