package com.sunll.lintcode.easy;

import java.util.HashMap;

/**
 * <p>desc: 跳台阶与变态跳台阶</p>
 * 这个题考察的是动态规划，通过动态规划的方式降低时间复杂度
 *
 * ！！！！！！！！！！！！！！！！！！！下面来推倒一下递推公式！！！！！！！！！！！！
 * 跳台阶
 * 当n = 1 时， 只有一种跳法，即1阶跳：Fib(1) = 1;
 * 当n = 2 时， 有两种跳的方式，一阶跳和二阶跳：Fib(2)  = 2;
 * 当n = 3 时，有两种跳的方式，第一次跳出一阶后，对应Fib(3-1)种跳法； 第一次跳出二阶后，对应Fib(3-2)种跳法。Fib(3) = Fib(2) + Fib(1)= Fib(2) + Fib(1)= 3;
 * 当n = 4时，有两种方式：第一次跳出一阶，对应Fib(4-1)种跳法；第一次跳出二阶，对应Fib(4-2)种跳法。所以，Fib(4) = Fib(4-1) + Fib(4-2)= Fib(3) + Fib(2)种跳法。
 * 当n = n 时，共有n种跳的方式，第一次跳出一阶后，后面还有Fib(n-1)中跳法； 第一次跳出二阶后，后面还有Fib(n-2)中。Fib(n) = Fib(n-1)+Fib(n-2)。
 *
 * 变态跳台阶
 * 当n = 1 时， 只有一种跳法，即1阶跳：Fib(1) = 1
 * 当n = 2 时， 有两种跳的方式，一阶跳和二阶跳：Fib(2)  = 2;
 * 到这里为止，和普通跳台阶是一样的。
 * 当n = 3 时，有三种跳的方式，第一次跳出一阶后，对应Fib(3-1)种跳法； 第一次跳出二阶后，对应Fib(3-2)种跳法；第一次跳出三阶后，只有这一种跳法。Fib(3) = Fib(2) + Fib(1)+ 1 = Fib(2) + Fib(1) + Fib(0) = 4;
 * 当n = 4时，有四种方式：第一次跳出一阶，对应Fib(4-1)种跳法；第一次跳出二阶，对应Fib(4-2)种跳法；第一次跳出三阶，对应Fib(4-3)种跳法；第一次跳出四阶，只有这一种跳法。所以，Fib(4) = Fib(4-1) + Fib(4-2) + Fib(4-3) + 1 = Fib(4-1) + Fib(4-2) + Fib(4-3) + Fib(4-4) 种跳法。
 * 当n = n 时，共有n种跳的方式，第一次跳出一阶后，后面还有Fib(n-1)中跳法； 第一次跳出二阶后，后面还有Fib(n-2)中跳法..........................第一次跳出n阶后，后面还有 Fib(n-n)中跳法。Fib(n) = Fib(n-1)+Fib(n-2)+Fib(n-3)+..........+Fib(n-n) = Fib(0)+Fib(1)+Fib(2)+.......+Fib(n-1)。
 * 通过上述分析，我们就得到了通项公式：
 * Fib(n) =  Fib(0)+Fib(1)+Fib(2)+.......+ Fib(n-2) + Fib(n-1)
 * 因此，有 Fib(n-1)=Fib(0)+Fib(1)+Fib(2)+.......+Fib(n-2)
 * 两式相减得：Fib(n)-Fib(n-1) = Fib(n-1)         =====》  Fib(n) = 2*Fib(n-1)     n >= 3
 * 这就是我们需要的递推公式：Fib(n) = 2*Fib(n-1)     n >= 3
 * @author sunliangliang 2019-08-17 23:31
 * @version 1.0
 */
public class JumpFloor {
    public static void main(String[] args) {
        System.out.println(jumpFloor01(3));
        System.out.println(jumpFloor02(3));
        System.out.println(jumpFloor03(3));
        System.out.println(jumpFloor04(3));
    }

    /**
     * 普通跳台阶，一只青蛙一次只能跳一个或者两个台阶，请问跳N个台阶有多少种跳法？
     * 第一种方式：递归（时间复杂度为N(2^n)）
     */
    public static int jumpFloor01(int sum){
        if(sum < 3) return sum;
        return jumpFloor01(sum-1)+jumpFloor01(sum-2);
    }

    /**
     * 普通跳台阶，一只青蛙一次只能跳一个或者两个台阶，请问跳N个台阶有多少种跳法？
     * 第二种方式：动态规划，时间复杂度是O(n)
     * 思想：其实跟递归的思想一样！只不过不是递归而是利用了历史计算结果
     * @param sum
     * @return
     */
    public static int jumpFloor02(int sum){
        if (sum < 3) return sum;
        int a=1, b=2, res=0;
        while(sum >= 3){
            res = a+b;
            a = b;
            b = res;
            sum--;
        }
        return res;
    }

    /**
     * 变态跳台阶，一只青蛙一次可以跳一个或者两个或者三个或者....N个台阶，请问跳N个台阶有多少种跳法？
     * 递归方式，时间复杂度是指数的
     * 思想：通过上述的递推公式推倒，我们就可以轻而易举使用递归和动态规划了！
     * @param sum
     * @return
     */
    public static int jumpFloor03(int sum){
        if(sum < 3) return sum;
        return 2*jumpFloor01(sum-1);
    }

    /**
     * 变态跳台阶的动态规划，非递归方式，时间复杂度是线性的
     */
    public static int jumpFloor04(int sum){
        if (sum < 3) return sum;
        int a = 2, res=0;
        while(sum >= 3){
            res = 2*a;
            a = res;
            sum--;
        }
        return res;
    }
}
