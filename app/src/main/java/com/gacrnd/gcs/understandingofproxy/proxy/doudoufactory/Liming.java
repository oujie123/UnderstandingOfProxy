package com.gacrnd.gcs.understandingofproxy.proxy.doudoufactory;

import com.gacrnd.gcs.understandingofproxy.proxy.Message;

/**
 * @author Jack_Ou  created on 2020/7/3.
 */
public class Liming implements Message {

    @Override
    public void message() {
        System.out.println("TY service");
    }
}
