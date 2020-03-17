package sort;


import five_array.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Sort {
   /**
    * 2 * 冒泡排序
    * 3 *
    * 4 * @param array
    * 5 * @return
    * 最佳 O(n),最差 O(n2),平均 O(n2)
    * 6
    */
   public static int[] BubbleSort(int[] array) {
      if (array == null || array.length == 0) {
         return array;
      }
      for (int i = 0; i < array.length; i++) {
         for (int j = 0; j < array.length - 1 - i; j++) {
            if (array[j + 1] < array[j]) {
               int tmp = array[j];
               array[j] = array[j + 1];
               array[j + 1] = tmp;
            }
         }
      }
      return array;
   }

   /**
    * 选择排序，下标
    * 最佳情况：T(n) = O(n2)  最差情况：T(n) = O(n2)  平均情况：T(n) = O(n2)
    *
    * @param array
    * @return
    */
   public static int[] SelectionSort(int[] array) {
      if (array == null || array.length == 0) {
         return array;
      }
      for (int i = 0; i < array.length; i++) {
         int min = i;
         for (int j = i; j < array.length; j++) {
            if (array[j] < array[min]) {
               min = j;
            }
         }
         int tmp = array[min];
         array[min] = array[i];
         array[i] = tmp;
      }
      return array;
   }

   /**
    * 插入排序，while两者比较
    * 最佳情况：T(n) = O(n)   最坏情况：T(n) = O(n2)   平均情况：T(n) = O(n2)
    *
    * @param array
    * @return
    */
   public static int[] InsertionSort(int[] array) {
      if (array == null || array.length == 0) {
         return array;
      }
      int current;
      for (int i = 0; i < array.length; i++) {
         current = array[i + 1];
         int preIndex = i;
         while(preIndex >= 0 && array[preIndex] > current) {
            array[preIndex + 1] = array[preIndex];
            preIndex--;
         }
         array[preIndex + 1] = current;
      }
      return array;
   }

   /**
    * 希尔排序
    * 最佳情况：T(n) = O(nlog2 n)  最坏情况：T(n) = O(nlog2 n)  平均情况：T(n) =O(nlog2n)
    *
    * @param array
    * @return
    */
   public static int[] ShellSort(int[] array) {
      if (array == null || array.length == 0) {
         return array;
      }
      int len = array.length;
      int tmp, gap = len / 2;
      while (gap > 0) {
         for (int i = gap; i < len; i++) {
            tmp = array[i];
            int preIndex = i - gap;
            while (preIndex >= 0 && array[preIndex] > tmp) {
               array[preIndex + gap] = array[preIndex];
               preIndex -= gap;
            }
            array[preIndex + gap] = tmp;
         }
         gap /= 2;
      }
      return array;
   }

   /**
    * 归并排序，切分到最小，将数组向上返回
    * 最佳情况：T(n) = O(n)  最差情况：T(n) = O(nlogn)  平均情况：T(n) = O(nlogn)
    *
    * @param array
    * @return
    */
   public static int[] MergeSort(int[] array) {
      if (array.length < 2)
         return array;
      int mid = array.length / 2;
      int[] left = Arrays.copyOfRange(array,0, mid);
      int[] right = Arrays.copyOfRange(array, mid, array.length);
      return merge(MergeSort(left), MergeSort(right));
   }

   /**
    * @param left
    * @param right
    * @return
    */
   public static int[] merge(int[] left, int[] right) {
      int[] result = new int[left.length + right.length];
      for (int index = 0, i = 0, j = 0; index < result.length; index++) {
         if (i >= left.length) {
            result[index] = right[j++];
         } else if (j >= right.length) {
            result[index] = left[i++];
         } else if (left[i] >= right[j]) {
            result[index] = right[j++];
         } else {
            result[index] = left[i++];
         }
      }
      return result;
   }

   /**
    * 快速排序算法，排序返回的smallIndex 一次固定一个，不需要再进行排序
    * 最佳情况：T(n) = O(nlogn)   最差情况：T(n) = O(n2)   平均情况：T(n) = O(nlogn)
    *
    * @param array
    * @param start
    * @param end
    * @return
    */
   public static void QuickSort(int[] array, int start, int end) {
      if (array.length < 1 || start < 0 || end >= array.length || start > end)
         return ;
      int smallIndex = partition(array, start, end);
      if (smallIndex > start)
         QuickSort(array, start, smallIndex - 1);
      if (smallIndex < end)
         QuickSort(array, smallIndex + 1, end);;
   }

   public static int partition(int[] array, int start, int end) {
      int pivot = (int) (start + Math.random() * (end - start + 1));
      swap(array, pivot, start);
      int i = start, j = end, tmp = array[start];
      while (i < j) {
         while (i < j && array[j] >= tmp)  {
            j--;
         }
         while (i < j && array[i] <= tmp) {
            i++;
         }
         swap(array, i, j);
      }
      array[start] = array[i];
      array[i] = tmp;
      return i;
   }

   public static void swap(int[] array, int i, int j) {
      int temp = array[i];
      array[i] = array[j];
      array[j] = temp;
   }


   static int len;

   /**
    * 堆排序，此方法都是从上往下的时候，注意递归的临界条件
    * 最佳情况：T(n) = O(nlogn) 最差情况：T(n) = O(nlogn) 平均情况：T(n) = O(nlogn)
    *
    * @param array
    * @return
    */
   public static int[] HeapSort(int[] array) {
      len = array.length;
      if (len < 1) return array;
      buildMaxHeap(array);
      while (len > 0) {
         swap(array,0, len - 1);
         len--;
         adjustHeap(array, 0);
      }
      return array;
   }

   public static void buildMaxHeap(int[] array) {
      for (int i = (len / 2 - 1); i >= 0; i--) {
         adjustHeap(array, i);
      }
   }

   public static void adjustHeap(int[] array, int i) {
      int maxIndex = i;
      if (i * 2 < len && array[maxIndex] < array[i * 2])
         maxIndex = i * 2;
      if (i * 2 + 1 < len && array[maxIndex] < array[i * 2 + 1])
         maxIndex = i * 2 + 1;
      if (maxIndex != i) {
         swap(array, maxIndex, i);
         adjustHeap(array, maxIndex);
      }
   }

   /**
    * 计数排序
    * 最佳情况：T(n) = O(n+k)  最差情况：T(n) = O(n+k)  平均情况：T(n) = O(n+k)
    *
    * @param array
    * @return
    */
   public static int[] CountingSort(int[] array) {
      if (array.length == 0)
         return array;
      int bias, min = array[0], max = array[0];
      for (int i = 1; i < array.length; i++) {
         if (array[i] > max)
            max = array[i];
         if (array[i] < min)
            min = array[i];
      }
      bias = 0 - min;
      int[] bucket = new int[max - min + 1];

      for (int i = 0; i < array.length; i++) {
         bucket[array[i] + bias]++;
      }
      int index = 0, i = 0;
      while (index < array.length) {
         if (bucket[i] != 0) {
            array[index] = i - bias;
            bucket[i]--;
            index++;
         } else
            i++;
      }
      return array;
   }

   /**
    * 注意，如果递归使用桶排序为各个桶排序，
    * 则当桶数量为1时要手动减小BucketSize增加下一循环桶的
    * 数量，否则会陷入死循环，导致内存溢出。
    * 最佳情况：T(n) = O(n+k)   最差情况：T(n) = O(n+k)   平均情况：T(n) = O(n2)
    *
    * @param array
    * @param bucketSize
    * @return
    */
   public static ArrayList<Integer> BucketSort(ArrayList<Integer> array, int bucketSize) {
      if (array == null || array.size() < 2)
         return array;
      int max = array.get(0), min = array.get(0);

      for (Integer value : array) {
         if (value > max)
            max = value;
         if (value < min)
            min = value;
      }
      int bucketCount = (max - min) / bucketSize + 1;
      ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketCount);
      ArrayList<Integer> resultArr = new ArrayList<>();
      for (int i = 0; i < bucketCount; i++) {
         bucketArr.add(new ArrayList<Integer>());
      }
      for (Integer integer : array) {
         bucketArr.get((integer - min) / bucketSize).add(integer);
      }

      for (int i = 0; i < bucketCount; i++) {
         if (bucketSize == 1) {// 如果待排序数组中有重复数字时,多个桶，size却为1，直接按顺序遍历即可
            for (int j = 0; j < bucketArr.get(i).size(); j++)
               resultArr.add(bucketArr.get(i).get(j));
         } else {
            if (bucketCount == 1)
               bucketSize--;
            ArrayList<Integer> temp = BucketSort(bucketArr.get(i), bucketSize);
            for (Integer integer : temp)
               resultArr.add(integer);
         }
      }
      return resultArr;
   }


   /**
    * 基数排序
    * 最佳情况：T(n) = O(n * k)   最差情况：T(n) = O(n * k)   平均情况：T(n) = O(n * k)
    *
    *
    * @param array
    * @return
    */
   public static int[] RadixSort(int[] array) {
      if (array == null || array.length < 2)
         return array;
// 1.先算出最大数的位数；
      int max = array[0];
      for (int i = 1; i < array.length; i++) {
         max = Math.max(max, array[i]);
      }
      int maxDigit = 0;
      while (max != 0) {
         max /= 10;
         maxDigit++;
      }
      int mod = 10, div = 1;
      ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>();
      for (int i = 0; i < 10; i++) {
         bucketList.add(new ArrayList<>());
      }
      for (int i = 0; i < maxDigit; i++, mod *= 10, div *= 10) {
         for (int j = 0; j < array.length; j++) {
            int num = (array[j] % mod) / div;
            bucketList.get(num).add(array[j]);
         }
      }
      int index = 0;
      for (int i = 0; i < bucketList.size(); i++) {
         for (int j = 0; j < bucketList.get(j).size(); j++)
            array[index++] = bucketList.get(i).get(j);
         bucketList.get(i).clear();
      }
      return array;
   }

   public static void main(String[] args) {
      int[] nums = new int[]{1,9,7,2,5,6,6,3,4,8};
      //QuickSort(nums, 0, nums.length - 1);
      HeapSort(nums);
       System.out.println(Arrays.toString(nums));
   }
}

