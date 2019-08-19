package com.sunll.lintcode.normal;

import java.util.Arrays;

/**
 * <p>desc: 最长回文子串longest palindromic substring</p>
 *
 * 动态规划，manacher算法
 * @author sunliangliang 2019-08-18 22:19
 * @version 1.0
 */
public class LPS {
    public static void main(String[] args) {
        String str = "abccbaee";
        //System.out.println(lps01(str.toCharArray()));
        System.out.println(lps02(str.toCharArray()));
    }

    /**
     * 动态规划求解
     * 递推公式就是：如果s[i-1]==s[j+1] && dp[i][j]=true,那么dp[i-1][j+1] = true
     *     1	2	3	4	5
     *     1	a
     *     2	ad	d
     *     3	adc	dc	c
     *     4	adcd	dcd	cd	d
     *     5	adcdf	dcdf	cdf	df	f
     * @param str
     * @return
     */
    public static String lps01(char[] str){
        int left = 1;
        int right = 0;
        boolean[][] dp = new boolean[str.length][str.length];
        for (int i = 0; i<str.length; i++){
            for (int j = i; j>=0; j--){
                if(i-j >1){
                    if (dp[i-1][j+1] && str[i] == str[j]){
                        dp[i][j] = true;
                    }else {
                        dp[i][j] = false;
                    }
                }else{
                    dp[i][j] = str[i] == str[j];
                }
                if (dp[i][j] && right - left < j-i){
                    System.out.println(1);
                    left = j;
                    right = i;
                }
            }
        }
        return String.valueOf(str).substring(left, right+1);
    }

    /**
     * manacher马拉车算法
     * 第一步：处理字符串为#a#b#a#b.......c#a#，目的是统一奇偶性
     * 第二步：计算res[]
     * 具体算法思想自行百度吧
     * 时间复杂度O(n)
     * @return
     */
    public static String lps02(char[] str){
        char[] src = new char[str.length*2+1];
        int index = 0;
        for (int i =0; i< str.length;i++){
            src[index++] = '#';
            src[index++] = str[i];
        }
        src[index] = '#';
        //开始算法
        int[] res = new int[str.length*2+1];
        int resIndex = 0;
        int maxLen = 1;
        res[0] = 1;
        int right = 0;//历史最长回文子串达到的最右侧索引
        int mid = 0;//历史最长回文子串的对称中间点索引
        for (int i=1; i<src.length; i++){
            //分两种情况
            //第一种情况：如果i<=right
            if (i <= right){
                //如果左侧回文子串的长度没有超过当前最大回文子串的范围的话，直接res[i] = res[left]；
                if (right-i >= res[mid-(i-mid)]){
                    res[i] = res[mid-(i-mid)];
                }else{
                    while(src[right+1] == src[i-(right+1-i)]){//如果超过了界限，那么从界限后面开始进行匹配
                        right++;
                    }
                }
            }else{//第二种情况：如果i>right的话，啥也不用管，重新进行回文子串匹配即可
                right = i;
                while(i-(right+1-i)>=0 && right+1<=src.length-1 && src[right+1] == src[i-(right+1-i)]){
                    right++;
                }
            }
            mid = i;
            maxLen = maxLen<(right-i+1)? (right-i+1):maxLen;
            resIndex = maxLen<(right-i+1)? (i-(right-i)):resIndex;
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0; i< maxLen*2+1;i++){
            if (src[resIndex] != '#'){
                sb.append(src[resIndex]);
            }
            resIndex++;
        }
        return sb.toString();
    }
}
