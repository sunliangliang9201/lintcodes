package com.sunll.lintcode.normal.producerconsumer;

/**
 * <p>desc: 生产者</p>
 *
 * @author sunliangliang 2019-08-29 16:14
 * @version 1.0
 */
public class MyProducer<T> implements Runnable{
    private Store<T> myStore;
    private T t;

    public MyProducer(Store<T> store){
        this.myStore = store;
    }
    public void setT(T t){
        this.t = t;
    }

    @Override
    public void run() {
        while (this.myStore != null){
            try {
                myStore.produce(this.t);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
