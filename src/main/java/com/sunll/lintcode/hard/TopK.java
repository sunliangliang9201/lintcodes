package com.sunll.lintcode.hard;
import java.lang.Math;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author sunliangliang
 * @version 1.0
 * 问题描述：编写一个程序解决选择问题，所谓选择问题即找出无需数组中的第K大或者第K小的那个元素，假设K=N/2
 * 将会比较各类方法的时间复杂度
 */
public class TopK {
    public static void main(String[] args){
        int[] arr = new int[10000000];
        for(int i = 0; i < arr.length; i++){
            arr[i] = (int)(Math.random() * 10);
        }
        double startTime = System.currentTimeMillis();
        int res = topK_1(arr, arr.length/2);
        double endTime = System.currentTimeMillis();
        System.out.println("使用自带Arrays排序的方法结果为："+ res + "耗时为：" + (endTime-startTime));
        startTime = System.currentTimeMillis();
        res = topK_1(arr, arr.length/2);
        endTime = System.currentTimeMillis();
        System.out.println("使用方法结果为："+ res + "耗时为：" + (endTime-startTime));
    }

    /**
     * 使用Arrays.sort的快速排序，时间复杂度
     * @param arr data
     * @param k k
     * @return result
     */
    private static int topK_1(int[] arr, int k){
        Arrays.sort(arr);
        return arr[k];
    }

    /**
     * 自己实现快速排序试一下,快速排序的基准选取有三种：固定、随机、三取样，下面写的是最理想的三取样
     * @param arr data
     * @param k k
     * @return result
     */
    //三取样分割，左、右、中的均值
    public static int topK_2(int[] arr, int k){
        int jizhun = arr[1];
        return 1;
    }
    //递归实现快速排序
    public static void sort_2(int[] arr, int lo, int hi){

    }

}
