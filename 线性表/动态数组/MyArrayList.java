package com.mj.线性表.动态数组;

public class MyArrayList<E> {
    private int size;
    private E[] elements;

    private static final int DEFAULT_CAPACITY = 10;
    private static final int ELEMENT_NOTFOUND = -1;

    public MyArrayList(int capacity) {
        capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
        elements = (E[]) new Object[capacity];
    }

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object element) {
        return indexOf(element) != ELEMENT_NOTFOUND;
    }

    public void add(E element) {
        add(size, element);
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index:" + index + ",Size" + size);
        }
        return (E) elements[index];
    }

    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index:" + index + ",Size" + size);
        }
        E e = get(index);
        elements[index] = (E) element;
        return e;
    }

    public void add(int index, E element) {
        if (size == elements.length) {
            Object[] newElement = new Object[size + (size >> 1)];
            for (int i = 0; i < size; i++) {
                newElement[i] = elements[i];
            }
            elements = (E[]) newElement;
        }
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = (E) element;
        size++;
        return;
    }

    public E remove(int index) {
        Object temp = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[index] = elements[index + 1];
        }
        size--;
        trim();

        return (E) temp;
    }

    private void trim() {
        int capacity = elements.length;
        if (size >= (capacity >> 1) || capacity <= DEFAULT_CAPACITY) return;
        int newCapacity = (capacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;


    }

    public void remove(E element) {
        remove(indexOf(element));
    }

    public int indexOf(Object element) {
        if (element == null) return ELEMENT_NOTFOUND;
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element))
                return i;
        }
        return ELEMENT_NOTFOUND;
    }

    void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        elements[--size] = null;
        size = 0;
        if (elements != null && elements.length > DEFAULT_CAPACITY)
            elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("size:" + size);
        for (int i = 0; i < size; i++) {
            stringBuilder.append(" " + (E) elements[i]);
        }
        return stringBuilder.toString();
    }
}
