package com.gacrnd.gcs.understandingofproxy.proxy.agent;

import com.gacrnd.gcs.understandingofproxy.proxy.Message;

/**
 * @author Jack_Ou  created on 2020/7/3.
 */
public class Agent implements Message {

    private final Message message;

    public Agent(Message message){
        this.message = message;
    }

    private void before(){
        System.out.println("前置服务");
    }

    private void after(){
        System.out.println("收尾工作");
    }

    @Override
    public void message() {
        before();
        message.message();
        after();
    }
}
