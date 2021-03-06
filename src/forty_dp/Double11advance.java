package forty_dp;

public class Double11advance {
    public static void double11advance(int[]items, int n, int w) {
        boolean[][] states = new boolean[n][3*w + 1];
        states[0][0] = true;
        states[0][items[0]] = true;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= 3*w; j++) {
                if (states[i - 1][j] == true) states[i][j] = states[i - 1][j];
            }
            for (int j = 0; j <= 3*w - items[i]; j++) {
                if (states[i - 1][j] == true) states[i][j + items[i]] = true;
            }
        }

        int j;
        for (j = w; j < 3*w + 1; j++) {
            if (states[n - 1][j] == true) break;
        }
        if (j == -1) return;
        for (int i = n - 1; i >= 1; i--) {
            if (j - items[i] >= 0 && states[i - 1][j - items[i]] == true) {
                System.out.println(items[i] + " ");
                j = j - items[i];
            }
        }
        if (j != 0) System.out.println(items[0]);
    }
}
