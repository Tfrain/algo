package thirty_three_bm;

public class Bm {
    private static final int SIZE = 256;
    //b是模式串，m是模式串的长度，bc表示存储ascii码的散列表
    private void generateBC(char[] b, int m, int[] bc) {
        for(int i = 0; i < SIZE; i++) {
            bc[i] = -1;
        }
        for(int i=0; i<m; i++) {
            int ascii = (int)b[i];
            bc[ascii] = i;// 数组中存储的是字符串相应的下标,重复时记录的是靠后的下标，防止出现遗漏
        }
    }
    //a是主串、b是模式串、m模式串长度、n主串长度
    //这是只含有坏字符的bm算法
    // private int bm(char[] a, int n, char[]b, int m) {
    //     int[] bc = new int[SIZE];
    //     generateBC(b, m, bc);
    //     int i = 0;// i表示主串与模式串对齐的第一个字符
    //     while(i <= n-m) {
    //         int j;
    //         for(j = m-1; j>=0; j--){
    //             if(a[i+j] == b[j]) break; // 坏字符对应模式串中的下标是j
    //         }
    //         if(j < 0) return i;// 匹配成功，返回主串与模式串第一个匹配的字符的位置
    //         i = i+(j-bc[(int)a[i+j]]);
    //     }
    //     return -1;
    // }

    private void generateGS(char[] b, int m, int [] suffix, boolean[] prefix) {
        // b表示模式串，m表示长度，suffix，prefix数组事先申请好了
        for(int i = 0; i < m; i++) {
            suffix[i] = -1;
            prefix[i] = false;
        }
        //这个算法比较重要，模式串从前和从后比较，true赋给最长公共后缀前缀子串
        //一次性求完，在接下来的遍历过程中比较方便，但也意味着占用内存吧
        for(int i=0; i<m-1; i++) {
            int j = i;
            int k = 0; // 公共后缀子串长度
            while(j >= 0&&b[j] == b[m-1-k]) {// 与b[0, m-1]求公共后缀子串
                --j;
                ++k;
                suffix[k] = j+1;//j+1表示公共后缀子串在b[0, i]中的起始下标
            }
            if(j == -1) prefix[k] = true;//如果公共后缀子串也是模式串的前缀子串
        }
    }
    public int bm(char[] a, int n, char[] b, int m) {
        int[] bc = new int[SIZE];
        generateBC(b, m, bc);
        int[] suffix = new int[m];
        boolean[] prefix = new boolean[m];
        generateGS(b, m, suffix, prefix);
        int i = 0;
        while(i <= n-m) {
            int j;
            for(j = m-1; j >= 0; --j) {
                if(a[i+j] != b[j]) break; // 坏字符对应模式串中的下标是j
            }
            if(j < 0) return i;
            int x = j - bc[(int)a[i+j]];
            int y = 0;
            if (j < m-1) {  // 如果有好后缀的话
                y = moveByGS(j, m, suffix, prefix);
            }
            i = i + Math.max(x, y);
        }
        return -1;
    }
    // j表示坏字符对应的模式串中的字符下标; m表示模式串长度，返回移动位数
    private int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
        int k = m-1-j;
        if(suffix[k] != -1) return j-suffix[k]+1;
        for(int r = j+2; r <= m-1; r++) {
            if(prefix[m-r] == true) {
                return r;
            }
        }
        return m;// 没有则移动m位
    }

}
