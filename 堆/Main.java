package com.mj.堆;

import com.mj.树.printer.BinaryTrees;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

    }

    @Test
    public void test1() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(new Integer[]{1, 2, 3, 4, 5});
        heap.add(68);
        heap.add(72);
        heap.add(43);
        heap.add(50);
        heap.add(38);
        heap.add(68);
        heap.add(68);
        heap.add(68);
        heap.add(68);
        heap.add(68);

        BinaryTrees.print(heap);
        System.out.println();
        heap.remove();
        heap.remove();
        BinaryTrees.print(heap);
        System.out.println();
        System.out.println(heap.replace(100));
        BinaryTrees.print(heap);
    }

    @Test
    public void test2() {
        BinaryHeap<Integer> heap = new BinaryHeap<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, (a, b) -> b - a);
        BinaryTrees.print(heap);
    }

    @Test
    public void test3() {
        //TOP k
        BinaryHeap<Integer> heap = new BinaryHeap<>((a, b) -> a - b);
        Integer[] integer = new Integer[10000];
        for (int i = 0; i < 10000; i++) {
            integer[i] = i;
        }
        int k = 5;
        for (int i = 0; i < integer.length; i++) {
            if (heap.size < k) {
                heap.add(i);
            } else {
//                int intRan = random.nextInt(100000);
                if (heap.get() > integer[i]) {
                    heap.replace(integer[i]);
                }
            }

        }
        BinaryTrees.print(heap);
    }
}
