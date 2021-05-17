package com.mj.排序.cmp;

import com.mj.排序.Sort;

public class SelectionSort<E extends Comparable<E>> extends Sort<E> {

    @Override
    protected void sort() {
        for (int end = array.length - 1; end > 0; end--) {
            int maxIndex = 0;
            for (int begin = 1; begin <= end; begin++) {
                if (cmp(maxIndex, begin) < 0) {
                    maxIndex = begin;
                }
            }
            if(cmp(maxIndex,end)==0){
                continue;
            }
            swap(maxIndex,end);
        }
    }
}
