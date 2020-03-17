package leetcode;

public class NumArray {
    // int dp[];
    // public NumArray(int[] nums) {
    //     if (nums == null || nums.length == 0) return;
    //     dp = new int[nums.length];
    //     dp[0] = nums[0];
    //     for (int i = 1; i < nums.length; i++) {
    //         dp[i] = dp[i - 1] + nums[i];
    //     }
    // }
    // public int sumRange(int i, int j) {
    //     if (i == 0) return dp[j];
    //     else return dp[j] - dp[i - 1];
    // }
    private int[] sums;

    public NumArray(int[] nums) {
        // 好在空数组时，不会出现数组越界错误，不用多一条判断
        sums = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        return sums[j + 1] - sums[i];
    }
}
