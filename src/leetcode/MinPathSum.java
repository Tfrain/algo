package leetcode;

public class MinPathSum {
    public int minPathSum(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;

        int row = grid.length;
        int column = grid[0].length;
        int[] dp = new int[column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (j == 0) {
                    dp[j] = dp[j];// 只能从上面来
                } else if (i == 0) {// 不能放在前面，且需要用选择语句，否则可能出现越界
                    dp[j] = dp[j - 1];// 只能从左侧来
                } else {
                    dp[j] = Math.min(dp[j], dp[j - 1]);
                }
                dp[j] += grid[i][j];
            }
        }
        return dp[column - 1];
    }
}
