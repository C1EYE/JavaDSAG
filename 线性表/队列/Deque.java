package com.mj.线性表.队列;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Deque<E> {
    private List<E> list = new LinkedList<>();

    int size() {
        return list.size();
    }

    boolean isEmpty() {
        return list.isEmpty();
    }

    void clear() {
        list.clear();
    }

    void enQueueFront(E element) {
        list.add(0, element);
    }

    void enQueueRear(E element) {
        list.add(list.size(), element);
    }

    E deQueueFront() {
        return list.remove(0);
    }

    E deQueueRear() {
        return list.remove(list.size() - 1);
    }

    E front() {
        return list.get(0);
    }

    E rear() {
        return list.get(list.size() - 1);
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.enQueueFront(11);
        deque.enQueueFront(22);
        deque.enQueueRear(33);
        deque.enQueueRear(44);

        while (!deque.isEmpty()){
            System.out.println(deque.deQueueFront());
        }


    }

}
