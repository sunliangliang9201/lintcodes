package com.sunll.lintcode.easy;

/**
 * <p>desc: 字符串大小写转换</p>
 *
 * @author sunliangliang 2019-08-29 23:09
 * @version 1.0
 */
public class CaseReverse {
    public static void main(String[] args) {
        String str = "AaBBccDdD";
        System.out.println(caseReverse01(str));
    }

    public static String caseReverse01(String str){
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++){
            chars[i] = (char) (chars[i] > 96? chars[i]-32: chars[i]+32);
        }
        return String.valueOf(chars);
    }
}
