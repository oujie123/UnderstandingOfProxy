package com.gacrnd.gcs.understandingofproxy.proxy;


import com.gacrnd.gcs.understandingofproxy.proxy.agent.Agent;
import com.gacrnd.gcs.understandingofproxy.proxy.doudoufactory.HanMeiMei;
import com.gacrnd.gcs.understandingofproxy.proxy.doudoufactory.Liming;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Jack_Ou  created on 2020/7/3.
 */
public class Customer {

    public static void main(String[] args) {
        //静态代理
//        Message message = new Liming();
//        Agent agent = new Agent(message);
//        agent.message();

        final Liming liming = new Liming();
        final HanMeiMei hanMeiMei = new HanMeiMei();

        //动态代理
        Object o = Proxy.newProxyInstance(Customer.class.getClassLoader(), new Class[]{Message.class, Climping.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                System.out.println("==========");
                return method.invoke(hanMeiMei, objects);
            }
        });

        Message message = (Message) o;
        message.message();

        Climping climping = (Climping) o;
        climping.climping();
    }
}
