package niuke;

public class ReOrderArray {
    //冒泡思想，将所有偶数移动到最右侧
    public void reOrderArray(int[] array) {
        int N = array.length;
        for (int i = N - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (isEven(array[j]) && !isEven(array[j+1])) {
                    swap(array, j, j+1);
                }
            }
        }
    }

    private boolean isEven(int num) {
        return num % 2 == 0;
    }

    private void swap(int[] array, int i, int j) {
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
    }
    /*public void reOrderArray(int[] array) {
        int oddNum = 0;
        for (int num : array) {
            if(num % 2 != 0) {
                oddNum += 1;
            }
        }
        int[] copy = array.clone();
        int i = 0, j = oddNum;
        for(int num : copy) {
            if(num % 2 == 0) array[j++] = num;
            else array[i++] = num;
        }
    }*/
}
