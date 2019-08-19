package com.sunll.lintcode.hard;

import java.util.Arrays;
import java.util.BitSet;
import java.util.PriorityQueue;

/**
 * <p>desc: 完美洗牌</p>
 * 一个数组[a1,a2,a3...an,b1,b2,b3...bn] 洗牌之后变成[b1,a1,b2,a2....bn,an],有的题要求结果是[b1,a1,b2,a2....bn,an]，没关系再来一个O(N)的复杂度交换即可
 * 三种方法：
 * 暴力移动时间复杂度0（n2） 空间复杂度O（1）
 * 找规律 时间复杂度O（n） 空间复杂度O（n） (2 * i) % (2 * n + 1)
 * 完美洗牌 时间复杂度O（n） 空间复杂度O（1）走圈，完美结论2*n = 3^k -1，有k的环，根据规律将数据进行移动到环所指向的位置即可，走一次所以是O（n）
 * 不需要额外空间所以是O（1）
 * @author sunliangliang 2019-08-19 09:11
 * @version 1.0
 */
public class PerfectShuffle {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,8};
//        System.out.println(Arrays.toString(perfectShuffle01(arr)));
//        perfectShuffle02(arr);
//        System.out.println(Arrays.toString(arr));
    }

    /**
     * 通项公式，时间复杂度O(n)空间复杂度O(n)
     * @param arr
     */
    public static int[] perfectShuffle01(int[] arr){
        int[] res = new int[arr.length];
        for(int i=1; i<=arr.length; i++){
            res[2*i % (arr.length +1)-1] =arr[i-1] ;
        }
        return res;
    }

    /**
     * 走环算法
     * 神级结论：若 2 * n =（3^k - 1），则可确定圈的个数及各自头部的起始位置
     * 需要注意的是如果2 * n !=（3^k - 1）的时候，找出m，然后对n-m再次应用走环算法即可
     * @param arr
     * @return
     */
    public static void perfectShuffle02(int[] arr){
        int start = 0;
        int n = arr.length/2;
        while(n>1){
            int m = 1, k = 0;
            while((3*k -1) < 2*n){//计算环的个数,以及如果2 * n !=（3^k - 1）的情况的m的值
                k++;
            }
            m = (3*k-1)/2;
            //如果发生了m<n的情况，那么就需要再走圈之前吧m到n的数据移动到后面去,循环左移，我们用三段反转
            move(arr, m-1, m, n-1);
            //开始走圈
            for (int j = 0; j <k;j++){

            }
        }
    }

    public static void move(int[] arr, int start, int len, int end){

    }
}
