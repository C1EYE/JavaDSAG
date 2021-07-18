package com.mj.DP;

public class MaxSubArray {
    public static void main(String[] args) {
        System.out.println(maxSubArray2(new int[]{1, 5, 2, 8, -1, -8, 0, 4}));
    }

    public static int maxSubArray1(int[] nums){
        if (nums == null || nums.length == 0) { return 0; }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < dp.length; i++) {
            if(dp[i-1]<=0){
                dp[i] = nums[i];
            }else{
                dp[i] = dp[i - 1] + nums[i];
            }
            max = Math.max(max, dp[i]);

        }
        return max;

    }

    //滚动数组优化
    public static int maxSubArray2(int[] nums){
        if (nums == null || nums.length == 0) { return 0; }
        int dp = nums[0];
        int max = dp;
        for (int i = 1; i < nums.length; i++) {
            if(dp<=0){
                dp = nums[i];
            }else{
                dp += nums[i];
            }
            max = Math.max(max, dp);

        }
        return max;

    }

}
