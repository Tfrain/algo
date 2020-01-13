package niuke;

public class RectCover {
    // 利用dp的思想，要覆盖 2*n 的大矩形，可以先覆盖 2*1 的矩形，
    // 再覆盖 2*(n-1) 的矩形；或者先覆盖 2*2 的矩形，再覆盖 2*(n-2) 的矩形。
    // 这里有整体看重复的情形，但是题目没要求
    public int rectCover(int target) {
        if (target <= 2) return target;
        int pre = 1, mid = 2;
        int result = 0;
        for (int i = 3; i <= target; i++) {
            result = pre + mid;
            pre = mid;
            mid = result;
        }
        return result;
    }
}
