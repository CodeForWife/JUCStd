package me.juc.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁ReentrantReadWriteLock
 * 读写、写写 互斥，不可同时进行
 * 读读可并行进行
 */
class MyCache {

    private volatile Map<String,Object> map = new HashMap<>();
    ReentrantReadWriteLock reRWLock = new ReentrantReadWriteLock();

    public void put(String key,Object value)  {
        reRWLock.writeLock().lock(); //写锁
        try {
            System.out.println(Thread.currentThread().getName() + ":正在写入");
            Thread.sleep(400);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName() + ":写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reRWLock.writeLock().unlock();
        }
    }

    public void get(String key) throws InterruptedException {

        reRWLock.readLock().lock(); // 读锁
        try {
            System.out.println(Thread.currentThread().getName() + ":正在获取");
            Thread.sleep(100);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName() + ":读取完成" + o);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reRWLock.readLock().unlock();
        }
    }
}

public class ReadWriteLockDemo {

    public static void main(String[] args) {

        MyCache myCache = new MyCache();

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(()->{
                try {
                    myCache.put("K" + finalI,"V" + finalI);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },"TP" + i).start();
        }

        for (int i = 0; i < 9; i++) {
            final int tmp = i;
            new Thread(()->{
                try {
                    myCache.get("K" + tmp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"TG" + i).start();
        }
    }
}
