package com.mj.布隆过滤器;

public class Main {
    private static final int NUM = 1000_0000;
    private static final double P = 0.1;
    public static void main(String[] args) {
        BloomFilter<Integer> bloomFilter = new BloomFilter<>(NUM, P);
        for (int i = 0; i < NUM; i++) {
            bloomFilter.put(i);
        }
        int count = 0;
        for (int i = 0; i < NUM+(NUM>>1); i++) {
           if(bloomFilter.contains(i)){
               count++;
           }
        }
        System.out.println("包含"+count+"\n"+"误判率"+(double)(count-NUM)/NUM);


    }
}
