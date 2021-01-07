package com.gacrnd.gcs.understandingofproxy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author Jack_Ou  created on 2021/1/7.
 */
public class ProxyHandler implements InvocationHandler {

    private OrderService service;

    public ProxyHandler(OrderService service) {
        this.service = service;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("before invoke -->");
        o = method.invoke(service, objects);
        System.out.println("after invoke -->");
        return o;
    }
}
