package com.sunll.lintcode.easy.singleton;

/**
 * <p>desc: </p>
 *
 * @author sunliangliang 2019-08-29 15:43
 * @version 1.0
 */
public class Singleton {
    public static void main(String[] args) {
//        Singleton01 s1 = Singleton01.getInstance();
//        Singleton01 s2 = Singleton01.getInstance();
//        Singleton02 s1 = Singleton02.getInstance();
//        Singleton02 s2 = Singleton02.getInstance();
//        Singleton03 s1 = Singleton03.getInstance();
//        Singleton03 s2 = Singleton03.getInstance();
        Singleton04 s1 = Singleton04.getInstance();
        Singleton04 s2 = Singleton04.getInstance();
        System.out.println(s1);
        System.out.println(s2);
    }


}

