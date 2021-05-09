package com.mj.堆;

public interface Heap<E> {
    int size();

    boolean isEmpty();

    void clear();

    void add(E element);

    //获得堆顶元素
    E get();

    E remove();

    //将堆顶元素替换
    E replace(E e);


}
