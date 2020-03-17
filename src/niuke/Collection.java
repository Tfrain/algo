package niuke;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Collection {
    public int FindGreatestSumOfSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int greatestSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int val : nums) {
            sum = sum <= 0 ? val : sum + val;
            greatestSum = Math.max(greatestSum, sum);
        }
        return greatestSum;
    }

    public String PrintMinNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0) return "";
        int n = numbers.length;
        String[] nums = new String[n];
        for (int i = 0; i < n; i++) {
            nums[i] = numbers[i] + "";
        }
        Arrays.sort(nums,(s1, s2) -> (s1 + s2).compareTo(s2 + s1));
        String ret = "";
        for (String str : nums) {
            ret += str;
        }
        return ret;
    }

    public int GetUglyNumber_Solution(int index) {
        if (index <= 0) return 0;
        int[] dp = new int[index];
        dp[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        for (int i = 1; i < index; i++) {
            int next2 = 2 * dp[i2], next3 = 3 * dp[i3], next5 = 5 * dp[i5];
            dp[i] = Math.min(next2, Math.min(next3, next5));
            if (dp[i] == next2)  i2++;
            if (dp[i] == next3)  i3++;
            if (dp[i] == next5)  i5++;
        }
        return dp[index - 1];
    }

    public int FirstNotRepeatingChar2(String str) {
        BitSet bs1 = new BitSet(256);
        BitSet bs2 = new BitSet(356);
        for (int c : str.toCharArray()) {
            if (!bs1.get(c) && !bs2.get(c))
                bs1.set(c);
            else if (bs1.get(c) && !bs2.get(c))
                bs2.set(c);
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (bs1.get(c) && !bs2.get(c))
                return i;
        }
        return -1;
    }

    private long cnt = 0;
    private int[] tmp;
    public int InversePairs(int[] array) {
        tmp = new int[array.length];
        mergeSort(array, 0, array.length - 1);
        return (int) (cnt % 1000000007);
    }
    private void mergeSort(int[] array, int l, int h) {
        if (h - l < 1) return;
        int m = l + (h - l) / 2;
        mergeSort(array, l, m);
        mergeSort(array, m + 1, h);
        merge(array, l, m, h);
    }
    private void merge(int[] array, int l, int m, int h) {
        int i = l, j = m + 1, k = l;
        while (i <= m || j <= h) {
            if (i > m)
                tmp[k] = array[j++];
            else if (j > h)
                tmp[k] = array[i++];
            else if (array[i] <= array[j])
                tmp[k] = array[i++];
            else {
                tmp[k] = array[j++];
                this.cnt += m - i + 1;
            }
            k++;
        }
        for (k = l; k <= h; k++) {
            array[k] = tmp[k];
        }
    }


 public class ListNode {
     int val;
     ListNode next = null;

     ListNode(int val) {
         this.val = val;
     }
 }
    public class Solution {
        public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
            ListNode l1 = pHead1, l2 = pHead2;
            while (l1 != l2) {
                l1 = (l1 == null) ? pHead2 : l1.next;
                l2 = (l2 == null) ? pHead1 : l2.next;
            }
            return l1;

        }
    }

    public int GetNumberOfK(int [] array , int k) {

        int first = binarySearch(array, k);
        int last = binarySearch(array, k + 1);
        return (first == array.length || array[first] != k) ? 0 : last - first;
    }
    public int binarySearch(int[] array, int k) {
        int h = array.length;
        int l = 0;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (array[m] >= k) h = m;
            else l = m + 1;
        }
        return l;
    }

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
    public int TreeDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(dfs(root.left, 1), dfs(root.right, 1));
    }
    private int dfs(TreeNode root, int count) {
        if (root == null) return count;
        return Math.max(dfs(root.left, count + 1),dfs(root.right, count + 1));
    }

    private boolean isBalanced = true;
    public boolean IsBalanced_Solution(TreeNode root) {
        height(root);
        return isBalanced;
    }
    private int height(TreeNode root) {
        if (root == null || !isBalanced) return 0;
        int left = height(root.left);
        int right = height(root.right);
        if (Math.abs(left - right) > 1) isBalanced = false;
        return 1 + Math.max(left, right);
    }

    //num1,num2分别为长度为1的数组。传出参数
    //将num1[0],num2[0]设置为返回结果
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        int diff = 0;
        for (int num : array) {
            diff ^= num;
        }
        diff &= -diff;
        for (int num : array) {
            if ((diff & num) == 0) {
                num1[0] ^= num;
            } else {
                num2[0] ^= num;
            }
        }
    }
    public ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (array == null || array.length == 0) return ret;
        int i = 0, j = array.length - 1, k = 0;
        while (array[i] + array[j] != sum) {
            if (j - i < 1) {
                k = 1;
                break;
            }
            if (array[i] + array[j] < sum)
                i++;
            else
                j--;
        }
        if (k == 1){
            return ret;
        }
        ret.add(array[i]);
        ret.add(array[j]);
        return ret;

    }
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        int first = 1, end = 2;
        int curSum = 3;
        while (end < sum) {
            if (curSum > sum) {
                curSum -= first;
                first++;
                if (first >= end) break;
            } else if (curSum < sum) {
                end++;
                curSum += end;
            } else {
                ArrayList<Integer> tmp = new ArrayList<>() ;
                for (int i = first; i <= end; i++) {
                    tmp.add(i);
                }
                ret.add(tmp);
                curSum -= first + first + 1;
                end++;
                curSum += end;
                first += 2;
            }
        }
        return ret;
    }


    public String ReverseSentence(String str) {
        int n = str.length();
        char[] rev = str.toCharArray();
        int i = 0, j = 0;
        while (j <= n) {
            if (j == n || rev[j] == ' ') {
                reverse(rev, i, j - 1);
                i = j + 1;
            }
            j++;
        }
        reverse(rev, 0, n - 1);
        return new String(rev);
    }
    private void reverse(char[] rev, int i, int j) {
        while (i < j) {
            swap(rev, i++, j--);
        }
    }

    private void swap(char[] rev, int i, int j) {
        char tmp = rev[i];
        rev[i] = rev[j];
        rev[j] = tmp;
    }

    public String LeftRotateString(String str,int n) {
        if (str == null || str.length() == 0) return "";
       int len = str.length();
       char[] ret = str.toCharArray();
       reverse(ret, 0, n - 1);
       reverse(ret, n, len - 1);
       reverse(ret, 0, len - 1);
       return new String(ret);
    }

    public boolean isContinuous(int [] numbers) {
        int n = numbers.length;
        if (n < 5) return false;
        Arrays.sort(numbers);
        int cnt = 0;
        for (int number : numbers) {
            if (number == 0) {
                cnt++;
            }
        }
        for (int i = cnt + 1; i < n; i++) {
            if (numbers[i] - numbers[i - 1] == 1) {
                continue;
            } else if (numbers[i] == numbers[i - 1]) {
                return false;
            } else {
                cnt -= numbers[i] - numbers[i - 1] + 1;
            }

        }
        return cnt >= 0;
    }
    public int LastRemaining_Solution(int n, int m) {
        if (n == 0) return -1;
        if (n == 1) return 0;
        return (LastRemaining_Solution(n - 1, m) + m)% n;
    }

    public int Sum_Solution(int n) {
        int sum = n;
        boolean b = (n > 0) && ((sum += Sum_Solution(n - 1)) > 0);
        return sum;
    }
    public int Add(int num1,int num2) {
         return (num2 == 0) ? num1 : Add(num1 ^ num2, (num1 & num2) << 1);
    }
    public int StrToInt(String str) {
        if (str == null || str.length() == 0)
            return 0;
        int ret = 0;
        int n = str.length();
        boolean isNegative = str.charAt(0) == '-';
        for (int i = 0; i < n; i++) {
            char tmp = str.charAt(i);
            if(i == 0 && (tmp == '-' || tmp == '+'))
                continue;
            if (tmp < '0' || tmp > '9')
                return 0;
            ret = ret * 10 + tmp - '0';
            if (isNegative && ret == Integer.MAX_VALUE / 10 && tmp == '8') {
                return Integer.MIN_VALUE;
            }
            if (ret % 10 != tmp - '0')
                return 0;
        }
        return ret = isNegative ? -ret : ret;

    }
    // Parameters:
    //    numbers:     an array of integers
    //    length:      the length of array numbers
    //    duplication: (Output) the duplicated number in the array number,length of duplication array is 1,so using duplication[0] = ? in implementation;
    //                  Here duplication like pointor in C/C++, duplication[0] equal *duplication in C/C++
    //    这里要特别注意~返回任意重复的一个，赋值duplication[0]
    // Return value:       true if the input is valid, and there are some duplications in the array number
    //                     otherwise false
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        if (numbers == null || length == 0)
            return false;
        for (int i = 0; i < length; i++) {
            if (numbers[i] != i && numbers[numbers[i]] != numbers[i]) {
                swap(numbers, i, numbers[i]);
            } else  if (numbers[i] == i) {
                continue;
            } else {
                duplication[0] = numbers[i];
                return true;
            }
        }
        return false;
    }
    private void swap(int[] num, int i, int j) {
        int tmp = num[i];
        num[i] = num[j];
        num[j] = tmp;
    }
    public int[] multiply(int[] A) {
        if (A == null || A.length == 0)
            return new int[0];
        int n = A.length;
        int[] B = new int[n];
        for (int i = 0, pro = 1; i < n; i++) {
            B[i] = pro;
            pro *= A[i];
        }
        for (int i = n - 1, pro = 1; i >= 0; i--) {
            B[i] *= pro;
            pro *= A[i];
        }
        return B;
    }


    private int[] cnts = new int[256];
    private Queue<Character> queue = new LinkedList<>();
    //Insert one char from stringstream
    public void Insert(char ch)
    {
        cnts[ch]++;
        queue.add(ch);
        while (!queue.isEmpty() && cnts[queue.peek()] > 1) {
            queue.poll();
        }
    }
  //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        return queue.isEmpty() ? '#' : queue.peek();
    }

    public ListNode EntryNodeOfLoop(ListNode pHead)
    {
        if (pHead == null || pHead.next == null)
            return null;
        ListNode fast = pHead, slow = pHead;
        do {
            fast = fast.next.next;
            slow = slow.next;
        } while (fast != slow);
        fast = pHead;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }
    public ListNode deleteDuplication(ListNode pHead)
    {
        if (pHead == null || pHead.next == null) return pHead;
        ListNode next = pHead.next;
        if (pHead.val == next.val) {
            while (next != null && pHead.val == next.val) {
                next = next.next;
            }
            return deleteDuplication(next);
        } else {
            pHead.next = deleteDuplication(pHead.next);
            return pHead;
        }
    }
    public class TreeLinkNode {
      int val;
      TreeLinkNode left = null;
      TreeLinkNode right = null;
      TreeLinkNode next = null;

      TreeLinkNode(int val) {
          this.val = val;
      }
    }
    public TreeLinkNode GetNext(TreeLinkNode pNode)
    {
        if (pNode.right != null) {
            TreeLinkNode node = pNode.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        } else {
            while (pNode.next != null) {
                TreeLinkNode parent = pNode.next;
                if (parent.left == pNode)
                    return parent;
                pNode = parent;
            }
        }
        return null;
    }
    boolean isSymmetrical(TreeNode pRoot)
    {
        if (pRoot == null)
            return true;
        return isSymmetrical(pRoot.left, pRoot.right);
    }
    private boolean isSymmetrical(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null)
            return true;
        if (t1 == null || t2 == null)
            return false;
        if (t1.val != t2.val)
            return false;
        return isSymmetrical(t1.left, t2.right) &&  isSymmetrical(t1.right, t2.left);
    }
    public ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        boolean reverse = false;
        while (queue.size() != 0) {
            int cnt = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            while(cnt-- > 0) {
                TreeNode root = queue.poll();
                if (root == null)
                    continue;
                list.add(root.val);
                queue.add(root.left);
                queue.add(root.right);
            }
            if (reverse)
                Collections.reverse(list);
            reverse = !reverse;
            if (list.size() != 0)
                ret.add(list);
        }
        return ret;
    }

    ArrayList<ArrayList<Integer> > Print1(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        while (queue.size() != 0) {
            ArrayList<Integer> list = new ArrayList<>();
            int cnt = queue.size();
            while(cnt-- > 0) {
                TreeNode root = queue.poll();
                if (root == null)
                    continue;
                list.add(root.val);
                queue.add(root.left);
                queue.add(root.right);
            }
            if (list.size() != 0) {
                ret.add(list);
            }
        }
        return ret;
    }
    private String deserializeStr;
    String Serialize(TreeNode root) {
        if (root == null)
            return "#";
        return root.val + " " + Serialize(root.left) + " " + Serialize(root.right);
    }
    TreeNode Deserialize(String str) {
        deserializeStr = str;
        return Deserialize();
    }
    private TreeNode Deserialize() {
        if (deserializeStr.length() == 0)
            return null;
        int node = deserializeStr.indexOf(" ");
        String val = node == -1 ? deserializeStr : deserializeStr.substring(0, node);
        deserializeStr = node == -1 ? "" : deserializeStr.substring(node + 1);
        if (val.equals("#"))
            return null;
        int value = Integer.parseInt(val);
        TreeNode t = new TreeNode(value);
        t.left = Deserialize();
        t.right = Deserialize();
        return t;
    }

    private TreeNode ret;
    private int cnt1 = 0;
    TreeNode KthNode(TreeNode pRoot, int k)
    {
       inOrder(pRoot, k);
       return ret;
    }
    private void inOrder(TreeNode pRoot, int k) {
        if (pRoot == null || cnt1 >= k) {
            return;
        }
        inOrder(pRoot.left, k);
        cnt1++;
        if (cnt1 == k) ret = pRoot;
        inOrder(pRoot.right, k);
    }
    private PriorityQueue<Integer> left = new PriorityQueue<>((o1, o2) -> o1 - o2);
    private PriorityQueue<Integer> right = new PriorityQueue<>();
    private int N = 0;

    public void Insert(Integer num) {
        if (N % 2 == 0) {
            left.add(num);
            right.add(left.poll());
        } else {
            right.add(num);
            left.add(right.poll());
        }
        N++;
    }

    public Double GetMedian() {
        return N % 2 == 0 ? (left.peek() + right.peek()) / 2.0 : left.peek() / 1.0;
    }
    public ArrayList<Integer> maxInWindows(int [] num, int size)
    {
        if (num == null || num.length == 0 || size == 0 || num.length < size)
            return null;
        ArrayList<Integer> ret = new ArrayList<>();
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);

        for (int i = 0; i < num.length; i++) {
            if (i < size - 1) {
                queue.add(num[i]);
            } else {
                queue.add(num[i]);
                ret.add(queue.peek());
                queue.remove(num[i - size + 1]);
            }
        }
        return ret;
    }

    private final static int[][] next = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    private int rows;
    private int cols;

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str)
    {
        if (rows == 0 || cols == 0) return false;
        this.rows = rows;
        this.cols = cols;
        char[][] ma = new char[rows][cols];
        int k = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                ma[i][j] = matrix[k++];
            }
        }
        boolean[][] marked = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (ma[i][j] == str[0])
                    if (isHasPath(ma, marked,i, j, str, 0))
                        return true;
            }
        }
        return false;
    }
    private boolean isHasPath(char[][] ma, boolean[][] marked, int r, int c, char[] str, int pathLen) {
        if (pathLen == str.length) return true;
        if (r < 0 || r >= rows || c < 0 || c >= cols
                || ma[r][c] != str[pathLen] || marked[r][c]) {
            return false;
        }
        marked[r][c] = true;
        for (int[] n : next) {
            if (isHasPath(ma, marked, n[0] + r, n[1] + c, str, pathLen + 1)) {
                return true;
            }
        }
        marked[r][c] = false;
        return false;
    }
    public int cutRope(int target) {
        int[] dp = new int[target + 1];
        for (int i = 2; i < target + 1; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(Math.max(dp[i], dp[i - j] * j), (i - j) * j);
            }
        }
        return dp[target];
    }

    public boolean match(char[] str, char[] pattern)
    {
        int m = str.length, n = pattern.length;
        boolean[][] dp = new boolean[m + 1][n + 1];
         dp[0][0] = true;
         for (int i = 1; i <= n; i++) {
             if (pattern[i - 1] == '*') {
                 dp[0][i] = dp[0][i - 2];
             }
         }

         for (int i = 1; i < m; i++) {
             for (int j = 1; j < n; j++) {
                 if (str[i - 1] == pattern[j - 1] || pattern[j - 1] == '.') {
                     dp[i][j]  = dp[i - 1][j - 1];
                 } else if (pattern[j - 1] == '*') {
                     if (pattern[j - 2] == str[i - 1] || pattern[j - 2] == '.') {
                         dp[i][j] |= dp[i][j - 1];
                         dp[i][j] |= dp[i - 1][j];
                         dp[i][j] |= dp[i][j - 2];
                     } else {
                         dp[i][j] = dp[i][j - 2];
                     }
                 }
             }
         }
         return dp[m][n];
    }


    public boolean isNumeric(char[] str) {
        if (str == null || str.length == 0) {
            return false;
        }
        boolean hasDot = false, hasE = false;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '.') {
                if (hasDot) {
                    // 已经出现过'.'
                    return false;
                } else {
                    hasDot = true;
                }
            } else if (isE(str[i])) {
                if (hasE) {
                    // 已经出现过'E'
                    return false;
                }
                // 'E'后面不会再出现'E'和'.'
                hasE = true;
                hasDot = true;
            } else if (isPlusMinus(str[i])) {
                if (i != 0 && (!hasE || !isE(str[i - 1]))) {
                    // '+''-'不是第一个且前面不是'E'
                    return false;
                }
            } else if (!isNumber(str[i])) {
                return false;
            }
        }
        // 最后一个不是'E'
        return !isE(str[str.length - 1]);
    }

    private boolean isE(char c) {
        return c == 'E' || c == 'e';
    }

    private boolean isPlusMinus(char c) {
        return c == '+' || c == '-';
    }

    private boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }
}
