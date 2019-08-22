package com.sunll.lintcode.normal;

import java.util.List;

/**
 * <p>desc: 硬币组合问题</p>
 * 比如现在有面值 1 5 10 20 50元的纸币
 * 1.如果需要给个人找零85元，请问使用最少的纸币个数的组合是几张？以及给出一种组合。***********这个问题很重要*********
 * 2.如果不限定纸币的张数，请问一共有多少种组合方式？不需要具体组合的结果。
 * 3.如果限定纸币的个数为3张，请问有多少种组合方式？不需要具体组合的结果。
 *
 * 我们要从这些题中学习贪心算法和动态规划。
 * @author sunliangliang 2019-08-21 23:23
 * @version 1.0
 */
public class MoneyCombine {
    public static void main(String[] args) {

    }

    /**
     * 第一个问题
     * 第一个解决方案：贪心法
     * 思想：从最大额开始组合，以此使用第二大面额进行组合，直到结束
     * 弊端：局部最优解！！！！！！！
     * @param arr
     * @param total
     * @return
     */
    public static List<Integer> getCombine01(int[] arr, int total){

        return null;
    }

    /**
     * 第一个问题
     * 第一个解决方案：动态规划
     * 思想：每一步
     * 优势：全局最优解
     * @param arr
     * @param total
     * @return
     */
    public static List<Integer> getCombine02(int[] arr, int total){

        return null;
    }

}
