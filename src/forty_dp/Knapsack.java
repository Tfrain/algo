package forty_dp;

public class Knapsack {
    // weight 物品重量 n 物品数量，w 背包可承载的重量
    public int knapsack (int[] weight, int n, int w) {
        boolean [][] states = new boolean[n][w + 1];
        states[0][0] = true;
        states[0][weight[0]] = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= w; j++) {
                if (states[i - 1][j] == true) states[i][j] = states[i - 1][j];
            }
            for (int j = 0; j < w - weight[i]; j++) {
                if (states[i - 1][j] == true) states[i][j + weight[i]] = true;
            }
        }
        for (int i = w; i >= 0; i--) {
            if (states[n - 1][i] == true) return i;
        }
        return 0;
    }
}
