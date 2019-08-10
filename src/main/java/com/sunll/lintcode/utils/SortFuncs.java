package com.sunll.lintcode.utils;

import java.util.Arrays;

/**
 * 排序实现，从简单到复杂
 *
 * @author sunliangliang 2019/5/29
 * @version 1.0
 */
public class SortFuncs {

    public static void main(String[] args) {
        int[] arr = new int[10];
        for(int i=0; i<arr.length; i++){
            arr[i] = (int)(Math.random() * 100);
        }
        System.out.println(Arrays.toString(arr));

        //Test
        //bubbleSort(arr);
        //insertSort(arr);
        //shellSort(arr);
        selectSort(arr);

        System.out.println(Arrays.toString(arr));
    }


    /**
     * 冒泡排序
     * 时间复杂度 O(n2) 空间复杂度O(1) 稳定排序
     * 思想：不必多少，给予比较和移动操作，每一轮都选出最大的来移动到最后
     * @param arr 输入数组
     */
    public static void bubbleSort(int[] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length - i - 1; j++){
                if(arr[j] > arr[j+1]){
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
    }


    /**
     * 简单插入排序
     * 时间复杂度 O(n2) 空间复杂度O(1) 稳定排序
     * 思想：其实与冒泡、选择排序差不多，直接插入的思想是每次将一个新的数插入到已经排序的序列中，挨个比较
     * @param arr 输入数组
     */
    public static void insertSort(int[] arr){
        for(int i = 1; i < arr.length; i++){
            int cursor = i - 1;
            int tmp = arr[i];
            while(cursor >= 0 && arr[cursor] > tmp){
                arr[cursor+1] = arr[cursor];
                cursor--;
            }
            arr[cursor + 1] = tmp;
        }
    }

    /**
     * 希尔排序，对插入排序的优化，每一步减少步长，所以也叫作缩减增量插入排序
     * 时间复杂度O(n1.25) 空间复杂度O(1)  不稳定排序
     * 由于跟步长序列选取有关没有一个准确的时间复杂度
     * 但是希尔排序是第一批突破n2复杂度的基于比较的排序方式
     * 思想：希尔排序是直接插入排序的优化，另外一种的插入优化是二分查找优化
     * 想法就是跳着比较，因为插入排序的性能跟是否有序有很大关系，逐渐减小跳的间隔，直到=1结束。
     * @param arr 输入数组
     */
    public static void shellSort(int[] arr){
        int len = arr.length / 2;
        while(len > 0){
            for(int i=0; i< len; i++){
                for(int j= i+len; j<arr.length; j=j+len){
                    int cursor = j - len;
                    int tmp = arr[j];
                    while(cursor >= 0 && arr[cursor] > tmp){
                        arr[cursor + len] = arr[cursor];
                        cursor -= len;
                    }
                    arr[cursor + len] = tmp;
                }
            }
            len = len / 2;
        }
    }

    /**
     * 选择排序
     * 时间复杂度 O(n2) 空间复杂度 O(n2) 稳定排序
     * 思想：选择排序、插入排序、冒泡排序都差不多，选择排序的思想就是每次从带排序序列找出最小的来，它如果
     * 是带排序序列的第一个，不操作，如果不是就跟第一个交换。
     * @param arr 输入数组
     */
    public static void selectSort(int[] arr){
        for (int i=0; i<arr.length; i++){
            int cursor = i;
            int tmp = arr[cursor];
            for (int j=i; j<arr.length; j++){
                if (tmp > arr[j]){
                    tmp = arr[j];
                    cursor = j;
                }
            }
            arr[cursor] = arr[i];
            arr[i] = tmp;
        }
    }

    /**
     * 堆排序
     * 以大顶推为例 时间复杂度O(nlogn) 空间复杂度O(1) 不稳定排序
     * 思想：首先从最后的非叶子节点开始建立堆，然后把根结点最大和最后一个叶子结点交换，然后调整堆（不包含最后那个了）
     * @param arr
     */
    public static void heapSort(int[] arr){
        for (int i = arr.length/2; i>=0; i--){
            heapAjust();
        }

    }

    //调整堆
    private static void heapAjust() {
    }
}
