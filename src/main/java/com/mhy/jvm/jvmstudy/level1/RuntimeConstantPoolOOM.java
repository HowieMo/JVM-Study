package com.mhy.jvm.jvmstudy.level1;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 * mhy
 * 2019/3/19 20:15
 * 方法区 运行时常量池内存溢出
 */
//String.intern()是一个Native方法，作用：如果字符串常量池中已经包含一个等于此String对象的字符串，
// 则返回代表池中这个字符串的String对象；否则，将此String对象包含的字符串添加到常量池中
public class RuntimeConstantPoolOOM {
    //该方法在JDK1.6下会出现OOM异常：PermGen space，但是1.7并不会。
    public static void main(String[] args) {
        //使用list保持着常量池引用，避免Full GC回收常量池行为
        List<String> list = new ArrayList<>();
        //10M的PermSize在Integer范围内足够产生OOM了
        int i=0;
        while (1>0) {
            list.add(String.valueOf(i++).intern());
        }
    }

    //jdk1.6输出  false   false
    //jdk1.7输出  true    true
    private void testIntern(){
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern()==str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2==str2);
    }
}
