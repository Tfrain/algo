package forty_two_dp;

public class IwstBT {
    private char[] a = "mitcmu".toCharArray();
    private char[] b = "mtacnu".toCharArray();
    private int n = 6;
    private int m = 6;
    private int minDist = Integer.MAX_VALUE;

    public void lwstBT(int i, int j, int edist) {
        if (i == n || j == m) {
            if (i < n) edist += (n - i);
            if (j < m) edist += (m - j);
            if (edist < minDist) minDist = edist;
            return;
        }

        if (a[i] == b[j]) {
            lwstBT(i + 1, j + 1, edist);
        } else {
            // 删除a或者b前添加一个字符
            lwstBT(i + 1, j, edist + 1);
            // 删除b或者a前添加一个字符
            lwstBT(i, j + 1, edist + 1);
            // 将a和b替换为相同字符
            lwstBT(i + 1, j + 1, edist + 1);
        }
    }
    public int lwstDP(char[] a, int n, int m) {
        int[][] minDist = new int[n][m];
        for (int j = 0; j < m; j++) {
            if (a[0] == b[j]) minDist[0][j] = j;
            else if (j != 0) minDist[0][j] = minDist[0][j - 1] + 1;
            else minDist[0][j] = 1;
        }
        for (int i = 0; i < n; i++) {
            if (a[i] == b[0]) minDist[i][0] = i;
            else if (i != 0) minDist[i][0] = minDist[i - 1][0] + 1;
            else minDist[i][0] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (a[i] == b[j]) {
                    minDist[i][j] = min(minDist[i - 1][j] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j - 1]);
                } else {
                    minDist[i][j] = min(minDist[i - 1][j] + 1, minDist[i][j - 1] + 1, minDist[i - 1][j - 1] + 1);
                }
            }
        }
        return minDist[n - 1][m - 1];
    }
    private int min(int a, int b, int c) {
        int minV = Integer.MAX_VALUE;
        if (a < minV) minV = a;
        if (b < minV) minV = b;
        if (c < minV) minV = c;
        return minV;
    }

    public int lcs(char[] a, int n, char[] b, int m) {
        int[][] maxLcs = new int[n][m];
        for (int j = 0; j < m; j++) {
            if (a[0] == b[j]) maxLcs[0][j] = 1;
            else if (j != 0) maxLcs[0][j] = maxLcs[0][j - 1];
            else maxLcs[0][j] = 0;
        }
        for (int i = 0; i < m; i++) {
            if (a[i] == b[0]) maxLcs[i][0] = 1;
            else if (i != 0) maxLcs[i][0] = maxLcs[i - 1][0];
            else maxLcs[i][0] = 0;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (a[i] == b[j]) {
                    maxLcs[i][j] = max(maxLcs[i - 1][j], maxLcs[i][j - 1], maxLcs[i - 1][j - 1] + 1);
                }
                else {
                    maxLcs[i][j]= max(maxLcs[i - 1][j], maxLcs[i][j - 1], maxLcs[i - 1][j - 1] + 1);
                }
            }
        }
        return  maxLcs[n - 1][m - 1];
    }

    private  int max(int a, int b, int c) {
        int maxV = Integer.MAX_VALUE;
        if (a > maxV) maxV = a;
        if (b > maxV) maxV = b;
        if (c > maxV) maxV = c;
        return maxV;
    }
}
