package com.sunll.lintcode.easy.singleton;

/**
 * <p>desc: 懒汉式，方法同步</p>
 * 第一：私有静态变量 = null
 * 第二：私有构造函数
 * 第三：对外提供静态方法 用syn修饰
 * 这种方式不建议，效率特别低，因为不管对象是不是已经创建成功，之后每次请求都要走阻塞串行
 * @author sunliangliang 2019-08-29 15:51
 * @version 1.0
 */
public class Singleton02 {
    private static Singleton02 singleton = null;
    private Singleton02(){}
    public static synchronized Singleton02 getInstance(){
        if (null == singleton){
            singleton = new Singleton02();
        }
        return singleton;
    }
}
