package com.mj.DP;

public class Knapsack {
    public static void main(String[] args) {
        int[] values = {6, 3, 5, 4, 6};
        int[] weights = {2, 2, 6, 5, 4};
        int capacity = 10;
        System.out.println(maxValue2(values, weights, capacity));
        System.out.println(maxValueExactly(values, weights, capacity));

    }

    static int maxValueExactly(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0) { return 0; }
        if (weights == null || weights.length == 0) { return 0; }
        if (values.length != weights.length || capacity <= 0) { return 0; }

        int[] dp = new int[capacity + 1];
        for (int i = 1; i <= capacity; i++) {
            dp[i] = Integer.MIN_VALUE;
        }
        for (int i = 1; i <= values.length; i++) {
            //容量不足就没有选择的必要
            for (int j = capacity; j >= weights[i - 1]; j--) {
                if (j >= weights[i - 1]) {
                    dp[j] = Math.max(dp[j], values[i - 1] + dp[j - weights[i - 1]]);
                }
            }
        }
        return dp[capacity] < 0 ? -1 : dp[capacity];
    }


    static int maxValue2(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0) { return 0; }
        if (weights == null || weights.length == 0) { return 0; }
        if (values.length != weights.length || capacity <= 0) { return 0; }

        int[] dp = new int[capacity + 1];
        for (int i = 1; i <= values.length; i++) {
            //容量不足就没有选择的必要
            for (int j = capacity; j >= weights[i - 1]; j--) {
                if (j >= weights[i - 1]) {
                    dp[j] = Math.max(dp[j], values[i - 1] + dp[j - weights[i - 1]]);
                }
            }
        }
        return dp[capacity];
    }


    static int maxValue(int[] values, int[] weights, int capacity) {
        if (values == null || values.length == 0) { return 0; }
        if (weights == null || weights.length == 0) { return 0; }
        if (values.length != weights.length || capacity <= 0) { return 0; }

        int[][] dp = new int[values.length + 1][capacity + 1];
        for (int i = 1; i <= values.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (j < weights[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], values[i - 1] + dp[i - 1][j - weights[i - 1]]);
                }
            }
        }
        return dp[values.length][capacity];
    }
}
