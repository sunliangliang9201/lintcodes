package com.sunll.lintcode.normal;

import java.util.Arrays;

/**
 * <p>desc: 最长递增子序列 和最长连续递增子序列</p>
 *
 * @author sunliangliang 2019-08-12 22:22
 * @version 1.0
 */
public class LIS {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,4,5,3,8,9};
        System.out.println(Arrays.toString(lis01(arr)));
        System.out.println(Arrays.toString(lis02(arr)));
        System.out.println(lis03(arr));
        System.out.println(Arrays.toString(lisubnums(arr)));
    }

    /**
     * 最长递增子序列（不连续）
     * 第一种方式：a与排序后的a进行LCS得到的结果就是最长递增子序列！时间复杂度就是LCS的时间复杂度O(n2)
     * 但是！！！如果使用LCS的思想来解决的话需要一个条件就是不能有重复元素！不然的话重复元素在LCS中是取出来的，但是相等不满足递增
     */
    public static int[] lis01(int[] arr){
        int[] arr2 = Arrays.copyOf(arr,arr.length);
        Arrays.sort(arr2);
        return LCS.lcSequence(arr, arr2);
    }

    /**
     * 最长递增子序列（不连续）
     * 第二中方式：动态规划时间复杂度O(n2)
     * 思想：非常简单，其实动态规划很好理解，关键就是在于什么时候应用！
     * 设长度为N的数组为{a0，a1, a2, ...an-1)，则假定以aj结尾的数组序列的最长递增子序列长度为L(j)，则L(j)={ max(L(i))+1, i<j且a[i]<a[j] }。
     * 也就是说，我们需要遍历在j之前的所有位置i(从0到j-1)，找出满足条件a[i]<a[j]的L(i)，
     * 求出max(L(i))+1即为L(j)的值。最后，我们遍历所有的L(j)（从0到N-1），找出最大值即为最大递增子序列。时间复杂度为O(N^2)。
     *
     * 另外：上面的L(i)数组只记录了最大长度，如果想要结果的话，需要哦再记录一个数组用于记录L(i)的长度从那个索引计算而来的，这样的话就可以得出
     * 最长递增子序列了。
     *
     * ！！！优化：如果用一个hashmap保存历史，那么就会省去很多重复计算
     * @param
     */
    public static int[] lis02(int[] arr){
        int[] flag = new int[arr.length];
        for(int i: flag){
            i = -1;
        }
        int[] res = new int[arr.length];
        res[0] = 1;
        for (int i = 1; i<arr.length; i++){
            int cursor = i-1;
            int max = res[i];
            int maxIndex = i;
            while(cursor >= 0){
                if(arr[cursor] < arr[i]){
                    if (res[cursor] > max){
                        max = res[cursor];
                        maxIndex = cursor;
                    }
                }
                cursor--;
            }
            res[i] = max+1;
            flag[i] = maxIndex;
        }
        int maxLen = res[0];
        int index = 0;
        for (int i = 1; i<res.length;i++){
            if (maxLen < i){
                maxLen = i;
                index = i;
            }
        }
        int[] result = new int[maxLen];
        for (int j=result.length-1; j>=0; j--){
            result[j] = arr[index];
            index = flag[index];
        }
        return result;
    }

    /**
     * 最长递增子序列（不连续）
     * 第三种：贪心+二分查找
     * 思想：这种方式的技巧就是尾部元素始终是最长递增子序列的尾部元素，而如果下个元素小于尾部元素的话，就用这个元素替换有序序列中的第一个比他大的元素
     * 这样做的结果就是当所有元素走完时就可以得到最终的长度，但是有一个缺点就是得不到最长递增子序列的真实序列。
     * @param
     */
    public static int lis03(int[] arr){
        int[] res = new int[arr.length];
        res[0] = arr[0];
        int len = 1;
        for (int i = 1; i<arr.length; i++){
            if (res[i-1] < arr[i]){
                res[len++] = arr[i];
            }else if(res[i-1] == arr[i]){
                continue;
            }else{
                int index = binarySearch(res, arr[i],len-1);
                if (index == -1) continue;
                res[index] = arr[i];
            }
        }
        return len;
    }

    public static int binarySearch(int[] arr, int key, int end){
        int start = 0;
        while(start <= end){
            int index = (start+end)/2;
            if(arr[index] == key){
                return -1;
            }else if(arr[index] < key){
                start = index+1;
            }else{
                end = index-1;
            }
        }
        return start;
    }

    /**
     * 最长递增子序列（连续）
     * 思想：千万不要想复杂了！用一个count计数即可，一旦不递增了，就重新来。
     * @return
     */
    public static int[] lisubnums(int[] arr){
        int res = 1;
        int index = 0;
        int count= 1;
        for (int i=1; i<arr.length; i++){
            if (arr[i] > arr[i-1]){
                if (count >= res){
                    count++;
                    res = count;
                    index = i;
                }else{
                    count++;
                }
            }else{
                count = 1;
            }
        }
        int[] result = new int[res];
        System.arraycopy(arr,index-res+1,result,0,res);
        return result;
    }
}
