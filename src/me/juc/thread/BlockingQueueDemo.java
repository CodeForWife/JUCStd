package me.juc.thread;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        /*System.out.println(blockingQueue.offer("a",2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b",2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c",2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("d",2L, TimeUnit.SECONDS));

        System.out.println(blockingQueue.poll(2l,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2l,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2l,TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2l,TimeUnit.SECONDS));*/

        blockingQueue.put("a");

        blockingQueue.put("b");
        blockingQueue.put("c");
        System.out.println("-------------");
//        blockingQueue.put("d"); //一直阻塞

        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        System.out.println("=============");
        blockingQueue.take(); //一直阻塞

        //返回true或false
    /*    System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));

        System.out.println(blockingQueue.peek());

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());*/

        //抛异常
   /*     System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
//        System.out.println(blockingQueue.add("d"));
        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());*/


    }

}
