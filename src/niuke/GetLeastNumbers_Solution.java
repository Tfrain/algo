package niuke;

import java.util.ArrayList;

public class GetLeastNumbers_Solution {
    public ArrayList<Integer> getLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (k > input.length || k <= 0) return ret;
        findKthSmallest(input, k - 1);
        for (int i = 0; i < k; i++) {
            ret.add(input[i]);
        }
        return ret;
    }
    private void findKthSmallest(int[] nums, int k) {
        int l = 0, h = nums.length - 1;
        while(l < h) {
            int j = partition(nums, l, h);
            if (j == k) break;
            else if (j < k) l = j + 1;
            else h = j - 1;
        }
    }

    private int partition(int[] nums, int l, int h) {
        int i = l, j = h + 1;
        int p = nums[l];
        while (true) {
            while (i != h && nums[++i] < p);
            while (j != l && nums[--j] > p);
            if (i >= j) break;
            swap(nums, i, j);
        }
        swap(nums, l, j);
        return j;
    }
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


}
