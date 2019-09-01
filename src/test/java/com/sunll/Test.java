import java.util.*;

/**
 * desc
 *
 * @author sunliangliang 2019/5/29
 * @version 1.0
 */
public class Test {

    public static void main(String[] args) {
        int maxLevel = 4;
        int[] powers = new int[maxLevel];
        powers[maxLevel-1] = (2 << (maxLevel-1)) -1;//如果是4层，那么根据上面的分析应该有的临界值有 1 2 4 8 -》 0 1 3 7
        for (int i = maxLevel-2, j = 0; i>=0; i--, j++){
            powers[i] = powers[i+1] - (2 << i);
        }
        System.out.println(Arrays.toString(powers));
    }


}
