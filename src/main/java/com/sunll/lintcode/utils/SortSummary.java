package com.sunll.lintcode.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *  @author sunliangliang
 *  @version 1.1
 *  sort funcs
 */
public class SortSummary {
    public static void main(String[] args) {
        int[] arr = new int[10];
        for(int i = 0; i < arr.length; i++){
            arr[i] = (int)(Math.random() * 100);
        }
        System.out.println(Arrays.toString(arr));
        //insertionSort(arr);
        //shellSort(arr);
        //selectSort(arr);
        //bubbleSort(arr);
        //heatSort(arr);
        //quickSort(arr, 0, arr.length - 1);
        //mergeSort(arr, 0 ,arr.length - 1);
        //radixSort(arr);
        //countingRadixSort(arr);
        //ucketSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * insertion sort
     * time complexity: O(n2), space complexity: O(1)
     * stable
     * @param arr data
     */
    public static void insertionSort(int[] arr){
        int insertNum;
        int tmp;
        for(int i = 1; i < arr.length; i++){
            insertNum = arr[i];
            tmp = i;
            while(tmp > 0){
                if(arr[tmp - 1] <= insertNum){
                    break;
                }
                arr[tmp] = arr[--tmp];
            }
            arr[tmp] = insertNum;
        }
    }

    /**
     * shlel sort ,is insertion sort`s optimization
     * time commplexity: O(nlogn2), space complexity: O(1)
     * unstable
     * @param arr data
     */
    public static void shellSort(int[] arr){
        int len = arr.length / 2;
        while(len> 0){
            for(int i = 0; i < len; i++){
                for(int j = len + i; j < arr.length; j += len){
                    int k = j - len;
                    int tmp = arr[j];
                    while(k >= 0 && tmp < arr[k]){
                        arr[k + len] = arr[k];
                        k -= len;
                    }
                    arr[k + len] = tmp;
                }
            }
            len = len / 2;
        }
    }

    /**
     * select sort
     * time complexity: O(n2) ,space complexity: O(1)
     * stable
     * @param arr data
     */
    public static void selectSort(int[] arr){
        int tmp;
        int position;
        for(int i = 0; i < arr.length; i++){
            tmp = arr[i];
            position = i;
            for(int j = i; j< arr.length; j++){
                if(arr[j] < tmp){
                    tmp = arr[j];
                    position = j;
                }
            }
            arr[position] = arr[i];
            arr[i] = tmp;
        }
    }

    /**
     * heat sort, is select sort`s optimization
     * time complexity: O(nlog2n), space complexity: O(nlog2n)
     *unstable
     * @param arr data
     */
    public static void heatSort(int[] arr){
        for(int i = 0; i < arr.length - 1; i++){
            buildMaxHeat(arr, arr.length - 1 - i);
            swap(arr, 0, arr.length - 1 - i);
        }
    }
    //构建最大堆
    private static void buildMaxHeat(int[] arr, int lastIndex){
        for(int i = (lastIndex - 1)/2; i >= 0; i--){
            int k = i;
            while(k * 2 +1 <= lastIndex){
                int  biggrtIndex = 2*k+1;
                if(biggrtIndex < lastIndex){
                    if(arr[biggrtIndex] < arr[biggrtIndex + 1]){
                        biggrtIndex++;
                    }
                }
                if(arr[k] < arr[biggrtIndex]){
                    swap(arr, k, biggrtIndex);
                    k = biggrtIndex;
                }else{
                    break;
                }
            }
        }
    }
    //交换位置
    private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * bubble sort
     * O(n2)
     * stable
     * @param arr data
     */
    public static void bubbleSort(int[] arr){
        int tmp;
        for(int i = 0; i< arr.length; i++){
            for(int j = 0; j < arr.length - i -1; j++){
                if(arr[j] > arr[j+1]){
                    tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
    }

    /**
     * quick sort
     * time complexity: O(n*log2n), space complexity: O(nlog2n)
     * unstable
     * @param arr data
     */
    public static void quickSort(int[] arr, int start, int end){
        //三样中值法
        if(start < end){
            int baseNum = arr[start + (end - start)/2];
            int midNum;
            int i = start;
            int j = end;
            do{
                while(arr[i] < baseNum && i < end){
                    i++;
                }
                while(arr[j] > baseNum && j > start){
                    j--;
                }
                if(i <= j){
                    midNum = arr[i];
                    arr[i] = arr[j];
                    arr[j] = midNum;
                    i++;
                    j--;
                }
            }while(i <= j);
            if(start < j){
                quickSort(arr, start, j);
            }
            if(end > i){
                quickSort(arr, i, end);
            }
        }
    }

    /**
     * merge sort
     * time complexity: O(nlogn), space complexity: unknown
     * stable
     * @param arr data
     */
    public static void mergeSort(int[] arr, int start, int end){
        int mid = (end + start)/2;
        if(start < end){
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }
    //归并
    public static void merge(int[] arr, int start, int mid, int end){
        int[] tmpArr = new int[end - start + 1];
        int i = start;
        int j = mid + 1;
        int k = 0;
        while(i <= mid && j <= end){
            if(arr[i] < arr[j]){
                tmpArr[k++] = arr[i++];
            }else{
                tmpArr[k++] = arr[j++];
            }
        }
        while(i <= mid){
            tmpArr[k++] = arr[i++];
        }
        while(j <= end){
            tmpArr[k++] = arr[j++];
        }
        for(int x = 0; x < tmpArr.length; x++){
            arr[x + start] = tmpArr[x];
        }
    }

    /**
     * counting radix sort
     * linear time cost
     * @param arr data
     */
    public static void countingRadixSort(int[] arr){
        int max = arr[0];
        int min = arr[0];
        for (int i = 0; i < arr.length; i++){
            max = (max < arr[i]) ? arr[i]: max;
            min = (min > arr[i]) ? arr[i]: min;
        }
        int[] bucket = new int[max - min + 1];
        for (int i = 0; i < arr.length; i++){
            bucket[arr[i] - min] = bucket[arr[i] - min] + 1;
        }
        int count = 0;
        for (int i = 0; i < bucket.length; i++){
            while (bucket[i] > 0){
                arr[count++] = i + min;
                bucket[i]--;
            }
        }
    }

    /**
     * radix sort
     * time complexity: O(nlog(r)n), space complexity: O(kn)
     * stable
     * @param arr data
     */
    public static void radixSort(int[] arr){
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int count = 0;
        while (max > 0) {
            max /= 10;
            count++;
        }
        ArrayList<ArrayList<Integer>> queue = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < 10; i++){
            ArrayList<Integer> queue1 = new ArrayList<Integer>();
            queue.add(queue1);
        }
        for (int i = 0; i < count; i++){
            for (int j = 0; j < arr.length; j++){
                int x = arr[j] % (int)Math.pow(10, i + 1) / (int)Math.pow(10, i);
                ArrayList<Integer> queue2 = queue.get(x);
                queue2.add(arr[j]);
                queue.set(x, queue2);
            }
            int time = 0;
            for (int k = 0; k < 10; k++){
                while (queue.get(k).size() > 0) {
                    ArrayList<Integer> queue3 = queue.get(k);
                    arr[time] = queue3.get(0);
                    queue3.remove(0);
                    time++;
                }
            }
        }
    }

    /**
     * bucket sort
     *
     * @param arr data
     */
    public static void bucketSort(int[] arr){
        int max = arr[0];
        int min = arr[0];
        for (int i = 0; i < arr.length; i++){
            max = (max < arr[i]) ? arr[i]: max;
            min = (min > arr[i]) ? arr[i]: min;
        }
        int bucketNum = (max -min) / arr.length + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < bucketNum; i++){
            bucketArr.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < arr.length; i++){
            int num = (arr[i] - min) / arr.length;
            bucketArr.get(num).add(arr[i]);
        }
        for (int i = 0; i < bucketNum; i++){
            Collections.sort(bucketArr.get(i));
        }
        int count = 0;
        for (int i = 0; i < bucketNum; i++){
            for (int j = 0; j < bucketArr.get(i).size(); j++){
                arr[count++] = bucketArr.get(i).get(j);
            }
        }
    }
}
