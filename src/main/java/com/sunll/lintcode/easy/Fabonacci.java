package com.sunll.lintcode.easy;

import java.util.Arrays;

/**
 * <p>desc: 斐波那契数列</p>
 * 其实这个斐波那契fabonacci数列跟基础跳台阶是一致的，当千项等于前两项之和
 * 实现fabonacci数列的三种方式：
 * 1.递归  时间复杂度是O(2^n)
 * 2.迭代  时间复杂度是O(n)
 * 3.通向公式 时间复杂度是O(n)
 *
 * @author sunliangliang 2019-08-22 10:39
 * @version 1.0
 */
public class Fabonacci {
    public static void main(String[] args) {
        //返回前10位的fabonacci数列
        System.out.println(fabonacci01(10));
        System.out.println(Arrays.toString(fabonacci02(10)));
    }

    /**
     * 递归法
     * @param num
     * @return
     */
    public static int fabonacci01(int num){
        if (num < 3) return num;
        return fabonacci01(num - 1) + fabonacci01(num - 2);
    }

    /**
     * 迭代
     * @param num
     * @return
     */
    public static int[] fabonacci02(int num){
        int[] res = new int[num];
        res[0] = 1;
        res[1] = 2;
        int index = 2;
        while(num-- >= 3){
           res[index] = res[index-1] + res[index-2];
           index++;
        }
        return res;
    }

    /**
     * 通项公式
     * @param num
     * @return
     */
    public static int[] fabonacci03(int num){
        int[] res = new int[num];
        for (int i = 0; i < res.length; i++){
            //res[i] =.....//通项公式自行百度吧
        }
        return res;
    }
}
