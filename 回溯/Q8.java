package com.mj.回溯;

public class Q8 {
    //标记该列有无皇后
    byte cols;
    // \
    short leftTop;
    // /
    short rightTop;
    int[] queens;
    private int ways = 0;

    public static void main(String[] args) {
        new NQ().placeQueens(8);
    }

    void placeQueens(int n) {
        if (n < 1) { return; }
        queens = new int[n];
        cols = 0;
        leftTop  = 0;
        rightTop = 0;
        place(0);
        System.out.println(n + "皇后共有" + ways + "种摆法");
    }

    /**
     * 从row行开始摆放
     *
     * @param row
     */
    void place(int row) {
        if (row == 8) {
            ways++;
            show();
            return;
        }
        for (int col = 0; col < 8; col++) {
            if ((cols & (1 << col)) != 0) { continue; }
            int ltIndex = row - col + 7;
            if ((leftTop & (1 << ltIndex)) == 1) { continue; }
            int rtIndex = row + col;
            if ((rightTop & (1 >> rtIndex)) == 1) { continue; }

            //在当前位置摆放皇后
            cols |= (1 << col);
            leftTop |= (1 << ltIndex);
            rightTop |= (1 << rtIndex);
            queens[row] = col;
            place(row + 1);
            cols &= ~(1 << col);
            leftTop &= ~(1 << ltIndex);
            rightTop &= ~(1 << rtIndex);
        }
    }

    void show() {
        System.out.println("第" + ways + "种摆法");
        for (int row = 0; row < queens.length; row++) {
            for (int col = 0; col < queens.length; col++) {
                if (queens[row] == col) {
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

