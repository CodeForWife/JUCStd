package me.juc.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " put 1");
            try {
                blockingQueue.put("1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " put 2");
            try {
                blockingQueue.put("2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " put 3");
            try {
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T" + 1).start();

        new Thread(() -> {
            try {
//                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + " take " + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
//                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + " take " + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
//                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + " take " + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T2").start();
    }
}
