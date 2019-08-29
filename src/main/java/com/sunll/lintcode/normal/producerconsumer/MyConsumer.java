package com.sunll.lintcode.normal.producerconsumer;


/**
 * <p>desc: 消费者</p>
 *
 * @author sunliangliang 2019-08-29 16:15
 * @version 1.0
 */
public class MyConsumer<T> implements Runnable {
    private Store<T> mystore;

    public MyConsumer(Store store){
        this.mystore = store;
    }

    @Override
    public void run() {
        while (this.mystore != null){
            try {
                mystore.consume();
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
