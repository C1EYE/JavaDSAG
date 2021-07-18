package com.mj.排序.cmp;

import com.mj.排序.Sort;

public class QuickSort<E extends Comparable<E>> extends Sort<E> {

    @Override
    protected void sort() {
        sort(0, array.length);
    }

    /**
     * 对[begin,end)范围进行快排
     *
     * @param begin
     * @param end
     */
//    private void sort(int begin, int end) {
//        if (end - begin < 2) {
//            return;
//        }
//        //确定轴点位置
//        int mid = pivotIndex(begin, end);
//        //对子序列排序
//        sort(begin, mid);
//        //注意这里
//        sort(mid + 1, end);
//    }
//
//    private int pivotIndex(int begin, int end) {
//        //随机取轴点
//        swap(begin, begin + (int) Math.random() * (end - begin));
//        E pivot = array[begin];
//        --end;
//        while (begin < end) {
//
//            while (begin < end) {
//                //右元素大于轴点元素
//                if (cmp(pivot, array[end]) < 0) {
//                    end--;
//                } else {
//                    array[begin++] = array[end];
//                    break;
//                }
//            }
//            while (begin < end) {
//                //左元素小于轴点元素
//                if (cmp(pivot, array[begin]) > 0) {
//                    begin++;
//                } else {
//                    array[end--] = array[begin];
//                    break;
//                }
//            }
//        }
//        array[begin] = pivot;
//        return begin;
//
//
//
//
//    }
    private void sort(int begin, int end) {
        if (end - begin < 2) { return; }
        int pivot = pivotIndex(begin, end);
        sort(begin, pivot);
        sort(pivot + 1, end);
    }

    private int pivotIndex(int begin, int end) {
        E element = array[begin];
        --end;
        while (begin < end) {
            while (begin < end) {
                if (cmp(array[end], element) > 0) {
                    end--;
                } else {
                    array[begin++] = array[end];
                    break;
                }
            }

            while (begin < end) {
                if (cmp(array[begin], element) < 0) {
                    begin++;
                } else {
                    array[end--] = array[begin];
                    break;
                }
            }

        }
        array[begin] = element;
        return begin;
    }


}
