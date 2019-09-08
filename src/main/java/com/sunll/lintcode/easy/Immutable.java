package com.sunll.lintcode.easy;

import java.util.Arrays;

/**
 * <p>desc: 不可变类实现</p>
 * 1.用final 修饰class，不可以被继承
 * 2.所有变量都要是private
 * 3.不能有setter方法
 * 4.getter方法必须是变量的clone（非基本类型）
 * 5.构造方法如果涉及传入参数时也要是clone的。
 * @author sunliangliang 2019-09-08 10:04
 * @version 1.0
 */
public final class Immutable {
    private int count;
    private String[] arr;

    public Immutable(String[] arr){
        //this.arr = arr;//错误
        this.arr = arr.clone();
    }

    public int getCount() {
        return count;
    }
    public String[] getArr(){
        return this.arr.clone();
    }

    public static void main(String[] args) {
        String[] arr = new String[]{"a","b","c"};
        Immutable i1 = new Immutable(arr);
        arr[0] = "b";
        System.out.println(Arrays.toString(i1.getArr()));
    }
}
