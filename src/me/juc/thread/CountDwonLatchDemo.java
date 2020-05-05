package me.juc.thread;

import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch让一些线程阻塞直到另一些线程完成一系列操作之后才被唤醒
 * 其它线程调用countDown方法会将计数器-1（调用countDown方法的线程不会阻塞），
 * 当计数器的值变为0时，因调用await方法被阻塞的线程会被唤醒，继线执行
 */
public class CountDwonLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6 ; i++) {
            int tmp = i;
            new Thread(() -> {
                System.out.println(ClassEnum.foreachClass(tmp).getRetMsg() + "同学离开");
                countDownLatch.countDown();
            },"t" + i).start();
        }

        countDownLatch.await();

        System.out.println("班长关门");
    }
}

enum ClassEnum {

    ONE(1,"张三"),TWO(2,"李四"),THREE(3,"王朝"),FOUR(4,"马汉"),FIVE(5,"王五"),SIX(6,"赵六");

    private int retcode;
    private String retMsg;

    ClassEnum(int retcode,String retMsg) {
        this.retcode = retcode;
        this.retMsg = retMsg;
    }

    public int getRetcode() {
        return retcode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public static ClassEnum foreachClass(int index) {
        ClassEnum[] myArray = ClassEnum.values();
        for (ClassEnum element:myArray) {
            if (element.getRetcode() == index) {
                return element;
            }
        }
        return null;
    }

}
