package com.sunll.lintcode.normal.producerconsumer;


import java.util.concurrent.Callable;

/**
 * <p>desc: </p>
 *  快手
 * @author sunliangliang 2019-08-29 16:38
 * @version 1.0
 */
public class Test {
    public static void main(String[] args) {
        Store<Integer> store = new MyStore(10);
        MyProducer<Integer> producer = new MyProducer(store);
        producer.setT(1);
        Thread t = new Thread(producer);
        t.setDaemon(true);
        t.start();

        MyConsumer<Integer> consumer = new MyConsumer<>(store);
        Thread t1 = new Thread(consumer);
        t1.setDaemon(true);
        t1.start();
        try {
            Thread.currentThread().join();//等待其他线程运行完毕
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
