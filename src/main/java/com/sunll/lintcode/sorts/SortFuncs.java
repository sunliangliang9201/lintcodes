package com.sunll.lintcode.sorts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

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
        //selectSort(arr);
        //heapSort(arr);
        //quickSort(arr, 0 ,arr.length-1);
        //mergeSort(arr, 0, arr.length -1);
        //countSort(arr);
        //bucketSort(arr);
        //radixSort(arr);

        //关于快排的重复元素问题
        int[] arr1 = new int[]{1,2,3,4,5,4,5,4,1,9,11};
        int[] arr2 = new int[]{1,2,3,4,5,4,5,4,1,9,11};
        //quickSort01(arr1, 0, arr.length-1);//处理重复元素有问题
        quickSort02(arr1, 0 ,arr.length-1);//可以处理存在重复元素问题
        quickSort03(arr2, 0 ,arr.length-1);//可以处理存在重复元素问题
        //System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
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
        for (int i = arr.length/2; i>=0; i--){//从最后非叶子结点开始建堆；
            heapAjust(arr, i, arr.length);
        }
        for (int i=arr.length-1; i>0; i--){//每次将根结点（最大值）和最后那个节点交换，然后就可以调整堆（排除最后那个，因为后面的已经有序了）
            swap(arr, 0, i);
            heapAjust(arr, 0, i);
        }
        //其实两步可以并成一步，无所谓了
    }

    //调整堆
    private static void heapAjust(int[] arr, int start, int end){
        int k = start;
        int tmp = arr[start];
        int index = 2*k + 1;
        while(index < end){
            if(index+1 < end){
                if(arr[index] < arr[index+1]){
                    index += 1;
                }
            }
            if(arr[index] > tmp){
                arr[k] = arr[index];
                k = index;
                index = 2*k + 1;
            }else{
                break;
            }
        }
        arr[k] = tmp;//循环下去，直到两个子节点的值都小于父节点的值时，跳出循环，然后将start索引处的值赋给k索引处即可。
    }

    //交换元素
    public static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 快速排序01 没有重复元素的情况，我们可以看出如果有重复元素的下面的代码就会进入死循环
     * 时间复杂度 O(nlogn) 空间复杂度O(1) 不稳定排序
     * @param arr
     */
    public static void quickSort01(int[] arr, int start, int end){
        if (start >= end){
            return;
        }else{
            int midNum = arr[(start + end)/2];
            int i = start;
            int j = end;
            while(j > i){
                while(i < j && arr[j] > midNum){
                    j--;
                }
                while(i < j && arr[i] < midNum){
                    i++;
                }
                if(i < j){
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
            arr[i] = midNum;
            quickSort01(arr, start, i-1);
            quickSort01(arr, j+1, end);
        }
    }

    /**
     * 快速排序02 存在重复元素的情况（360，百度，小米）
     * 时间复杂度 O(nlogn) 空间复杂度O(1) 不稳定排序
     * @param arr
     */
    public static void quickSort02(int[] arr, int start, int end){
        if (start >= end){
            return;
        }else{
            int midNum = arr[start];
            int i = start;
            int j = end;
            while(j > i){
                while(i < j && arr[j] >= midNum){
                    j--;
                }
                arr[i] = arr[j];
                while(i < j && arr[i] <= midNum){
                    i++;
                }
                arr[j] = arr[i];
            }
            arr[i] = midNum;
            quickSort02(arr, start, i-1);
            quickSort02(arr, j+1, end);
        }
    }

    /**
     * 快速排序03 存在大量重复元素的情况下的优化，也就是用基准面将数据分为三部分，小于基准面，大于基准面，等于基准面，把等于基准面的那部分数据不进行下一次排序
     * 这样就减少了无用排序
     * 时间复杂度 O(nlogn) 空间复杂度O(1) 不稳定排序
     * @param arr
     */
    public static void quickSort03(int[] arr, int start, int end){
        if (start >= end){
            return;
        }else{
            int midNum = arr[start];
            int i = start;
            int j = end;
            int k = start;
            while(j >= i){
                if (arr[i] < midNum){
                    int tmp = arr[i];
                    arr[i] = arr[k];
                    arr[k] = tmp;
                    i++;
                    k++;
                }else if(arr[i] > midNum){
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                    j--;
                }else{
                    i++;
                }
            }
            quickSort03(arr, start, k);
            quickSort03(arr, j+1, end);
        }
    }

    /**
     * 归并排序
     * 时间复杂度O(nlogn) 空间复杂度O(n) 稳定排序
     * 思想：化繁为简 化散为整，一开始全部打散，两两一组，然后一组内比较，然后俩俩合并
     * @param arr
     */
    public static void mergeSort(int[] arr, int start, int end){
        if (start < end){
            int mid = (start+end)/2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid+1, end);
            merge(arr, start, mid, end);
        }
    }
    //俩俩合并
    public static void merge(int[] arr, int start, int mid, int end){
        int[] res = new int[end - start +1];
        int index = 0;
        int i = start;
        int j = mid + 1;
        while(i <= mid && j <= end){//开始合并，从i和i+mid+1开始比较，然后放入新数组
            if (arr[i] <= arr[j]){
                res[index++] = arr[i++];
            }else{
                res[index++] = arr[j++];
            }
        }
        while(i <= mid){//上面循环结束时，肯定有一个数组还剩元素，此时把剩下的加到res后面即可
            res[index++] = arr[i++];
        }
        while(j <= end){
            res[index++] = arr[j++];
        }
        for (int ii = start; ii <= end; ii++){//把数据覆盖到arr中
            arr[ii] = res[ii - start];
        }
    }

    /**
     * 计数排序
     * 时间复杂度O(n+max-min) 空间复杂度O(max-min) 不稳定
     * 思想：三次遍历，第一次找出max和min，知道了需要申请多大的数组；第二次把数据读入数组，在相应的位置上标1或者大于1，表示个数；第三次读出即可
     * @param arr
     */
    public static void countSort(int[] arr){
        int max = arr[0];
        int min = arr[0];
        for (int i: arr) {
            max = i>max? i:max;
            min = i<min? i:min;
        }
        int[] tmp = new int[max-min+1];
        for (int i = 0; i<arr.length; i++){
            tmp[arr[i] - min] = tmp[arr[i] - min]+1;
        }
        int count = 0;
        for (int j = 0; j< tmp.length; j++){
            while(tmp[j]>0){
                arr[count] = j+min;
                tmp[j]--;
                count++;
            }
        }
    }

    /**
     * 桶排序
     * 时间复杂度O(n+n(log(n/k)))假设桶内排序是快速排序 空间复杂度O(n+k) 不稳定
     * 思想：跟计数排序相当类似，只不过一个槽位存多个真实值并槽内进行排序
     * @param arr
     */
    public static void bucketSort(int[] arr){
        int max = arr[0];
        int min = arr[0];
        for (int i: arr) {
            max = i>max? i:max;
            min = i<min? i:min;
        }
        int bucketNum = (max -min) / arr.length + 1;
        ArrayList<LinkedList<Integer>> bucketArr = new ArrayList<LinkedList<Integer>>();
        for (int i = 0; i < bucketNum; i++){
            bucketArr.add(new LinkedList<Integer>());
        }
        for (int i = 0; i < arr.length; i++){
            int num = (arr[i] - min) / arr.length;
            bucketArr.get(num).add(arr[i]);
        }
        for (int i = 0; i < bucketNum; i++){//桶内排序
            Collections.sort(bucketArr.get(i));
        }
        int count = 0;
        for (int i = 0; i < bucketNum; i++){
            for (int j = 0; j < bucketArr.get(i).size(); j++){
                arr[count++] = bucketArr.get(i).get(j);
            }
        }
    }

    /**
     * 基数排序
     * 时间复杂度O(d*(n+k)) 空间复杂度O(n+k)
     * 思想：根据数的个十百千位来排序的，两种，一种是从低位带高位，另一种从高位到低位。我们看从低位到高位
     * 各位排序的时候，个数数是多少就进入那个桶里面，这样这个数据有一定的顺序了。再进行十位排序，十位是几就进入对应的桶，现在更加有序了，
     * 直到最后全部有序
     * @param arr
     */
    public static void radixSort(int[] arr){
        int max = arr[0];
        for (int i: arr) {
            max = max<i? i:max;
        }
        int count = 0;
        while(max > 0){
            max = max /10;
            count++;
        }
        ArrayList<LinkedList<Integer>> buckets = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            buckets.add(i, new LinkedList<Integer>());
        }
        for (int i=0; i<count; i++){
            for (int j=0; j<arr.length; j++){
                int index =  arr[j] % (int)Math.pow(10, i+1) / (int)Math.pow(10, i);//关键点就是每一轮比较完毕插入数据后，看下面的注释
                buckets.get(index).add(arr[j]);
            }
            int time = 0;
            for(int j=0; j<10; j++){//注意这里：每轮完毕之后都会将数据重新读出覆盖arr，并删除buckets中桶中的数据，然后进行更高位的比较
                while(buckets.get(j).size()>0){
                    arr[time++] = buckets.get(j).pop();
                }
            }
        }
    }
}
