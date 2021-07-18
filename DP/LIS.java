package com.mj.DP;

public class LIS {
    public static void main(String[] args) {
        System.out.println(lengthOfLIS2(new int[]{5, 1, 2, 4, 3, 7}));
    }

    static int lengthOfLIS2(int[] nums){
        if (nums == null || nums.length == 0) { return 0; }
        int len = 0;
        int[] top = new int[nums.length];
        for (var num: nums){
//            int j = 0;
//            while (j<len){
//                if(top[j]>=num){
//                    top[j] = num;
//                    break;
//                }
//                j++;
//            }
//            if(j==len){
//                len++;
//                top[j] = num;
//            }
            int begin = 0;
            int end = len;
            while (begin<end){
                int mid = (begin + end) >> 1;
                if(num <=top[mid]){
                    end = mid;
                }else{
                    begin = mid + 1;
                }
            }
            top[begin] = num;
            if (begin == len) { len++; }
        }
        return len;
    }

    static int lengthOfLIS(int[] nums){
        if (nums == null || nums.length == 0) { return 0; }
        int[] dp = new int[nums.length];
        int max = dp[0] = 1;
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }
}
