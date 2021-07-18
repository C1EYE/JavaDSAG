package com.mj.DP;

public class LCSubstring {
    public static void main(String[] args) {
        System.out.println(lcs2("ABCDBA", "ABCDA"));
    }

    static int lcs2(String str1,String str2){
        if (str1 == null || str2 == null) { return 0; }
        char[] chars1 = str1.toCharArray();
        if (chars1.length == 0) { return 0; }
        char[] chars2 = str2.toCharArray();
        if (chars2.length == 0) { return 0; }
        char[] rowNums = chars1, colNums = chars2;
        if(chars1.length<chars2.length){
            rowNums = chars2;
            colNums = chars1;
        }

        int[] dp = new int[colNums.length+1];
        int max = Integer.MIN_VALUE;
        for (int row = 1; row <= rowNums.length; row++) {
            for (int col = colNums.length; col >=1; col--) {
                if(chars1[row-1]==chars2[col-1]){
                    dp[col] = dp[col-1] + 1;
                }else{
                    dp[col] = 0;
                }
                max = Math.max(dp[col], max);
            }
        }

        return max;
    }


    static int lcs1(String str1,String str2){
        if (str1 == null || str2 == null) { return 0; }
        char[] chars1 = str1.toCharArray();
        if (chars1.length == 0) { return 0; }
        char[] chars2 = str2.toCharArray();
        if (chars2.length == 0) { return 0; }

        int[][] dp = new int[chars1.length + 1][chars2.length + 1];
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= chars1.length; i++) {
            for (int j = 1; j <= chars2.length; j++) {
                if(chars1[i-1]==chars2[j-1]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                max = Math.max(dp[i][j], max);
            }
        }

        return max;
    }

}
