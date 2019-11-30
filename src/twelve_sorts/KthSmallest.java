package twelve_sorts;

//我们选择数组区间A[0…n-1]的最后一个元素A[n-1]作为pivot，对数组A[0…n-1]原地分区，
// 这样数组就分成了三部分，A[0…p-1]、A[p]、A[p+1…n-1]。如果p+1=K，那A[p]就是要求解的元素；
// 如果K>p+1, 说明第K大元素出现在A[p+1…n-1]区间，我们再按照上面的思路递归地在A[p+1…n-1]这个区间内查找。
// 同 理，如果K<p+1，那我们就在A[0…p-1]区间查找。
public class KthSmallest {
    public static int kthSmallest(int[] arr, int k) {
        if (arr == null || arr.length < k) {
            return -1;
        }
        //从小到大排序，求出第k小的元素
        int partition = partition(arr, 0, arr.length - 1);
        while (partition + 1 != k) {
            //k大，则k在后面，否则在前面
            if (partition + 1 < k) {
                partition = partition(arr, partition + 1, arr.length - 1);
            } else {
                partition = partition(arr, 0, partition - 1);
            }
        }
        return arr[partition];
    }


    private static int partition(int[] arr, int p, int r) {
        int pivot = arr[r];
        //典型快排思想
        int i = p;
        for (int j = p; j < r; j++) {
            // 这里要是 <= ，不然会出现死循环，比如查找数组 [1,1,2] 的第二小的元素
            if(arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, r);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }

        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 3, 6, 5, 7, 33, 22};
        int res = kthSmallest(arr,4);
        System.out.println(res);
    }
}
