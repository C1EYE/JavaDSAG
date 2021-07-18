package com.mj.排序.cmp;

import com.mj.排序.Sort;

import java.util.ArrayList;
import java.util.List;

public class ShellSort<E extends Comparable<E>> extends Sort<E> {
    private List<Integer> stepSequence;

    @Override
    protected void sort() {
        stepSequence = shellStepSequence();
        for (Integer step : stepSequence) {
            sort(step);
        }

    }

    /**
     * 步长序列计算
     *
     * @return
     */
    private List<Integer> shellStepSequence() {
        List<Integer> stepSequence = new ArrayList<>();
        int step = array.length;
        while ((step >>= 1) > 0) {
            stepSequence.add(step);
        }
        return stepSequence;
    }

    /**
     * 分成step列进行排序
     *
     * @param step
     */
    private void sort(int step) {
        for (int col = 0; col < step; col++) {
            for (int begin = col + step; begin < array.length; begin += step) {
                int cur = begin;
                while (cur > col && cmp(cur, cur - step) < 0) {
                    swap(cur, cur - step);
                    cur-=step;
                }
            }
        }

    }
}
