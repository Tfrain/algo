package thirty_four_KMP;

public class KMP {
    public static int kmp(char[] a, int n, char[] b, int m) {
        int[] next = getNexts(b, m);
        int j = 0;
        for (int i = 0; i < n; i++) {
            while(j>0 && a[i] != b[j]) {
                j = next[j-1] + 1;
            }
            if(a[i] == b[j]) ++j;
            if(j == m) return i-m+1;
        }
        return -1;
    }
    //逆推时，通过前缀不断缩短，找到和b[i]相同的值，因为之前的next值到了b[i]，
    //所以次长可匹配前缀子串会容易找到下一个和b[i] 相等的值，对应的就是下面的while语句
    //动态规划，自后向前推算，自前向后编写的思想一览无余
    private static int[] getNexts(char[] b, int m) {
        int[] next = new int[m];
        next[0] = -1;
        int k = -1;
        for(int i = 1; i < m; i++) {
// 可能一直退到最后
            while(k != -1 && b[k+1] != b[i]) {
                k = next[k];//这里就是动态规划的关键了
            }
            //简单情况下，就不需要利用next数组
            if(b[k+1] == b[i]) k++;
            next[i] = k;
        }
        return next;
    }
}
