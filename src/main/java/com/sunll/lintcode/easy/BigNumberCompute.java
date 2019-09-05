package com.sunll.lintcode.easy;

import java.util.Map;

/**
 * <p>desc: 大数计算</p>
 * 这里的大数指的是用长整型已经容不下了，当然了类似Biginterger这种大数类就不考虑了
 * 大数加法（快手）
 * 大数减法
 * 大数乘法
 * 大数除法
 * @author sunliangliang 2019-08-19 23:01
 * @version 1.0
 */
public class BigNumberCompute {
    public static void main(String[] args) throws Exception {
        String a = "128";
        String b = "30";
//        System.out.println(bignumAdd(a,b));
        //System.out.println(bignumSubtract(a,b));
//        System.out.println(bignumMultiply(a,b));
        b = "3";
        System.out.println(bignumDivide(a,b));
    }

    /**
     * 大数加法
     * 思想：先把位数补齐，从个位开始计算，用一个临时变量记录是否需要进一位
     * @param a
     * @param b
     * @return
     */
    public static String bignumAdd(String a, String b){
        if (a.length() < b.length()){
            String tmp = a;
            a = b;
            b = tmp;
        }
        int cha = a.length() - b.length();
        for (int i = 1; i <= cha;i++){
            b = '0' + b;
        }
        StringBuilder res = new StringBuilder();
        int flag = 0;
        for (int i = a.length()-1; i>=0; i--){
            int sum = a.charAt(i) - 48 + b.charAt(i) - 48 + flag;
            flag = sum/10;
            res.append(sum % 10);
        }
        return res.reverse().toString();
    }

    /**
     * 大数减法
     * 思想：与大数加法类似，有略微的区别
     * @param a
     * @param b
     * @return
     */
    public static String bignumSubtract(String a, String b){
        StringBuilder res = new StringBuilder();
        String fuhao = "";
        if (a.length() < b.length() || (a.length() == b.length() && a.compareTo(b) < 0)){//如果b>a，那么就交换并且符号为负号
            fuhao = "-";
            String tmp = a;
            a = b;
            b = tmp;
        }
        int cha = a.length() - b.length();//补0
        for (int i = 1; i <= cha;i++){
            b = '0' + b;
        }
        //同样的套路，只不过现在不是进位，而是借位了
        int flag = 0;
        for (int i = a.length() - 1; i >= 0; i--){
            int subtract = a.charAt(i) - b.charAt(i) + flag;
            if (subtract < 0){
                flag = -1;
                subtract += 10;
            }else{
                flag = 0;
            }
            res.append(subtract);
        }
        //把后面的0去掉，说白了就是把结果的前面的0去掉
        int index = res.length()-1;
        while(res.charAt(index) == '0'){
            res.deleteCharAt(index--);
        }
        res.append(fuhao);
        return res.reverse().toString();
    }

    /**
     * 大数乘法
     * 跟加减法不一样的是不是对位运算进位或者借位了，而是将一个数拆开然后计算，比如123*45=123*45 + 123*5
     * @param a
     * @param b
     * @return
     */
    public static String bignumMultiply(String a, String b){
        String res = "";
        for (int i = 0; i < b.length(); i++){
            String tmp = compute(a,b.charAt(i));//每次用一位去乘
            tmp = add0(tmp,b.length() - i - 1);//比如123*40，用4乘完之后要补一个0
            res = bignumAdd(res, tmp);
        }
        return res;
    }
    private static String compute(String a, char b){
        int realNum = b - '0';
        int flag = 0;
        StringBuilder res = new StringBuilder();
        for (int i = a.length() - 1; i >= 0; i--){
            int ji = (a.charAt(i) - '0') * realNum + flag;
            flag = ji / 10;
            res.append(ji % 10);
        }
        return res.reverse().toString();
    }
    private static String add0(String a, int total){
        for (int i = 0; i < total; i++){
            a += "0";
        }
        return a;
    }

    /**
     * 大数除法
     * 思想：跟加减乘法完全不一样了！！
     * 比如128 / 3，可以看作128 / 30 = 4 余8，此轮结果是4*10=40
     * 下一轮 8 / 3，只能看作是8 / 3  = 2 余2，此轮结果是2*1
     * 综上结果=40 + 2
     * 关键点在于怎么计算128 / 30 或者8 / 3，思想就是用大数减法，比如123 减四次30 就余8了
     * @param a
     * @param b
     * @return
     */
    public static String bignumDivide(String a, String b) throws Exception {
        if (a.length() < b.length() || (a.length() == b.length() && a.compareTo(b) < 0)) return "0";
        String res = "";
        while(a.length() > b.length() || (a.length() == b.length() && a.compareTo(b) >0)){
            //接下来计算b每一轮需要补几个0
            int cha = a.length() - b.length();
            if (a.substring(0, b.length()).compareTo(b) < 0) cha--;
            StringBuilder lingNums = new StringBuilder();
            for (int i = 0; i < cha; i++){
                lingNums.append("0");
            }
            //开始循环相减
            int count = 0;
            String tmp = "";
            while(true){
                tmp = bignumSubtract(a, b + lingNums.toString());
                if (tmp.startsWith("-")) break;
                a = tmp;
                count++;
                //Thread.sleep(2000);
            }
            //每轮的结果加起来比如注释中的40 + 2
            res = bignumAdd(res, count + lingNums.toString());
        }
        return res;
    }
}
