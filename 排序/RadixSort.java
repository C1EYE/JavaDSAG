package com.mj.排序;

import java.util.Arrays;

public class RadixSort extends Sort<Integer> {
    @Override
    protected void sort() {
        int max = array[0];
        for(int i = 1;i<array.length;i++){
            if(array[i]>max){
                max = array[i];
            }
        }

        for (int divider = 1; divider <= max; divider*=10) {
            countingSort(divider);
        }

    }

    protected void countingSort(int divider) {
        //开辟存储空间
        int[] counts = new int[10];
        //统计每个元素出现的次数
        for (int i = 0; i < array.length; i++) {
            counts[array[i]/divider%10]++;
        }
        //累加次数
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        //从后向前遍历元素，放在合适位置，为了稳定性
        int[] newArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            newArray[--counts[array[i]/divider%10]] = array[i];
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = newArray[i];
        }

    }
}
