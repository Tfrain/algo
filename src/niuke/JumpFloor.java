package niuke;

public class JumpFloor {
    //不需要数组来存，只取最终结果。跳1 or 2层台阶和斐波那契差不多，n的结果可以由 n-2
    //来，也可以由 n-1来，而 n-2 和 n-1 可以继续往下递归，直至1和2层台阶的边界情况
    //将递归转换为迭代，好像这里也有点 DP 的思想
    public int jumpFloor(int target) {
        if(target <= 2) return target;
        int pre = 1, mid = 2, result = 0;
        for (int i = 2; i < target; i++) {
            result = pre + mid;
            pre = mid;
            mid = result;
        }
        return result;
    }
    /*public int JumpFloor(int target) {
        if(target <= 2) return target;
        return JumpFloor(target - 1) + JumpFloor(target -2);
    }*/
}
