package com.gacrnd.gcs.understandingofproxy.ClickInject;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Jack_Ou  created on 2020/7/7.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventType(listenerType = View.OnClickListener.class,listenerSetter = "setOnClickListener")
public @interface OnClick {
    int[] value();
}
