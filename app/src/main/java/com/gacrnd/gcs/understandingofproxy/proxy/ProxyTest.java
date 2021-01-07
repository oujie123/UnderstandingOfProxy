package com.gacrnd.gcs.understandingofproxy.proxy;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;


/**
 * @author Jack_Ou  created on 2021/1/7.
 */
public class ProxyTest {
    public static void main(String[] args) {
        // 创建真实实现类
        OrderService service = new OutOrderServiceImpl();

        // 将真实实现类交给ProxyHandler
        OrderService proxy = (OrderService) Proxy.newProxyInstance(service.getClass().getClassLoader(),
                service.getClass().getInterfaces(),
                new ProxyHandler(service));

        // 通过代理调用到真实实现的方法
        proxy.saveOrder();
    }
}