package com.mhy.jvm.jvmstudy.level1;

/**
 * VM Args: -Xss128k
 * mhy
 * 2019/3/19 19:33
 * 栈内存溢出
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e){
            //随着减小栈容量的大小，异常时输出的栈深度相应缩小（抛出StackOverflowError异常）
            System.out.println("stack length: " + oom.stackLength);
            throw e;
            //由于建立过多线程导致的内存溢出（抛出OutOfMemoryError异常），在不能减少线程数的情况下，只能通过减小最大堆和减少栈容量来换取更多的线程
        }
    }
}
