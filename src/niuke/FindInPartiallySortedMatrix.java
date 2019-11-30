package niuke;
//从右上角来遍历，也就是从较为集中的点出手，当然也可以从左下角开始
//如果该数字大于要查找的数字，则剔除这个数字所在的列，如果该数字小于要查找的数字，则剔除这个数字所在的行
public class FindInPartiallySortedMatrix {
    /*public static void main(String[] args) {
        int[][] array = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        FindInPartiallySortedMatrix f = new FindInPartiallySortedMatrix();
        System.out.println(f.Find(1,array));
    }*/
       boolean Find(int target, int[][] array) {
        int rows = array.length;
        int columns = array[0].length;
        boolean found = false;
        if (array != null && rows > 0 && columns > 0) {
            int row = 0;
            int column =  columns - 1;
            while(row < rows && column >= 0) {
                if(array[row][column] == target) {
                    found = true;
                    break;
                } else if (array[row][column] > target) {
                    column--;
                } else {
                    row++;
                }
            }
            System.out.println(row + ":" + column);
        }
        return found;
    }
}
