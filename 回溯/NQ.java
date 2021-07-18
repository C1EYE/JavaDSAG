package com.mj.回溯;

public class NQ {
    //标记该列有无皇后
    boolean[] cols;
    // \
    boolean[] leftTop;
    // /
    boolean[] rightTop;
    int[] queens;
    private int ways = 0;

    public static void main(String[] args) {
        new NQ().placeQueens(8);
    }

    void placeQueens(int n) {
        if (n < 1) { return; }
        queens = new int[n];
        cols = new boolean[n];
        leftTop = new boolean[(n << 1) - 1];
        rightTop = new boolean[leftTop.length];
        place(0);
        System.out.println(n + "皇后共有" + ways + "种摆法");
    }

    /**
     * 从row行开始摆放
     *
     * @param row
     */
    void place(int row) {
        if (row == cols.length) {
            ways++;
            show();
            return;
        }
        for (int col = 0; col < cols.length; col++) {
            if (cols[col]) { continue; }
            int ltIndex = row - col + cols.length-1;
            if (leftTop[ltIndex]) { continue; }
            int rtIndex = row + col;
            if (rightTop[rtIndex]) { continue; }

            //在当前位置摆放皇后
            cols[col] = true;
            leftTop[ltIndex] = true;
            rightTop[rtIndex] = true;
            queens[row] = col;
            place(row + 1);
            cols[col] = false;
            leftTop[ltIndex] = false;
            rightTop[rtIndex] = false;
        }


    }

    void show() {
        System.out.println("第" + ways + "种摆法");
        for (int row = 0; row < queens.length; row++) {
            for (int col = 0; col < queens.length; col++) {
                if (queens[row]==col) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        System.out.println("----------------------");
    }

}

