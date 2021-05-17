package com.mj.排序.cmp;

import com.mj.排序.Sort;

/**
 * 使用二分搜索优化过的插入排序
 *
 * @param <E>
 */
public class InsertSort3<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            insert(begin, search(begin));
        }
    }

    /**
     * 插入元素
     * @param begin 源位置
     * @param dest 目标位置
     */
    private void insert(int begin, int dest) {
        int cur = begin;
        E e = array[cur];
        while (cur != dest) {
            array[cur] = array[cur - 1];
            cur--;
        }
        array[dest] = e;
    }

    /**
     * 查找e在有序数组中插入的位置
     * @return
     */
    private int search(int index) {
        E e = array[index];
        //左闭右开
        int begin = 0;
        int end = index;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (cmp(array[mid], e) > 0) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return begin;
    }
}
