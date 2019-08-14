package com.sunll.lintcode.normal;

/**
 * <p>desc: 字符串循环左移</p>
 * 字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”
 * @author sunliangliang 2019-08-14 21:24
 * @version 1.0
 */
public class StringMove {
    public static void main(String[] args) {
        String a = "ABCabdDDd";
        System.out.println(strMove(a,3));
        System.out.println(strMove2(a, 3));

    }

    /**
     * 字符串循环左移
     * 方案一：调用字符串的截取操作
     * 这种方案是完全可以的，但是有一点不太好的地方就是空间复杂度
     * @param str
     * @return
     */
    public static String strMove(String str, int num){
        String a = str.substring(0, num);
        String b = str.substring(num,str.length());
        return b+a;
    }

    /**
     * 方案二：三段反转操作，我们直到反转操作很容易，空间复杂度是O(1)
     * @param str
     * @param num
     * @return
     */
    public static String strMove2(String str, int num){
        char[] chars = str.toCharArray();
        reverse(chars, 0, num-1);
        reverse(chars, num, chars.length-1);
        reverse(chars, 0, chars.length-1);
        return String.valueOf(chars);
    }

    /**
     * 反转操作
     * @param chars
     * @param start
     * @param end
     * @return
     */
    public static char[] reverse(char[] chars, int start, int end){
        for(int i=start; i<=(start+end)/2; i++){
            char tmp = chars[i];
            chars[i] = chars[end];
            chars[end] = tmp;
            end--;
        }
        return chars;
    }

}
