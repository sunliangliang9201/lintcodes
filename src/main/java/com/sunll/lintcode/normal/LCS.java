package com.sunll.lintcode.normal;

/**
 * 最长公共子序列问题和最长公共字串问题
 * longest common sequence & longest common substring
 * 第一：最长公共字串指的是连续的，这种情况比较简单！直接用动态规划思想即可，动态规划的意思是每一步都得到最优值
 * 第二：最长公共子序列指的是不连续的，这种情况稍微复杂一点，也可以用动态规划，具体区别请看代码。
 * @author sunliangliang 2019/5/29
 * @version 1.0
 */
public class LCS {
    public static void main(String[] args) {

        String a = "abcdef";
        String b = "adbcddf";
//        System.out.println(lcSubstring(a, b));
        System.out.println(lcSequence(a, b));
    }

    /**
     * 最长公共字串
     * 思想:这个相对就简单了，直接利用动态规划
     *     a b c d e f
     *   0 0 0 0 0 0 0
     * a 0 1 0 0 0 0 0
     * d 0 0 0 0 1 0 0
     * b 0 0 1 0 0 0 0
     * c 0 0 0 2 0 0 0
     * d 0 0 0 0 3 0 0
     * d 0 0 0 0 1 0 0
     * 可以看出最长子串长度是3
     * @param a
     * @param b
     * @return
     */
    public static String lcSubstring(String a, String b){
        int[][] res = new int[a.length()+1][b.length()+1];
        int max = 0;
        int index = 0;
        for (int i = 0; i <= a.length(); i++){
            for (int j = 0; j <= b.length(); j++){
                if (i == 0 || j == 0){
                    res[i][j] = 0;
                }else if (a.charAt(i-1) == b.charAt(j-1)){
                    res[i][j] = res[i-1][j-1] + 1;
                    if (res[i][j] > max){
                        max = res[i][j];
                        index = i;
                    }
                }
            }
        }
        return a.substring(index - max,index);
    }

    /**
     * 最长公共序列
     * 难点就在于不要求连续的字串，那么在记录res的时候就不一样了，同时回溯的时候也是有技巧的
     * 但是也很好理解，公共子串的递推是res[i][j]一定是res[i-1][j-1]+1来的，不然就记录为0
     * 此时的递推公式是res[i][j]可能是res[i-1][j]过来的也可能是res[i][j-1]来的，而且由于不连续问题，后续的值不会回归0
     *     a b c d e f
     *   0 0 0 0 0 0 0
     * a 0 1 1 1 1 1 1
     * d 0 1 1 1 2 2 2
     * b 0 1 2 2 2 2 2
     * c 0 1 1 3 3 3 3
     * d 0 1 1 3 4 4 4
     * d 0 1 1 3 4 4 4
     * f 0 1 1 3 4 4 5
     * @param a
     * @param b
     * @return
     */
    private static String lcSequence(String a, String b) {
        int[][] res = new int[a.length()+1][b.length()+1];
        int[][] flag = new int[a.length()+1][b.length()+1];//1 代表是由i-1,j-1来的； 2代表从上边来的； 3代表从左边来的
        for (int i = 0 ;i <= a.length(); i++){
            for (int j = 0; j <= b.length(); j++){
                if (i == 0 || j == 0){
                    res[i][j] = 0;
                }else if(a.charAt(i-1) == b.charAt(j-1)){
                    res[i][j] = res[i-1][j-1] + 1;
                    flag[i][j] = 1;
                }else{
                    res[i][j] = res[i-1][j] > res[i][j-1]? res[i-1][j]:res[i][j-1];
                    flag[i][j] = res[i-1][j] > res[i][j-1]? 2:3;
                }
            }
        }
        //最长的长度肯定是res[a.length][b.length]了，现在需要把结果回溯出来,此时就需要另外一个flag数组了，因为我们要知道是从哪条线上增加到该节点的
        StringBuilder result = new StringBuilder();
        int i = a.length();
        int j = b.length();
        while(i > 0 && j > 0){
            if (flag[i][j] == 1){
                result.append(a.charAt(i-1));
                i--;
                j--;
            }else if(flag[i][j] == 2){
                i--;
            }else{
                j--;
            }
        }
        return result.reverse().toString();
    }
}
