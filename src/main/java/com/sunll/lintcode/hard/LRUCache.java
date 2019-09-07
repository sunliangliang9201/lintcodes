package com.sunll.lintcode.hard;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * <p>desc: LRU缓存类实现</p>
 * 目的：实现一个LRU缓存类（快手）
 * 其他缓存：
 * 第一：FIFO缓存失效策略：  先进先出。比如我们用linkedList + HashMap来实现，把key加入队列linkedlist 把key和value加入hashmap，如果队列满了，把队列头部的数据删除并删除hashmap中的这个keyvalue即可
 * 第二：LFU缓存失效策略： 最近最不频繁使用。用一个优先队列priorityqueue + 计数 + HashMap来实现，和fifo区别就在于如果缓存命中就更改权重计数，优先队列会自动进行重排序。
 * 第三：LRU缓存失效策略： 最近最少使用。用一个双向链表linkedlist + hashmap来实现，和fifo的区别就在于如果缓存命中就把数据移动到队列的头部，新插入数据插入到队列尾部
 *          LRU也可以直接用LinkedHashMap来实现。
 * 注意：上述阐述中多是非线程安全的实现方式。如果想要实现线程安全的缓存类，那么就得用锁或者是同步集合来做。
 * 所以我们实现一个线程安全的缓存类吧。
 * @author sunliangliang 2019-09-05 23:27
 * @version 1.0
 */
public class LRUCache<T> {//注意正常来说应该是LRUCache<T, V>，因为用V泛型的化代码不太好弄，所以直接写死String了

    private  LinkedBlockingDeque<T> deque = new LinkedBlockingDeque<>();//用于O(n)的move
    private ConcurrentHashMap<T,String> map = new ConcurrentHashMap<>();//用于的logn的get和remove
    private int DEFAULT_LEN = 5;
    private int maxLen;
    public LRUCache(){
        this.maxLen = DEFAULT_LEN;
    }
    public LRUCache(int maxLen){
        this.maxLen = maxLen>0? maxLen:DEFAULT_LEN;
    }

    /**
     * 获取元素，命中缓存或者不命中
     */
    public String getCache(T key) throws InterruptedException {
        if (map.containsKey(key)){
            System.out.println("命中缓存" + key);
            //删除队列中间元素
            deleteEle(key);
            deque.addFirst(key);
            return map.get(key);
        }else{
            System.out.println("去数据库查询");
            String value = getFromMysql(key);
            Thread.sleep(500);
            //向队列中添加元素
            addFirst(key);
            map.put(key, key + value);
            return value;
        }
    }

    /**
     * 如命中缓存，现将key从队列删除再添加到队列头部
     */
    private void deleteEle(T key){
        Iterator<T> iter = deque.iterator();
        while (iter.hasNext()){
            T tmp = iter.next();
            if (tmp.equals(key)){
                iter.remove();
                break;
            }
        }
    }

    /**
     * 如没有命中缓存，则先去mysql查数据，然后再将数据的key加入队列头部，同时加入到hashmap中
     */
    private void addFirst(T key){
        if (deque.size() < maxLen){
            deque.addFirst(key);
        }else{
            T tmp = deque.removeLast();
            map.remove(tmp);//注意要把map中数据删除
            System.out.println("移除缓存" + tmp);
            deque.addFirst(key);
        }
    }

    /**
     * 模拟去mysql查询
     */
    String[] res = new String[]{"1", "2", "3"};
    public  String getFromMysql(T key){
        return res[Math.abs(new Random().nextInt() % res.length)];
    }


    public static void main(String[] args) throws InterruptedException {
        LRUCache<String> lruCache = new LRUCache<>();
        System.out.println(lruCache.getCache("a"));
        System.out.println(lruCache.getCache("b"));
        System.out.println(lruCache.getCache("c"));
        System.out.println(lruCache.getCache("d"));
        System.out.println(lruCache.getCache("e"));
        System.out.println(lruCache.getCache("f"));
        System.out.println(lruCache.getCache("g"));
        System.out.println(lruCache.getCache("a"));//此时超过了maxlen

    }
}
