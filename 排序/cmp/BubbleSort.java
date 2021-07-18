package com.mj.排序.cmp;

import com.mj.排序.Sort;

public class BubbleSort<E extends Comparable<E>> extends Sort<E> {
    @Override
    protected void sort() {
//        for (int i = 0; i < array.length - 1; i++) {
//
//            boolean flag = true;
//            for (int j = i; j < array.length; j++) {
//
//                if (cmp(i, j) > 0) {
//                    swap(i, j);
//                    flag = false;
//                }
//            }
//            if (flag) {
//                break;
//            }
//        }
        for(int end = array.length-1;end>0;end--){
            boolean flag = true;
            for(int begin = 0;begin<end;begin++){
                if(cmp(begin,begin+1)>0){
                    swap(begin,begin+1);
                    flag = false;
                }
            }
            if(flag)
            {
                return;
            }
        }
    }
}
