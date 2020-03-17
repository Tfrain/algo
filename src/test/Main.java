package test;

import java.util.*;
public class Main{
    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        // int Decrement = 0;
        // String[] nums = sc.nextLine().split(" ");
        // int[] num = new int[nums.length];
        // for (int i = 0; i < num.length; i++) {
        //     num[i] = Integer.parseInt(nums[i]);
        // }
        // for (int i = 1; i < num.length - 1; i++) {
        //     if (num[i] < num[i - 1] || num[i] > num[i + 1]) {
        //         num[i] = num[i - 1];
        //         Decrement++;
        //     }
        //     if (Decrement > 1)
        //         break;
        // }
        // if (Decrement > 1)
        //     System.out.println(0);
        // else
        //     System.out.println(1);

       int c = 4;
        System.out.println(sqrt(300));
    }
    public static double sqrt(double c) {

    if (c < 0) {

return Double.NaN;
    }

    double e = 1e-15;
    double x = c;
    int k = 0;
    double y = (x + c / x) / 2;
    while (Math.abs(x - y) > e) {
        x = y;
        y = (x + c / x) / 2;
        k++;
    }
        System.out.println(k);
    return x;
}
}
