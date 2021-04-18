package com.mj.线性表.接口;

public interface MyQueue<E> {
    int size();

    boolean isEmpty();

    void enQueue(E element);

    E deQueue();

    E front();

    void clear();
}
