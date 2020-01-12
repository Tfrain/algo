package thirty_two_BFRK;

public class BfRk {
    public static int bF(String a, String b) {
        int m = a.length(),n = b.length(),k;
        char[] mainString = a.toCharArray();
        char[] patternString = b.toCharArray();
        for (int i = 0; i < m-n+1; i++) {
            k = 0;
            for (int j = 0; j < n;j++) {
                if(mainString[i+j] == patternString[j]) {
                    k++;
                } else break;
            }
            if(k == n) return i;
        }
        return -1;
    }

    public static  int rK(String a, String b) {
        int m = a.length(),n = b.length(),s,j;
        int[] hash = new int[m-n+1];
        int[] table = new int[26];
        char[] mainString = a.toCharArray();
        char[] patternString = b.toCharArray();
        s = 1;
        //会溢出，但是没啥问题
        for(int i = 0; i < 26; i++) {
            table[i] = s;
            s *= 26;
        }
        for(int i=0; i < m-n+1;i++) {
            s = 0;
            for(j=0; j<n; j++) {
                //可以保证每个哈希值不会冲突
                s += (mainString[i+j] - 'a') * table[n-1-j];
            }
            hash[i] = s;
        }
        s = 0;
        for(j=0; j<n; j++) {
            s += (patternString[j] - 'a') * table[n-1-j];
        }
        for(j=0; j<m-n+1; j++) {
            if(hash[j] == s) {
                return j;
            }
        }
        return -1;
    }

}
