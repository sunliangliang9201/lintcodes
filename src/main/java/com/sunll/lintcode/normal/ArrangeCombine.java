package com.sunll.lintcode.normal;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p>desc: 排列组合各种问题</p>
 * 给定一个int数组
 * 组合：
 *      1.从N个数中选取M个数的所有组合？
 *      2.从N个数中选取任意个数的所有组合？
 *      3.从N个数中选取M个数，和为sum的所有组合？（常考）关于这个问题，我们延伸一下，比如1元 3元 5元 凑20元有多少种组合方式？或者说硬币数最少的组合，这个问题的不同之处在于不限定个数。关于这类问题，专门整理一下吧
 *      4.从N个数中选取任意个数，和为sum的所有组合？（在3的基础上加一层循环即可）
 *      5.从N个数中选取任意个数，和为sum的最短组合？（在4的基础上，再记录一个minLen即可）
 *
 * 排列：
 *      字符串的全排列（需要注意重复元素的问题！！）
 *      其实对于全排列，核心思想就是递归+交换元素。如果要考虑重复元素的话就需要再复杂一点。
 *
 * @author sunliangliang 2019-08-14 22:58
 * @version 1.0
 */
public class ArrangeCombine {
    public static void main(String[] args) {
        int[] arr = new int[]{1,3,5,2};
        //combine01(arr, 3, 0, null);
        //combine02(arr, 0, null);
        //combine03(arr,3, 0, null, 9);
//        for (int[] i: res){
//            System.out.println(Arrays.toString(i));
//        }

        String a = "abcd";
        String b = "abac";
        char[] chars = b.toCharArray();
        arrange01(chars,0, chars.length);
        for (String i: result){
            System.out.println(i);
        }


    }
    private static ArrayList<int[]> res = new ArrayList<>();//组合的结果集，公用
    private static ArrayList<String> result = new ArrayList<>();//排列的结果集，公用

    /**
     * 1.从N个数中选取M个数的所有组合？
     * 思想：递归的方式，还算简单,取出一个元素，再从剩下的元素中取一个，再从剩下的取。。。直到num个元素即可。
     * @param arr
     * @param num
     * @return
     */
    public static void combine01(int[] arr,int num, int start, int[] tmp){
        if (num <= 0 || arr.length < num){//递归退出条件，取出的元素个数够了等
            res.add(Arrays.copyOf(tmp,tmp.length));
            return;
        }
        if (tmp == null){
            tmp = new int[num];
        }
        for (int i = start; i<=arr.length-num; i++){//这里的关键就在于，-num的作用就是从剩下的多少个数中取出一个
            tmp[num-1] = arr[i];
            combine01(arr, num-1, i+1, tmp);//这里也很重要，从i+1开始取
        }
    }

    /**
     * 2.从N个数中选取任意个数的所有组合？
     * 同样还是递归，只不过不是固定个数了
     */
    public static void combine02(int[] arr, int start, int[] tmp){
        for (int i = 1; i<=arr.length;i++){
            combine01(arr,i,0,tmp);
        }
    }

    /**
     * 3.从N个数中选取M个数，和为sum的所有组合？
     * 这个问题是经常考的！
     * 其实在题目1中在add到结果集之前对sum进行一个判断就可以了，但是会有很多重复计算，可以适当加一些剪枝处理。
     */
    public static void combine03(int[] arr, int num, int start, int[] tmp, int total){
        if (tmp == null) tmp = new int[num];
        if(num <= 0 || arr.length < num){
           if (sum(tmp) == total) res.add(Arrays.copyOf(tmp,tmp.length));
           return;
        }
        for (int i = start; i<=arr.length-num; i++){
            tmp[num-1] = arr[i];
            combine03(arr, num-1, i+1, tmp, total);
        }
    }

    public static int sum(int[] arr){
        int sum = 0;
        for(int i: arr){
            sum += i;
        }
        return sum;
    }


    //=======================
    //以下是排列

    /**
     * 字符串的全排列（注意元素重复问题）
     * @param
     */
    public static void arrange01(char[] chars, int start, int end){
        if(start==end) result.add(String.valueOf(chars));
        for(int i = start; i< end; i++){
            if (isSwap(chars, start,i)){//这一步的判断很关键
                swap(chars, start, i);
                arrange01(chars, start+1, end);
                swap(chars, start, i);
            }
        }
    }

    public static void swap(char[] arr, int i, int j){
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static boolean isSwap(char[] chars, int start, int end){
        for (int i=start; i<end; i++){
            if (chars[i] == chars[end]){
                return false;
            }
        }
        return true;
    }
}
