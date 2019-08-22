package com.sunll.lintcode.normal;

import java.util.Arrays;

/**
 * <p>desc: 硬币组合问题</p>
 * 比如现在有面值 1 5 10 20 50元的纸币，同时每类纸币还有数量限制，当然了没有限制更好
 * 1.如果需要给个人找零85元，请问使用最少的纸币个数的组合是几张？以及给出一种组合。***********这个问题很重要*********
 * 2.如果不限定纸币的张数，请问一共有多少种组合方式达到85元？不需要具体组合的结果。
 *
 * 我们要从这些题中学习贪心算法和动态规划。
 * @author sunliangliang 2019-08-21 23:23
 * @version 1.0
 */
public class MoneyCombine {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 5, 10, 20};
        int[] count = new int[]{50, 20, 10, 3,2};
        int total = 86;
        System.out.println(Arrays.toString(getCombine01(arr, count, total)));
        System.out.println(getCombine02(arr, 86));
        System.out.println(allCombines(arr, total));
        System.out.println(countWays(arr, arr.length, 86));
    }

    /**
     * 第一个问题
     * 第一种解决方案：贪心法
     * 思想：从最大额开始组合，以此使用第二大面额进行组合，直到结束
     * 弊端：局部最优解！！！！！！！而且还会出现一个很大的问题就是找不到解，比如1 5 7元组成10元的组合，加入用贪心法的话，一个7元+3个1元，加入1元没有3个怎么办？？所以贪心法是有弊端的
     * @param arr
     * @param total
     * @return
     */
    public static int[] getCombine01(int[] arr, int[] count, int total){
        int[] res = new int[arr.length];//用来记录每种纸币使用的数量
        int[] resError = new int[]{-1};
        for (int i = res.length - 1; i >= 0; i--){
            if (total == 0) return res;
            if (total < 0) return resError;//发生不能正常找零的情况
            int use = Math.min(count[i], total / arr[i]);
            total -= use * arr[i];
            res[i] = use;
        }
        if (total != 0){
            return resError;
        }
        return res;
    }

    /**
     * 第一个问题
     * 第二种解决方案：动态规划
     * 思想：
     * 凑0美分：因为0美分根本不需要硬币，因此结果是0：f(0) = 0；
     * 凑1美分：因为有1美分面值的硬币可以使用，所以可以先用一个1美分硬币，然后再凑够0美分即可，而f(0)的值是我们已经算出来了的，所以：f(1) = 1 + f(0) = 1 + 0 = 1，这里f(1) = 1 + f(0) 中的1表示用一枚1美分的硬币；
     * 凑2美分：此时四种面额的硬币中只有1美分比2美分小，所以只能先用一个1美分硬币，然后再凑够1美分即可，而f(1)的值我们也已经算出来了，所以：f(2) = 1 + f(1) = 1 + 1 = 2，这里f(2) = 1 + f(1) 中的1表示用一枚1美分的硬币；
     * 凑3美分：和上一步同样的道理，f(3) = 1 + f(2) = 1 + 2 = 3；
     * 凑4美分：和上一步同样的道理，f(4) = 1 + f(3) = 1 + 3 = 4；
     * 凑5美分：这时就出现了不止一种选择了，因为有5美分面值的硬币。方案一：使用一个5美分的硬币，再凑够0美分即可，这时：f(5) = 1 + f(0) = 1 + 0 = 1，这里f(5) = 1 + f(0) 中的1表示用一枚5美分的硬币；方案二：使用1个1美分的硬币，然后再凑够4美分，此时：f(5) = 1 + f(4) = 1 + 4 = 5。综合方案一和方案二，可得：f(5) = min{1 + f(0),1 + f(4)} = 1；
     * 凑6美分：此时也有两种方案可选，方案一：先用一个1美分，然后再凑够5美分即可，即：f(6) = 1 + f(5) = 1 + 1 = 2；方案二：先用一个5美分，然后再凑够1美分即可，即：f(6) = 1 + f(1) = 1 + 1 = 2。综合两种方案，有：f(6) = min{1 + f(5), 1 + f(1)} = 2；
     * 从上面的分析过程可以看出，要凑够i美分，就要考虑如下各种方案的最小值：
     * 1 + f(i - value[j])，其中value[j]表示第j种（j从0开始，0 <= j < 4）面值且value[j] <= i
     * 那么现在就可以写出状态转移方程了：
     * f(i) = 0, i = 0
     * f(i) = 1, i = 1
     * f(i) = min{1 + f(i - value[j])}, i > 1，value[j] <= i
     * 优势：全局最优解
     * @param arr
     * @param total
     * @return
     */
    public static int getCombine02(int[] arr, int total){
        int min = total;
        int[] cache = new int[total+1];//用于缓存之前计算过的，这样效率会呈指数下降
        cache[0] = 0;
        for(int i = 1; i < total + 1; i++){
            if (i >= arr[0]){
                cache[i] = cache[i - arr[0]] + 1;//初始化为最多硬币数
            }else{//如果剩余的零钱比最小面值还小，那么
                cache[i] = Integer.MAX_VALUE - total;
            }
            for (int j = 1;j < arr.length; j++){
                if (i > arr[j] && (cache[i] > cache[i - arr[j]] + 1)){
                    cache[i] = cache[i - arr[j]] + 1;
                }
            }
        }
        return cache[total];
    }

    /**
     * 第二个问题
     * 动态规划思想，找出所有的组合，说白了动态规划就是当前值与之前值有关，而跟哪些有关就跟动态规划难度有关了，比如fabonacci数列比较简单，但是这个问题
     * 就比较复杂了。
     * arr长度为N，生成行数为N，列数为aim+1的矩阵dp。dp[i][j]的含义是在使用arr[0]…arr[i]货币的情况下，组成钱数j的方法数。
     * 如果完全不用arr[i]货币，只使用arr[0]…arr[i-1]货币时，方法数为dp[i-1][j]。
     * 如果用1张arr[i]货币，剩下的钱使用arr[0.......i-1]货币组成，方法数为dp[i-1][j-1*arr[i]]。
     * 如果用2张arr[i]货币，剩下的钱使用arr[0......i-1]货币组成，方法数为dp[i-1][j-2*arr[i]]。
     * 如果用3张arr[i]货币，剩下的钱使用arr[0......i-1]货币组成，方法数为dp[i-1][j-3*arr[i]]。
     * dp[i][j]的值即为上述所有值得累加和。
     * 求每一个位置都需要枚举，时间复杂度为O(aim)。dp一共有N*aim个位置，所以总的时间复杂度为O(N*aim^2)
     * @param arr
     * @param sum
     * @return
     */
    public static int allCombines(int[] arr, int sum){
        int[][] dp = new int[arr.length][sum + 1];//为什么加一，是因为边界问题！
        for (int i = 0; i < sum + 1; i++){
            dp[0][i] = sum % arr[0] == 0? 1:0;//边界问题
        }
        for (int i = 1; i < arr.length; i++){
            for (int j = 0; j < sum + 1; j++){
                int tmp = 0;
                for (int k = 0; k*arr[i] <=j; k++){//这一步很关键，
                    tmp += dp[i-1][j - k*arr[i]];
                }
                dp[i][j] = tmp;
            }
        }
        return dp[arr.length-1][sum];
    }

    /**
     * 更高级的动态规划
     * @param penny
     * @param n
     * @param aim
     * @return
     */
    public static int countWays(int[] penny, int n, int aim) {
        // write code here
        int[] dp=new int[aim+1];//表示每种钱数j有多少种拼凑情况

        for(int j=0;j<=aim;j++){//初始
            if(j%penny[0]==0) dp[j]=1;
            else dp[j]=0;
        }

        for(int i=1;i<n;i++){
            for(int j=1;j<=aim;j++){
                if(j>=penny[i]) dp[j]=dp[j-penny[i]]+dp[j];
            }
        }
        return dp[aim];
    }

}
