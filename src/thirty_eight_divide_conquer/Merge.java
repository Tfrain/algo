package thirty_eight_divide_conquer;

public class Merge {
    private static int num = 0;

   static int count(int[] a,int n) {
        num = 0;
        mergeSortCounting(a, 0, n - 1);
        return num;
    }
    private static void mergeSortCounting(int[] a, int p, int r) {
        if(p >= r) return;
        int q = (p + r) / 2;
        mergeSortCounting(a, p, q);
        mergeSortCounting(a, q + 1, r);
        // q为什么要传呢？
        merge(a, p, q, r);
    }

    private static void merge(int[] a, int p, int q, int r) {
        int i = p, j = q + 1, k = 0;
        int[] tmp = new int[r - p + 1];
        while(i <= q && j <= r) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++];
            } else {
                tmp[k++] = a[j++];
                //这里原本是2，4，3，2，5，6，会变成相对有序的情况
                //所以可以用下面这种便捷的逆序个数统计方法
                //最关键就是分割到一定程度小，从最小的部分计算排序,之后的两部分都是相对有序的。
                num += (q - i + 1);
            }
        }
        while (i <= q) {
            tmp[k++] = a[i++];
        }
        while (j <= r) {
            tmp[k++] = a[j++];
        }
        for(i = 0; i < r - p + 1; i++) {
            a[p + i] = tmp[i];
        }
    }

    public static void main(String[] args) {
        int[] a = {2,3,4,1,5,6};
        System.out.println(count(a,6));
    }
}
