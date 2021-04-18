package com.mj.线性表.队列;

import com.mj.线性表.抽象类.MyAbstractQueue;

import java.util.LinkedList;
import java.util.List;

public class MyQueue<E> extends MyAbstractQueue<E> {
    private List<E> elements = new LinkedList<>();

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public void enQueue(E element) {
        elements.add(element);
    }

    @Override
    public E deQueue() {
        return elements.remove(0);
    }

    @Override
    public E front() {
        return elements.get(0);
    }

    @Override
    public void clear() {
        elements.clear();
    }

    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.enQueue(11);
        queue.enQueue(22);
        queue.enQueue(33);
        queue.enQueue(44);

        while (!queue.isEmpty())
        {
            System.out.println(queue.deQueue());
        }
    }
}
