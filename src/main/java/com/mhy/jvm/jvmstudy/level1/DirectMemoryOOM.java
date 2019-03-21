package com.mhy.jvm.jvmstudy.level1;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * VM Args: -Xmx20M -XX:MaxDirectMemorySize=10M
 * mhy
 * 2019/3/21 21:05
 * 直接内存溢出
 */
public class DirectMemoryOOM {
    private static final int _1M = 1024*1024;

    public static void main(String[] args)throws Exception{
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (1>0){
            unsafe.allocateMemory(_1M);
        }
    }
}
