package niuke;

public class NumberOf1 {
    public int numberOf1(int n) {
        return Integer.bitCount(n);
    }
    //将含有1的位数不断缩减
    /*public int numberOf1(int n) {
        int sum = 0;
        while(n != 0) {
            sum++;
            n &= n-1;
        }
        return sum;
    }*/
}
