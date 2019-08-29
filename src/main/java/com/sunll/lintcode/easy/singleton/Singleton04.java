package com.sunll.lintcode.easy.singleton;

/**
 * <p>desc: 静态内部类</p>
 * 推荐使用的方式，静态内部类只有当使用它的时候才会加载，不使用不加载，那么就实现了懒加载
 * 另一方面静态内部类里面使用了饿汉式，所以天然线程安全
 * @author sunliangliang 2019-08-29 16:05
 * @version 1.0
 */
public class Singleton04 {
    private Singleton04(){}

    private static class SingletonInner{
        private static Singleton04 s= new Singleton04();
    }

    public static Singleton04 getInstance(){
        return SingletonInner.s;
    }
}
