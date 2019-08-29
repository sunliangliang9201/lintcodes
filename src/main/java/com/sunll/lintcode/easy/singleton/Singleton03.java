package com.sunll.lintcode.easy.singleton;

/**
 * <p>desc: 懒汉式，双重检测机制</p>
 *  优点就是比syn效率高很多
 *  但是有一点就是必须注意，用volatile来修改变量，不然会以极低的概率出现线程不安全
 * @author sunliangliang 2019-08-29 15:57
 * @version 1.0
 */
public class Singleton03 {
    private static volatile Singleton03 singleton = null;
    private Singleton03(){}
    public static Singleton03 getInstance(){
        if (null == singleton){
            synchronized (Singleton03.class){
                if (null == singleton){
                    singleton = new Singleton03();
                }
            }
        }
        return singleton;
    }
}
