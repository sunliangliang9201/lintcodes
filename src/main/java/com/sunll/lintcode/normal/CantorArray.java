package com.sunll.lintcode.normal;

import java.util.Arrays;

/**
 * <p>desc: cantor数组</p>
 * 一个数组里面乱序保存着N-1个连续的正整数，如果存在另一个数组表示的是后面比当前数组值小的有多少个如下
 * [4,6,2,5,3,1]  对应着[3,4,1,2,1,0]
 * 请问如果已知cantor数组如何复原原数组
 * @author sunliangliang 2019-08-14 17:23
 * @version 1.0
 */
public class CantorArray {
    public static void main(String[] args) {
        int[] cantor = new int[]{3,4,1,2,1,0};
        System.out.println(Arrays.toString(cantor(cantor)));
    }

    /**
     * 通过cantor数组来恢复源数组，元素组要求是连续无重复的数据，题目中会给出最大和最小，比如本题中给定前6个正整数，那么最小=1，最大=6
     * @param arr
     * @return
     */
    public static int[] cantor(int[] arr){
        int[] res = new int[arr.length];//这个n的空间可以省去，直接用arr
        int[] data = new int[arr.length];
        int min = 1;
        for (int i = 0; i<data.length; i++){
            data[i] = min++;
        }
        int end = data.length-1;
        int start = 0;
        for (int i =0; i<arr.length; i++){
            res[i] = data[arr[i]];
            start = res[i];
            for (int j = start-1;j<end;j++){
                data[j] = data[j+1];
            }
            end--;
        }
        return res;
    }
}
