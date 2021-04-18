package com.mj.线性表.栈;

import com.mj.线性表.抽象类.MyAbstractStack;

import java.util.ArrayList;
import java.util.List;

public class Stack<E> extends MyAbstractStack<E> {
    private List<E> elements = new ArrayList<E>();

    @Override
    public void push(E element) {
        elements.add(element);
        size++;
    }

    @Override
    public E pop() {
        return elements.remove(size-- - 1);
    }

    @Override
    public E top() {
        return elements.get(size - 1);
    }

    public static void main(String[] args) {
        Stack<Integer> integerStack = new Stack<Integer>();
        integerStack.push(1);
        integerStack.push(2);
        integerStack.push(3);
        integerStack.push(4);

        while (!integerStack.isEmpty()) {
            System.out.println(integerStack.pop());
        }

    }
}
