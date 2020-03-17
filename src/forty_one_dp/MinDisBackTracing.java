package forty_one_dp;

public class MinDisBackTracing {
    private int minDist = Integer.MAX_VALUE;

    public void minDistBT(int i, int j, int dist, int[][] w, int n) {
        if (i == n && j == n) {
            if (dist < minDist) minDist = dist;
            return;
        }
        if (i < n) {
            minDistBT(i + 1, j, dist + w[i][j], w, n);
        }
        if (j < n) {
            minDistBT(i, j + 1, dist + w[i][j], w, n);
        }
    }

    public int minDistDP(int[][] matrix, int n) {
        int[][] states = new int[n][n];
        int sum = 0;
        for (int j = 0; j < n; j++) {
            sum += matrix[0][j];
            states[0][j] = sum;
        }
        for (int i = 0; i < n; i++) {
            sum += matrix[i][0];
            states[i][0] = sum;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                states[i][j] = matrix[i][j] + Math.min(states[i - 1][j], states[i][j - 1]);
            }
        }
        return states[n - 1][n - 1];
    }

    private int[][] matrix = {{1, 3, 5, 9}, {2, 1, 3, 4}, {5, 2, 6, 7}, {6, 8, 4, 3}};
    private int n = 4;
    private int[][] mem = new int[4][4];
    public int minDist(int i, int j) {
        if (i == 0 && j == 0) return matrix[0][0];
        if (mem[i][j] > 0) return mem[i][j];
        int minLeft = Integer.MAX_VALUE;
        if (j - 1 > 0) {
            minLeft = minDist(i, j - 1);
        }
        int minUp = Integer.MAX_VALUE;
        if (i - 1 > 0) {
            minUp = minDist(i - 1, j);
        }

        int currMinDist = matrix[i][j] + Math.min(minLeft, minUp);
        mem[i][j] = currMinDist;
        return currMinDist;
    }

    public int minCoins(int money) {
        if (money == 1 || money == 3 || money == 5) return 1;
        boolean[][] states = new boolean[money][money + 1];
        if (money >= 1) states[0][1] = true;
        if (money >= 3) states[0][3] = true;
        if (money >= 5) states[0][5] = true;
        for (int i = 1; i < money; i++) {
            for (int j = 1; j <= money; j++) {
                if (states[i - 1][j]) {
                    if (j + 1 <= money) states[i][j + 1] = true;
                    if (j + 3 <= money) states[i][j + 3] = true;
                    if (j + 5 <= money) states[i][j + 5] = true;
                    // 每一行最右端的数即是最少的零钱数量，直到找到满足 money 数值的零钱数再返回
                    if (states[i][money]) return i + 1;
                }
            }
        }
        // 无法找零的数字
        return money;
    }
}
