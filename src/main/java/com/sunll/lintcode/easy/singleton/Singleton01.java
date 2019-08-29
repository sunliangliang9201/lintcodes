package com.sunll.lintcode.easy.singleton;

/**
 * <p>desc: 饿汉式，天然的线程安全</p>
 * 第一：私有静态变量，直接创建对象
 * 第二：私有构造函数
 * 第三：对外提供静态方法
 * @author sunliangliang 2019-08-29 15:51
 * @version 1.0
 */
public class Singleton01 {
    private static Singleton01 singleton = new Singleton01();
    private Singleton01(){}
    public static Singleton01 getInstance(){
        return singleton;
    }
}
