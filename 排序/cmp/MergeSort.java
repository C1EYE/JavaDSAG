package com.mj.排序.cmp;

import com.mj.排序.Sort;

@SuppressWarnings({"unchecked"})
public class MergeSort<E extends Comparable<E>> extends Sort<E> {

    private E[] leftArray;

    @Override
    protected void sort() {
        leftArray = (E[]) new Comparable[array.length >> 1];
        sort(0, array.length);
    }

    /**
     * 对[begin,end)范围内的数据进行归并排序
     * end-begin就是范围内的元素个数
     *
     * @param begin
     * @param end
     */
    private void sort(int begin, int end) {
        if (end - begin < 2) {
            return;
        }
        int mid = (begin + end) >> 1;
        sort(begin, mid);
        sort(mid, end);
        merge(begin, mid, end);
    }

    /**
     * 将 [begin,mid) 和 [id,end)范围的元素合并为一个有序序列
     */
    private void merge(int begin, int mid, int end) {
        //左数组的起始
        int li = 0, le = mid - begin;
        //右数组的起始
        int ri = mid, re = end;
        //覆盖的位置
        int ai = begin;
        //备份左数组
        for (int i = li; i < le; i++) {
            leftArray[i] = array[begin + i];
        }

        //左侧数组排序完就可以结束了
        while (li < le) {
            if (ri < re && cmp(array[ri], leftArray[li]) < 0) {
                array[ai++] = array[ri++];
            }else{
                array[ai++] = leftArray[li++];
            }
        }


    }
}
