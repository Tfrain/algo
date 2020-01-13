package niuke;

import java.util.Arrays;

public class JumpFloorII {
    //DP f(4) = f(4-1) + f(4-2) + f(4-3) + f(4-4)
    //因为青蛙可以跳上任意级的台阶，所以以青蛙跳上一个 4 级的台阶为例进行分析，
    // 它可以在开始直接跳 4 级到 4 级台阶，也可以从 1 级台阶上往上跳 3 个台阶到 4 级，
    // 也可以从 2 级台阶往上跳 2 个台阶到 4 级，还可以从 3 级台阶上跳 1 级到 4 级。
    // DP和递归都是一样的，考虑它子问题，更远的问题也是子问题，再后面则是边界问题
    public int jumpFloorII(int target) {
        return (int) Math.pow(2, target - 1);
    }
    /* public int jumpFloorII(int target) {
        int[] dp = new int[target];
        Arrays.fill(dp,1);
        //这里用到两层循环，因为需要用每一个dp的值
        //跳台阶只需要最后两个值，可以一层循环推出最后两个值
        for (int i = 1; i < target; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j];
            }
        }
        return dp[target - 1];
    }*/
}
