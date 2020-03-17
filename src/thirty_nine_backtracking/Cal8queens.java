package thirty_nine_backtracking;

public class Cal8queens {
    int [] result = new int[8];
    int k = 1;
    public void cal8queens(int row) {
        if(row == 8) {
            printQueens(result);
            return;
        }
        for (int column = 0; column < 8; column++) {
            if (isOK(row, column)) {
                result[row] = column;
                //递归方式增加行数
                cal8queens(row + 1);
            }
        }
    }
    private boolean isOK(int row, int column) {
        int leftUp = column - 1, rightUp = column + 1;
        // 往上考察每一行
        for (int i = row - 1; i >= 0; i--) {
            if (result[i] == column) return false;
            if (leftUp >= 0) {// 考察左上角对角线
                if (result[i] == leftUp) return false;
            }
            if (rightUp < 8) {// 考察右上角对角线
                if (result[i] == rightUp) return false;
            }
            leftUp--;rightUp++;
        }
        return true;
    }

    private void printQueens(int[] result) {
        for (int row = 0; row < 8; row++) {
            for (int column = 0;column < 8; column++) {
                if (result[row] == column) System.out.print("Q ");
                else System.out.print("* ");
            }
            System.out.println();
        }
        System.out.println("第" + k + "种解法");
        k++;
    }

    public static void main(String[] args) {
        Cal8queens cal = new Cal8queens();
        // 8皇后有92种解法, 一共有8^8种排列组合方式
        cal.cal8queens(0);
    }
}
