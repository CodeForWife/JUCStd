package me.juc.thread;

import javax.swing.plaf.TableHeaderUI;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public  void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " come in myLock");
        while (!atomicReference.compareAndSet(null,thread)){}
    }

    public  void myUnLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(thread.getName() + " come in myUnLock");
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(() -> {
            spinLockDemo.myLock();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnLock();
        },"AAA").start();

        Thread.sleep(1000);

        new Thread(()->{
            spinLockDemo.myLock();
            spinLockDemo.myUnLock();
        },"BBB").start();
    }
}
