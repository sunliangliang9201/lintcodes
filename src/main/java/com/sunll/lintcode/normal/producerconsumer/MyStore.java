package com.sunll.lintcode.normal.producerconsumer;

import java.util.LinkedList;

/**
 * <p>desc: </p>
 *
 * @author sunliangliang 2019-08-29 16:38
 * @version 1.0
 */
public class MyStore<T> implements Store<T> {

    private LinkedList<T> list; //存储物品
    private volatile int num; //当前物品数量
    private int MAX_NUM; //仓库最大存储数量

    public MyStore(int maxNum){
        this.MAX_NUM = maxNum>0? maxNum:100;
        this.list = new LinkedList<>();
    }


    @Override
    public void produce(T t) {
        synchronized (list){
            if (num+1 > this.MAX_NUM){
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(t);
            System.out.println(Thread.currentThread().getName() + "produce" + t.toString());
            num++;
            System.out.println("the store count is "+ String.valueOf(num));
            list.notifyAll();
        }
    }

    @Override
    public void consume() {
        synchronized (list){
            if (this.num == 0){
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T t = list.removeFirst();
            System.out.println(Thread.currentThread().getName()+" consume "+t.toString());
            num--;
            System.out.println("the store count is "+ String.valueOf(num));
            list.notifyAll();
        }
    }
}
