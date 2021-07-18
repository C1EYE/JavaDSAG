package com.mj.回溯;

public class Main {
    //索引是皇后的行号,值是皇后所在的列号
    private int[] cols;
    private int ways = 0;

    public static void main(String[] args) {
        new Main().placeQueens(4);
    }

    void placeQueens(int n) {
        if (n < 1) { return; }
        cols = new int[n];
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
            if (isValid(row, col)) {
                //在当前位置摆放皇后
                cols[row] = col;
                place(row + 1);
            }
        }

    }

    boolean isValid(int row, int col) {
        for (int i = 0; i < row; i++) {
            //当前列
            if (cols[i] == col) {
                System.out.println("["+row+"]["+col+"]=false");
                return false;
            }
            //斜线上 用斜率算
            if (row - i == Math.abs(col - cols[i])) {
                System.out.println("["+row+"]["+col+"]=false");
                return false; }
        }
        System.out.println("["+row+"]["+col+"]=true");
        return true;
    }

    void show() {
        System.out.println("第" + ways + "种摆法");
        for (int row = 0; row < cols.length; row++) {
            for (int col = 0; col < cols.length; col++) {
                if (cols[row] == col) {
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
