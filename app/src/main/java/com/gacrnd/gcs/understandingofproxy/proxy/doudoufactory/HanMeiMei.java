package com.gacrnd.gcs.understandingofproxy.proxy.doudoufactory;

import com.gacrnd.gcs.understandingofproxy.proxy.Climping;
import com.gacrnd.gcs.understandingofproxy.proxy.Message;

/**
 * @author Jack_Ou  created on 2020/7/4.
 */
public class HanMeiMei implements Message, Climping {

    @Override
    public void climping() {
        System.out.println("Climping");
    }

    @Override
    public void message() {
        System.out.println("message");
    }
}
