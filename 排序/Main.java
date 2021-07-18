package com.mj.排序;


import com.mj.tools.ArrayUtil;
import com.mj.tools.Assert;
import com.mj.排序.cmp.*;

import java.util.Arrays;

@SuppressWarnings({"rawtypes", "unchecked"})
public class Main
{
    public static void main(String[] args)
    {
        Integer[] array = ArrayUtil.random(10000, 1, 100);

        testSorts(array,
                    new BubbleSort(), new SelectionSort(), new InsertSort(), new InsertSort3(),
                    new HeapSort(), new MergeSort(), new QuickSort(),
                    new ShellSort(),
                    new CountingSort(),
                    new CountingSort2(),
                    new RadixSort()
                 );

    }


    static void testSorts(Integer[] array, Sort... sorts)
    {
        for (Sort sort : sorts)
        {
            Integer[] copy = ArrayUtil.copy(array);
            sort.sort(copy);
            Assert.test(ArrayUtil.isAscOrder(copy));
        }
        Arrays.sort(sorts);
        Arrays.stream(sorts).forEach(System.out::println);
    }


}
