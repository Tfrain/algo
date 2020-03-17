package forty_dp;

public class Bag_0_1 {
    public int maxW = Integer.MIN_VALUE;//总重量的最大值
    // cw表示当前已经装进去的物品的重量和；i表示考察到哪个物品了；
    // w背包重量；items表示每个物品的重量；n表示物品个数
    // 假设背包可承受重量100，物品个数10，物品重量存储在数组a中，那可以这样调用函数：
    // f(0, 0, a, 10, 100)
    private int[] weight = {2, 2, 4, 6, 3};
    private int n = 5;
    private int w = 9;
    // public void f(int i, int cw) { //调用f(0,0)
    //     // 总体来看，利用递归，要将返回条件放置在最前面，回溯和递归确实很巧妙
    //     if (cw == w || i == n) { // 前者表示装满了，后者表示考察完所有物品
    //         if (cw > maxW) maxW = cw;
    //         return;
    //     }
    //     // 不装
    //     f(i + 1, cw);
    //     // 装
    //     if (cw +weight[i] <= w) {
    //         f(i + 1, cw +weight[i]);
    //     }
    // }
    private boolean[][] mem = new boolean[5][10];
    public void f(int i, int cw) { //调用f(0,0)
        // 总体来看，利用递归，要将返回条件放置在最前面，回溯和递归确实很巧妙
        if (cw == w || i == n) { // 前者表示装满了，后者表示考察完所有物品
            if (cw > maxW) maxW = cw;
            return;
        }
        if (mem[i][cw]) return;
        mem[i][cw] = true;
        // 不装
        f(i + 1, cw);
        // 装
        if (cw +weight[i] <= w) {
            f(i + 1, cw +weight[i]);
        }
    }
}
