package com.mj.排序;

public class BinarySearch {
    public static int indexOf(int[] array, int e) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int begin = 0;
        int end = array.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (e < array[mid]) {
                end = mid;
            } else if (e > array[mid]) {
                begin = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 查找e在有序数组中插入的位置
     *
     * @return
     */
    public static int search(int[] array, int e) {
        if (array == null || array.length == 0) {
            return -1;
        }

        //左闭右开
        int begin = 0;
        int end = array.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (array[mid] > e) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        return begin;
    }
}
