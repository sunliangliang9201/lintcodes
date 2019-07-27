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
        shellSort(arr);

        System.out.println(Arrays.toString(arr));
    }


    /**
     * 冒泡排序
     * 时间复杂度 O(n2) 空间复杂度O(1) 稳定排序
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
     * @param arr 输入数组
     */
    public static void selectSort(int[] arr){

    }
}
