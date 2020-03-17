package leetcode;

public class Rob {
    public int rob(int[] nums) {
        int pre2 = 0, pre1 = 0;
        for (int i = 0; i < nums.length; i++) {
            int cur = Math.max(pre2 + nums[i], pre1);
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }
    public int robII(int [] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        if (n == 1) return nums[0];
        return Math.max(start(nums, 0, n - 2), start(nums, 1, n - 1));
    }

    private int start(int[] nums, int first, int end) {
        int pre2 = 0, pre1 = 0;
        int cur = 0;
        for (int i = first; i <= end; i++) {
            cur = Math.max(pre1, pre2 + nums[i]);
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }
}
