package com.gacrnd.gcs.understandingofproxy.ClickInject;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.net.ssl.TrustManagerFactory;

/**
 * @author Jack_Ou  created on 2020/7/7.
 */
public class InjectUtils {

    private static final String TAG = InjectUtils.class.getSimpleName();

    public static void injectClickListener(Activity activity) {
        Class<? extends Activity> cl = activity.getClass();
        //获得所有有注解的方法，包括：onCreate，onClick，onLongClick....
        Method[] declaredMethods = cl.getDeclaredMethods();

        for (Method method : declaredMethods) {
            //获取某个方法上的注解：Override，OnClick.....
            //annotation: onclick()/ onLongClick()
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                //注解类型     该处获得的是注解的注解（元注解），即OnClick的注解EventType.class
                //annotationType是元注解类
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if (annotationType.isAnnotationPresent(EventType.class)) {
                    //获取某个方法的EventType
                    EventType eventType = annotationType.getAnnotation(EventType.class);
                    //View.OnClickListener.class
                    Class listenerType = eventType.listenerType();
                    //setOnClickListener
                    String listenerSetter = eventType.listenerSetter();

                    //下一个目标是获得各个View
                    try {
                        // 不需要关心到底是OnClick 还是 OnLongClick
                        Method valueMethod = annotationType.getDeclaredMethod("value");
                        //valueMethod ---》 public abstract int[] com.gacrnd.gcs.understandingofproxy.ClickInject.OnClick.value()
                        Log.e(TAG, "value:" + valueMethod);
                        //调用onClick()方法得到View的Id值
                        int[] viewIds = (int[]) valueMethod.invoke(annotation);

                        method.setAccessible(true);
                        ListenerInvocationHandler<Activity> handler = new ListenerInvocationHandler(activity, method);
                        Object o = Proxy.newProxyInstance(annotationType.getClassLoader(), new Class[]{listenerType}, handler);
                        //对每一个View执行setOnClickListener
                        for (int viewId : viewIds) {
                            //获得某个button的view
                            View view = activity.findViewById(viewId);
                            // 获取指定的方法(不需要判断是Click还是LongClick)
                            // 如获得：setOnClickLisnter方法，参数为OnClickListener
                            // 获得 setOnLongClickLisnter，则参数为OnLongClickLisnter
                            Method setter = view.getClass().getMethod(listenerSetter, listenerType);
                            setter.invoke(view,o);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    /**
     * 还可能在自定义view注入，所以是泛型： T = Activity/View
     *
     * @param <T>
     */
    static class ListenerInvocationHandler<T> implements InvocationHandler {

        private Method method;
        private T target;

        public ListenerInvocationHandler(T target, Method method) {
            this.target = target;
            this.method = method;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return this.method.invoke(target, args);
        }
    }
}
