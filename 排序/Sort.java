package com.mj.排序;


import java.text.DecimalFormat;

/**
 * @author c1eye
 */
public abstract class Sort<E extends Comparable<E>> implements Comparable<Sort<E>> {
    protected E[] array;
    private int cmpCount = 0;
    private static final int MINCAPACITY = 2;

    private int swapCount = 0;
    private long time;
    private DecimalFormat fmt = new DecimalFormat("#.00");

    protected abstract void sort();

    public void sort(E[] array) {
        if (array == null || array.length < MINCAPACITY) {
            return;
        }
        this.array = array;
        time = System.currentTimeMillis();
        sort();
        time = System.currentTimeMillis() - time;
    }


    /**
     * 传入两个索引
     * 相等返回0
     * a>b返回正数
     * a<b返回负数
     *
     * @param a
     * @param b
     * @return
     */
    protected int cmp(int a, int b) {
        cmpCount++;
        return array[a].compareTo(array[b]);
    }

    protected int cmp(E a, E b) {
        cmpCount++;
        return a.compareTo(b);
    }

    protected void swap(int a, int b) {
        swapCount++;
        E temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    private String numberString(int number) {
        if (number < 10000) {
            return "" + number;
        }

        if (number < 100000000) {
            return fmt.format(number / 10000.0) + "万";
        }
        return fmt.format(number / 100000000.0) + "亿";
    }

    @Override
    public String toString() {
        String timeStr = "耗时：" + (time / 1000.0) + "s(" + time + "ms)";
        String compareCountStr = "比较：" + numberString(cmpCount);
        String swapCountStr = "交换：" + numberString(swapCount);
        String stableStr = "稳定性：" + isStable();
        return "【" + getClass().getSimpleName() + "】\n"
                + stableStr + " \t"
                + timeStr + " \t"
                + compareCountStr + "\t "
                + swapCountStr + "\n"
                + "------------------------------------------------------------------";

    }

    @Override
    public int compareTo(Sort o) {
        int result = (int) (this.time - o.time);
        if (result != 0) {
            return result;
        }
        result = this.cmpCount - o.cmpCount;
        if (result != 0) {
            return result;
        }
        return this.swapCount - o.swapCount;

    }

    private boolean isStable() {
//        if (this instanceof RadixSort) return true;
//        if (this instanceof CountingSort) return true;
//        if (this instanceof ShellSort) return false;
//        if (this instanceof SelectionSort) return false;
        Student[] students = new Student[20];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student(i * 10, 10);
        }
        sort((E[]) students);
        for (int i = 1; i < students.length; i++) {
            int score = students[i].score;
            int prevScore = students[i - 1].score;
            if (score != prevScore + 10) {
                return false;
            }
        }
        return true;
    }
}
