package com.sunll.lintcode.easy;

/**
 * <p>desc: 反转整数</p>
 * input 123 或者-123 输出是321 -321
 * 注意：这个题考的并不是数值的反转，而是考数据溢出的问题，比如1000000...9反转之后变成9000000...1可能超过了integer的大小或者long的范围
 * @author sunliangliang 2019-08-22 10:56
 * @version 1.0
 */
public class ReverseInteger {
    public static void main(String[] args) {
        int num = -345;
        System.out.println("INteger最大值"+Integer.MAX_VALUE);
        System.out.println("INteger最小值"+Integer.MIN_VALUE);
        System.out.println(reverseInteger(num));
    }

    /**
     *
     * 反转整数
     * @param num
     * @return
     */
    public static int reverseInteger(int num){
        int res = 0;
        while (num != 0){
            int pop = num %10;
            if ((res > Integer.MAX_VALUE/10 || (res == Integer.MAX_VALUE/10 && pop > 7)) || res < Integer.MIN_VALUE/10 || (res == Integer.MIN_VALUE/10 && pop < -8)){
                //这里为什么这么判断是有道理的，因为INteger大小范围是正负21亿多，负的个位是-8，正的个位是+7
                return 0;
            }
            res = res * 10 + pop;
            num = num / 10;
        }
        return res;
    }
}
