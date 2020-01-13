package niuke;

public class Fibonacci {
    //打表法就是最骚的
    private int[] fib = new int[40];

    public Fibonacci() {
        fib[1] = 1;
        for (int i = 2; i < fib.length; i++)
            fib[i] = fib[i - 1] + fib[i - 2];
    }

    public int Fibonacci(int n) {
        return fib[n];
    }
    /*public int fibonacci(int n) {
        if (n <= 1) return n;
        int pre = 0, mid = 1;
        int fib = 0;
        for (int i =2; i <= n; i++) {
            fib = pre + mid;
            pre = mid;
            mid = fib;
        }
        return fib;
    }*/
    /* public int fibonacci(int n) {
        if (n <= 1) return n;
        int[] fib = new int[n+1];
        fib[1] = 1;
        for(int i = 2; i <= n; i++) {
            fib[i] = fib[i-1] + fib[i-2];
        }
        return fib[n];
    }*/
    /* public int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n-1) + fibonacci(n-2);
    }*/
}
