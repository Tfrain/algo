package forty_dp;

public class Knapsack2 {
    private static int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w + 1];
        states[0] = true;
        states[items[0]] = true;
        for (int i = 1; i < n; i++) {
             for (int j = w - items[i]; j >= 0; j--) {
                 if (states[j] == true) states[j + items[i]] = true;
             }
        }
        for (int i = w; i >= 0; i--) {
            if (states[i] == true) return i;
        }
        return 0;
    }
}
