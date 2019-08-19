package com.sunll.lintcode.hard;

import java.util.Arrays;

/**
 * <p>desc: 求字符串匹配的问题，经典的KMP算法和KMP变形</p>
 *
 * 暴力解法(不解释)，经典KMP，KMP变形
 *
 * @author sunliangliang 2019-08-18 17:24
 * @version 1.0
 */
public class KMP {
    public static void main(String[] args) {
        String str = "ababababcaagf";
        String pattern = "abababca";
        System.out.println(kmp01(str.toCharArray(), pattern.toCharArray()));
        System.out.println(kmp02(str.toCharArray(), pattern.toCharArray()));
    }

    /**
     * 经典的KMP算法（只求第一个匹配的字符串的索引即可）
     * 思想：求pattern字符串的next[j]数组，代表最大的相同前缀后缀长度
     * 比如pattern字符串是abab，那么相同前缀后缀数组为[-1,0,1,2]，而next数组为[-1,0,0,1]
     * 这样，每次发生不匹配的时候，pattern数组移动的位数是当前索引j-next[j]
     */
    public static int kmp01(char[] str, char[] pattern){
        int[] next = getNext(pattern);
        int start = 0;
        int index = 0;
        while(index < str.length && start < pattern.length){
            if (start == -1 || str[index] == pattern[start]){
                index++;
                start++;
            }else{
                start = next[start];
            }
        }
        if(start == pattern.length){
            return index - pattern.length;
        }else{
            return -1;
        }
    }

    /**
     * 注意这个获取next数组的方法，困扰我一个半小时，我真他妈想骂人！！！一定要搞明白，这个是最长前缀和后缀不是回文子串
     * @param pattern
     * @return
     */
    public static int[] getNext(char[] pattern){
        int[] res = new int[pattern.length+1];
        res[0] = -1;
        int j = -1;
        int i = 0;
        while(i < pattern.length){
            if (j == -1 || pattern[i] == pattern[j]){
                i++;
                j++;
                res[i] = j;
            }else{
                j = res[j];//这一步是关键，如果不想等，那么就回到之前最长前缀后缀的那个位置，进行下一个字符的匹配，卧槽了，这里绕了
            }
        }
        return res;
    }


    /**
     * kmp的变形，其实说白了就是剪枝
     * 思想：如果发现不匹配的时候，开始通过next数组进行移动pattern，但是如果pattern[next[j]] = pattern[j]的话，那么这一步做了无用功了
     * 所以思想就是每当不匹配的时候，移动之前进行一个判断即可
     * @param str
     * @param pattern
     * @return
     */
    public static int kmp02(char[] str, char[] pattern){
        int[] next = getNext(pattern);
        int start = 0;
        int index = 0;
        while(start < pattern.length && index < str.length){
            if (start == -1 || pattern[start] == str[index]){
                start++;
                index++;
            }else{
                if (pattern[start] == pattern[next[start]]){
                    start = next[next[start]];
                }
                start = next[start];
            }
        }
        if (start == pattern.length){
            return index - pattern.length;
        }else{
            return -1;
        }
    }
}
