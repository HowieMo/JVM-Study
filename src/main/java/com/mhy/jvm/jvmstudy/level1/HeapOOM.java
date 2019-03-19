package com.mhy.jvm.jvmstudy.level1;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args:-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * mhy
 * 2019/3/18 22:48
 * 堆内存溢出
 */
public class HeapOOM {
    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (1>0){
            //java.lang.OutOfMemoryError: Java heap space
            //对象生成占用堆内存，在没有回收的情况下，超出对内存的最大设置20M，导致对内存溢出
            list.add(new OOMObject());
        }

    }
}
