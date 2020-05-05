package me.juc.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *  CyclicBarrier字面意思是可循环使用的屏障，它要做的事情是让一组线程达到一个屏障（也可以叫做同步点）时被阻塞，
 *  直到最后一个线程到达屏障时，屏障才会打开，所有被屏障拦截的线程才会继续干活，线程进入屏障通过CyclicBarrier的await()方法
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() -> {
            System.out.println("***召唤神龙***");
        });

        for (int i = 1; i <= 7 ; i++) {
            final int tmpi = i;
            new Thread(() -> {
                System.out.println("收集到第" + tmpi + "颗龙珠");
                try {
                    cyclicBarrier.await();//条件未达到，线程阻塞
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
