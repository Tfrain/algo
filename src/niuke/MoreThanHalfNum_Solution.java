package niuke;

public class MoreThanHalfNum_Solution {
    public int moreThanHalfNum_Solution(int[] array) {
        // 要从小到大考虑，不要老是从宏观上走死胡同
        int majority = array[0];
        for (int i = 1, cnt = 1; i < array.length; i++) {
            cnt = array[i] == majority ? cnt + 1 : cnt - 1;
            if (cnt == 0) {
                majority = array[i];
                cnt = 1;
            }
        }
        //为了确保不是阶段性的 majority，要最后确认一次
        int count = 0;
        for (int val : array) {
            if (val == majority) count++;
        }
        return count > array.length / 2 ? majority : 0;
    }
}
