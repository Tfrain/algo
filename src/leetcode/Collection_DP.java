package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collection_DP {
    public int numberOfArithmeticSlices(int[] A) {
       if (A == null || A.length < 3) return 0;
       int[] dp = new int[A.length];
       int cnt = 0;
       dp[0] = 0;
       for (int i = 2; i < A.length; i++) {
           if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
               dp[i] = dp[i - 1] + 1;
           }
       }
       for (int tmp : dp) {
           cnt += tmp;
       }
       return cnt;
    }

    public int integerBreak(int n) {
      // int first = n / 2;
      // int end = n - first;
      // int len = Math.max(first, end);
      // int[] dp = new int[len + 1];
      // dp[1] = 0;
      // for (int i = 2; i <= len; i++) {
      //     int left = i / 2, right = i - left;
      //     dp[i] = Math.max(left, dp[left]) * Math.max(right, dp[right]);
      // }
      // return Math.max(first, dp[first]) * Math.max(end, dp[end]);

           int[] dp = new int[n + 1];
            dp[1] = 1;
            for (int i = 2; i <= n; i++) {
                for (int j = 1; j <= i - 1; j++) {
                    dp[i] = Math.max(dp[i], Math.max(j * dp[i - j], j * (i - j)));
                }
            }
            return dp[n];
    }
    public int numSquares(int n) {
        ArrayList<Integer> list = generateSquareList(n);
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[0] = 0;
        for (int i = 2; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int square : list) {
                if (square > i)
                    break;
                min = Math.min(min, dp[i - square] + 1);
            }
            dp[i] = min;
        }
        return dp[n];
    }
    private ArrayList<Integer> generateSquareList(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        int square = 1, diff = 3;
        while (square <= n) {
            list.add(square);
            square += diff;
            diff += 2;
        }
        return list;
    }

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1 && s.equals("0")) return 0;
        int[] dp = new int[s.length() + 1];
        int n = s.length();
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (!s.substring(i - 1, i).equals("0")) {
                dp[i] += dp[i - 1];
            }
            if (s.substring(i - 2, i - 1).equals("0")) {
                continue;
            }
            if (Integer.valueOf(s.substring(i - 2, i)) < 27) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    // public int lengthOfLIS(int[] nums) {
    //     int n = nums.length;
    //     int[] dp = new int[n];
    //     for (int i = 0; i < n; i++) {
    //         int max = 1;
    //         for (int j = 0; j < i; j++) {
    //             if (nums[i] > nums[j]) {
    //                 max = Math.max(max, dp[j] + 1);
    //             }
    //         }
    //         dp[i] = max;
    //     }
    //     int ret = 0;
    //     for (int i = 0; i < n; i++) {
    //         ret = Math.max(ret, dp[i]);
    //     }
    //     return ret;
    // }

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] tails = new int[n];
        int len = 0;
        for (int num : nums) {
            int index = binarySearch(tails, len, num);
            tails[index] = num;
            if (index == len) {
                len++;
            }
        }
        return len;
    }
    private int binarySearch(int[] tails, int len, int key) {
        int l = 0, h = len;
        while(l < h) {
            int mid = l + (h - l) / 2;
            if (tails[mid] == key) {
                return mid;
            } else if (tails[mid] > key) {
                h = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
    public int findLongestChain(int[][] pairs) {
        if (pairs == null || pairs.length == 0 || pairs[0].length == 0)
            return 0;
        int n = pairs.length;
        Arrays.sort(pairs, (a, b) -> (a[0] - b[0]));
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int max = 1;
            for(int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
        }
        int ret = 0;
        for (int dps : dp) {
            ret = Math.max(ret, dps);
        }
        return ret;
    }
    // public int wiggleMaxLength(int[] nums) {
    //     if (nums == null || nums.length == 0) {
    //         return 0;
    //     }
    //     int down = 1, up = 1;
    //     for (int i = 1; i < nums.length; i++) {
    //         if (nums[i] > nums[i - 1]) {
    //             up = down + 1;
    //         } else if (nums[i] < nums[i - 1]) {
    //             down = up + 1;
    //         }
    //     }
    //     return Math.max(up, down);
    // }
    public int wiggleMaxLength(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length < 2) {
            return nums.length;
        }

        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    up[i] = Math.max(up[i], down[j] + 1);
                } else if (nums[i] < nums[j]) {
                    down[i] = Math.max(down[i], up[j] + 1);
                }
            }
        }
        return Math.max(down[nums.length - 1], up[nums.length - 1]) + 1;
    }
    public int longestCommonSubsequence(String text1, String text2) {
        int n1 = text1.length(), n2 = text2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n1][n2];
    }
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0)
            return false;
        int n = sum / 2;

        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int i = n; i > num; i--) {
                dp[i] = dp[i] || dp[i - num];
            }
        }
        return dp[n];
    }


    private int computeArraySum(int[] nums) {
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        return sum;
    }
    public int findTargetSumWays(int[] nums, int S) {
        int sum = computeArraySum(nums);
        if (sum < S || (sum + S) % 2 == 1) {
            return 0;
        }
        int w = (sum + S) / 2;
        int[] dp = new int[w + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int i = w; i >= num; i--) {
                dp[i] = dp[i] + dp[i - num];
            }
        }
        return dp[w];
    }
    public int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0) {
            return 0;
        }
        int[][] dp = new int[m + 1][n + 1];
        for (String s: strs){
            int ones = 0, zeros = 0;
            for (char c : s.toCharArray()) {
                if (c == '1') {
                    ones++;
                } else {
                    zeros++;
                }
            }
            for (int i = m; i >= zeros; i--) {
               for (int j = n; j >= ones; j--)  {
                   dp[i][j] = Math.max(dp[i][j], dp[i - zeros][j - ones] + 1);
               }
            }
        }
        return dp[m][n];
    }
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                if (i == coin) {
                    dp[i] = 1;
                } else if (dp[i] == 0 && dp[i - coin] != 0) {
                    dp[i] = dp[i - coin] + 1;
                } else if (dp[i - coin] != 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }

        }
        return dp[amount] == 0 ? -1 : dp[amount];
    }
    public int change(int amount, int[] coins) {
        if (coins == null)
            return 0;
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (String word : wordDict) {
                int len = word.length();
                if (len <= i && word.equals(s.substring(i - len, i))) {
                    dp[i] = dp[i - len];
                }
            }
        }
        return dp[n];
    }
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return 0;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        Arrays.sort(nums);
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length && j <= nums[i]; j++) {
                dp[i] += dp[i - nums[j]];
            }
        }
        return dp[target];
    }



}