package com.sunll.lintcode.normal;


import java.util.*;

/**
 * <p>desc: 最长无重复字串</p>
 * 输入是abcdabc，输出是4或者abcd
 * 暴力方法：O(n2),都可以想到的
 * 动态规划方法：借助空间O(n)的链表或者set或者hashmap或者数组
 * 动态规划方法：用指针记录，不太容易理解
 * @author sunliangliang 2019-08-21 09:08
 * @version 1.0
 */
public class LS {
    public static void main(String[] args) {
        String str = "abcdabc";
        System.out.println(ls01(str));
        System.out.println(ls02(str));
        System.out.println(ls03(str));
    }

    /**
     * 第一种方案
     * 动态规划：借助O(256)空间来实现
     * 思想：使用int[256]，借助额外空间
     * 每次读取一个字符，先去int[256]中找，如果没有的话left指针不变；如果int[256]中存在了，那么left指针进行移动最后不管int[256]中存在与否，既然读取到了就要加到int[256]中
     * 整个过程中结果是动态规划的。
     * @param str
     * @return
     */
    public static String ls01(String str){
        int[] chars = new int[256];//ascii码表最多表示256个字符（扩展ascii表是256，一般的是128个）
        int left = 0, res = 0;
        for (int i = 0; i<str.length(); i++){
            left = left < chars[str.charAt(i)]? chars[str.charAt(i)]:left;//一定要理解这一步和第三步，目的是把left移动到上一次出现该字符的下一个位置。
            res = res < i-left+1? i-left+1:res;
            chars[str.charAt(i)] = i+1;
        }
        return str.substring(left, left + res);//注意这里返回的是最后的最长的，如果返回第一个最长的话就再加一个指针即可。不必多说
    }

    /**
     * 第二种方案：借助O(n)的set空间，时间复杂度是O(2n)的其实和O(n)一样了
     * 思想：如果zaiset没有窗口就增大，同时结果动态规划变大。如果set中不存在，这里是关键，会移除left指针的元素，然后再次进set判断，是不是删除了重复的元素，如果还存在
     * 说明刚才删的元素还没有到删除的元素那里，所以继续删除，直到删除了重复的元素，这样就可以保证窗口哪元素不重复了！
     * @param str
     * @return
     */
    public static String ls02(String str){
        Set<Character> set = new HashSet<Character>();
        int left = 0, res = 0;
        for (int i = 0; i<str.length(); i++){
            if (!set.contains(str.charAt(i))){
                set.add(str.charAt(i));
                res = Math.max(res, set.size());
            }else{
                set.remove(str.charAt(left++));
            }
        }
        return str.substring(left, res+left);
    }

    /**
     * 第三种方案同样是借助O(n)的空间复杂度，时间复杂度是O(n)
     * 思想：使用hashmap，key是字符，value是出现的索引，跟使用int[256]的思想基本一致
     * @param str
     * @return
     */
    public static String ls03(String str){
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0, res = 0;
        for (int i = 0; i < str.length(); i++){
            if (map.containsKey(str.charAt(i))){
                left = Math.max(left, map.get(str.charAt(i)));
            }
            res = Math.max(res, i-left+1);
            map.put(str.charAt(i), i+1);
        }
        return str.substring(left, left + res);
    }
}
