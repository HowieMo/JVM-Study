package com.mhy.jvm.jvmstudy.level1;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 * mhy
 * 2019/3/19 21:43
 * 方法区内存溢出
 */
public class JavaMethodAreaOOM {
    /**
     * 通过CGLib字节码技术，对类进行增强，以达到产生大量的类而使方法区内存溢出
     * 无法重现书中内存溢出，不知道是不是因为JDK的问题，后续研究
     * @param args
     */
    public static void main(final String[] args) {
        while (1>0){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o,objects);
                }
            });
            enhancer.create();
        }
    }

    static class OOMObject{}
}
