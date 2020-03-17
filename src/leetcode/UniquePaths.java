package leetcode;

import java.util.Arrays;

public class UniquePaths {
    // public int uniquePaths(int m, int n) {
    //
    //     int[][] grid = new int[n][m];
    //     for (int i = 0; i < m; i++) {
    //         grid[0][i] = 1;
    //     }
    //     for (int j = 0; j < n; j++) {
    //         grid[j][0] = 1;
    //     }
    //
    //     for (int i = 1; i < n; i++) {
    //         for (int j = 1; j < m; j++) {
    //             grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
    //         }
    //     }
    //     return grid[n - 1][m - 1];
    // }
    public int uniquePaths(int m, int n) {

        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }
}
