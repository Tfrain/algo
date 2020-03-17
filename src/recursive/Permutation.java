package recursive;

import java.util.Collections;
import java.util.LinkedList;

public class Permutation {
    public static void allPermutation(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        LinkedList<String> listStr = new LinkedList<>();
        allPermutation(str.toCharArray(), listStr, 0);
        print(listStr);
    }
    private static void allPermutation(char[] c, LinkedList<String> listStr, int start) {
        if (start == c.length - 1) {
            listStr.add(String.valueOf(c));
        } else {
            for (int i = start; i < c.length; i++) {
                if (isSwap(c, start, i)) {
                    swap(c, i, start);
                    allPermutation(c, listStr, start + 1);
                    swap(c, i , start);
                }
            }
        }
    }
    private static void swap(char[] c, int i , int j) {
        char tmp = c[i];
        c[i] = c[j];
        c[j] = tmp;
    }
    private static void print(LinkedList<String> listStr)
    {
        Collections.sort(listStr);//使字符串按照'字典顺序'输出
        for (String str : listStr) {
            System.out.println(str);
        }
        System.out.println("size:" + listStr.size());
    }
    //去重的全排列就是从第一个数字起每个数分别与它后面非重复出现的数字交换。
    //用编程的话描述就是第i个数与第j个数交换时，要求[i,j)中没有与第j个数相等的数。
    private static boolean isSwap(char[] c, int start, int end) {
        for (int i = start; i < end; i++) {
            if (c[i] == c[end])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        allPermutation("abcdd");
    }
}
