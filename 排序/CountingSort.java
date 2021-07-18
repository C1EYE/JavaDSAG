package com.mj.排序;

public class CountingSort extends Sort<Integer> {
    @Override
    protected void sort() {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        int[] counts = new int[1 + max];
        for (int i = 0; i < array.length; i++) {
            counts[array[i]]++;
        }

        int index = 0;
        for (int i = 1; i < counts.length; i++) {
            while(counts[i]-- >0){
                array[index++] = i;
            }
        }


    }
}
