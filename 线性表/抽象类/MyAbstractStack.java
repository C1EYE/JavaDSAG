package com.mj.线性表.抽象类;

import com.mj.线性表.接口.MyStack;

public abstract class MyAbstractStack<E> implements MyStack<E> {
    protected int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }
}
