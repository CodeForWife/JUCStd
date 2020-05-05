package me.juc.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class ShareData1 {
    private int number = 1;
    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition c1 = reentrantLock.newCondition();
    private Condition c2 = reentrantLock.newCondition();
    private Condition c3 = reentrantLock.newCondition();

    public void print5() throws InterruptedException {
        reentrantLock.lock();
        while (number != 1) {
            c1.await();
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        number = 2;
        c2.signal();
        reentrantLock.unlock();
    }
    public void print10() throws InterruptedException {
        reentrantLock.lock();
        while (number != 2) {
            c2.await();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        number = 3;
        c3.signal();
        reentrantLock.unlock();
    }
    public void print15() throws InterruptedException {
        reentrantLock.lock();
        while (number != 3) {
            c3.await();
        }
        for (int i = 0; i < 15; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        number = 1;
        c1.signal();
        reentrantLock.unlock();
    }

}

public class SyncAndReentrantLockDemo {

    public static void main(String[] args) {
        ShareData1 shareData1 = new ShareData1();

        new Thread(() -> {
            try {
                shareData1.print5();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();

        new Thread(() -> {
            try {
                shareData1.print10();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BBB").start();

        new Thread(() -> {
            try {
                shareData1.print15();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"CCC").start();
    }


}
