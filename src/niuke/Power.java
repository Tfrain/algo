package niuke;

public class Power {
    //递归利用base*base，复杂度logn
    public double power(double base, int exponent) {
        if(exponent == 0) return 1;
        if(exponent == 1) return base;
        boolean isNegative = false;
        if(exponent < 0) {
            exponent = -exponent;
            isNegative = true;
        }
        double res = power(base * base, exponent / 2);
        if(exponent % 2 != 0) res = res * base;
        return isNegative ? 1 / res : res;
    }/*public double power(double base, int exponent) {
        double res = 1;
        if (exponent >= 0) {
            for (int i = 0; i < exponent; i++) {
                res *= base;
            }
        } else {
            for (int i = 0; i < -exponent; i++) {
                res /= base;
            }
        }
        return res;
    }*/

}
