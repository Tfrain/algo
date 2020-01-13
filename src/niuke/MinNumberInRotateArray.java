package niuke;

public class MinNumberInRotateArray {
    public int minNumberInRotateArray(int[] array) {
        if (array == null || array.length == 0) return 0;
        int l = 0, h = array.length-1;
        while(l < h) {
            int m = l + (h - l) / 2;
            if(array[l] == array[m] && array[m]==array[h]) return minNumber(array, l, h);
            //这种不行，因为遇见54，这样的就不行,感觉这个很难捉摸到啊，用后面的比较排除后面的。
            // else if (array[m] <= array[l]) h = m;
            else if(array[m] <= array[h]) h = m;
            else l = m + 1;
        }
        return array[l];
    }
    private int minNumber(int[] array, int l, int h) {
        for(int i = l; i < h-1; i++) {
            if(array[i] > array[i+1]) return array[i+1];
        }
        return array[l];
    }
}
