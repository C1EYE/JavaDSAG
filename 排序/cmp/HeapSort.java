package com.mj.排序.cmp;

import com.mj.排序.Sort;

public class HeapSort<E extends Comparable<E>> extends Sort<E> {
    private int heapSize;

    @Override
    protected void sort() {
        heapSize = array.length;
        //原地建堆
        for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
            siftDown(i);
        }

        while (heapSize > 1) {
            //交换堆顶元素与尾部元素
            swap(0, --heapSize);
            //0位置SiftDown
            siftDown(0);
        }


    }

    private void siftDown(int index) {
        int half = heapSize >> 1;
        E element = array[index];
        while (index < half) {
            int childIndex = (index << 1) + 1;
            E child = array[childIndex];

            int rChildIndex = childIndex + 1;
            if (rChildIndex < heapSize &&
                    cmp(array[rChildIndex], child) > 0
            ) {
                child = array[childIndex = rChildIndex];
            }

            if (cmp(element, child) > 0) {
                break;
            }
            array[index] = child;
            index = childIndex;
        }
        array[index] = element;
    }
}
