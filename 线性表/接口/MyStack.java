package com.mj.线性表.接口;

public interface MyStack<E> {
    int size();

    boolean isEmpty();

    void push(E element);

    E pop();

    E top();
}
