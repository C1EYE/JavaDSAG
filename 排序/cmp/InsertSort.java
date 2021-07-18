package com.mj.排序.cmp;

import com.mj.排序.Sort;

/**
 * @author c1eye
 */
public class InsertSort<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            int cur = begin;
            E e = array[cur];
            while (cur > 0 && cmp(e, array[cur-1]) < 0) {
                array[cur] = array[cur - 1];
                cur--;
            }
            array[cur] = e;
        }


    }
}
