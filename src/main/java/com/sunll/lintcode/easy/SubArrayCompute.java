package com.sunll.lintcode.easy;

import java.util.Arrays;

/**
 * <p>desc: 求和最大&最小&最接近0的子数组</p>
 * 数组内数据有正负，子数组这里是连续的，如果是不连续的话，这个题没有意义了
 * 第一：求和最大的子数组？
 * 第二：求和最小的子数组？
 * 第三：求和最接近0的子数组？
 *
 * @author sunliangliang 2019-08-21 22:21
 * @version 1.0
 */
public class SubArrayCompute {
    public static void main(String[] args) {
        int[] arr = new int[]{1,-1,-1,4,1};
        System.out.println(Arrays.toString(maxSum(arr)));
        System.out.println(Arrays.toString(minSum(arr)));
        System.out.println(Arrays.toString(close0sum(arr)));
        System.out.println(close0sum2(arr));

    }

    /**
     * 求和最大的子数组
     * 思想：动态规划，每一步都保证和是最大的
     * @param arr
     * @return
     */
    public static int[] maxSum(int[] arr){
        int res = arr[0];
        int sum = arr[0];
        int left = 0, right = 0, index=0;
        for (int i = 1; i < arr.length; i++){
            if ((sum + arr[i]) < arr[i]){
                sum = arr[i];
                left = i;
            }else{
                sum = sum + arr[i];
            }
            if (res < sum){
                res = sum;
                right = i;
                index = left;
            }
        }
        int[] result = new int[right - index + 1];
        System.arraycopy(arr, index, result, 0, right - index + 1);
        return result;
    }

    /**
     * 求最小的子数组
     * 思想：不必多说，与最大和思想一样
     * @param arr
     * @return
     */
    public static int[] minSum(int[] arr){
        int sum = arr[0], res = arr[0];
        int left = 0, right = 0, index = 0;
        for (int i = 1; i < arr.length; i++){
            if ((sum + arr[i]) > arr[i]){
                sum = arr[i];
                left = i;
            }else{
                sum = sum + arr[i];
            }
            if (res > sum){
                res = sum;
                right = i;
                index = left;
            }
        }
        int[] result = new int[right - index + 1];
        System.arraycopy(arr, index, result, 0, right - index + 1);
        return result;
    }

    /**
     * 接近0的子数组
     * 第一种解决方法：动态规划
     * 思想：可以用刚才的动态规划的思想吗？保证每一步的sum的绝对值最小？
     * @param arr
     * @return
     */
    public static int[] close0sum(int[] arr){
        int sum = arr[0], res = arr[0];
        int left = 0, right = 0, index = 0;
        for (int i = 1; i < arr.length; i++){
            if (Math.abs(sum + arr[i]) > Math.abs(arr[i])){
                sum = arr[i];
                left = i;
            }else{
                sum = sum + arr[i];
            }
            if (Math.abs(res) > Math.abs(sum)){
                res = sum;
                right = i;
                index = left;
            }
        }
        int[] result = new int[right - index + 1];
        System.arraycopy(arr, index, result, 0, right - index + 1);
        return result;
    }

    /**
     * 接近0的子数组
     * 第二种解决方法
     * 思想：依次进行加和，然后排序，最后计算差值并取绝对值，找出差值最小的，结果就是两个结果的下表对应的原数组和最接近0
     * 其实是可以理解，依次加和之后，再排序，那么相邻的两个数再相减，得到的就是子数组的和，那么绝对值之后最小的那就是结果了
     * 这种方案时间复杂度O(n)，空间复杂度O(1)，但是要想知道子数组内容的话需要额外借助一些空间
     * @param arr
     * @return
     */
    public static int close0sum2(int[] arr){
        for (int i = 1; i < arr.length; i++){
            arr[i] = arr[i] + arr[i-1];
        }
        Arrays.sort(arr);
        int res = arr[0];
        for (int  i= 1; i < arr.length; i++){
            arr[i] = arr[i] - arr[i-1];
            res = Math.abs(res) > Math.abs(arr[i]) ? arr[i] : res;
        }
        return res;
    }
}
