package com.mhy.jvm.jvmstudy.level2;

/**
 * -XX:+PrintGC
 * @author mhy
 * 2019/3/21 21:56
 * testGC（）方法执行后，objA和objB会不会被GC呢？
 */
public class ReferenceCountingGC {
    public Object instance = null;

    private static final int _1MB = 1024*1024;
    /**
     * 该对象的唯一意义是占点内存，好在GC日志中看清楚是否被回收过
     */
    private byte[] bigSize = new byte[2*_1MB];

    public static void main(String[] args) {
        ReferenceCountingGC referenceCountingGCA = new ReferenceCountingGC();
        ReferenceCountingGC referenceCountingGCB = new ReferenceCountingGC();
        referenceCountingGCA.instance = referenceCountingGCB;
        referenceCountingGCB.instance = referenceCountingGCA;

        referenceCountingGCA = null;
        referenceCountingGCB = null;

        //假设在这里进行GC，两个对象是否会被回收
        System.gc();
        //实际发现是由进行回收的，如果说是按照引用计数算法来实现GC的话，是不会进行回收的。
    }

}
