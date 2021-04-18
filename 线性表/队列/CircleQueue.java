package com.mj.线性表.队列;

public class CircleQueue<E> {
    private int front;
    private int size;
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public int size() {
        return size;
    }

    public CircleQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enQueue(E element) {
        ensureCapacity(size);
        //**
        elements[(front + size) % elements.length] = element;
        size++;
    }

    public E deQueue() {
        E result = elements[front];
        elements[front] = null;
        front = ++front % elements.length;
        size--;
        return result;

    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity > capacity) return;

        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[(front + i) % elements.length];
        }
        elements = newElements;
        front = 0;
    }

    public void clear() {

    }

    public E front() {
        return elements[front];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < elements.length; i++) {
            sb.append(" " + elements[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        CircleQueue<Integer> queue = new CircleQueue<>();
        for (int i = 0; i < 15; i++) {
            queue.enQueue(i);
        }
        System.out.println(queue);
        for (int i = 0; i < 5; i++) {
            queue.deQueue();
        }
        System.out.println(queue);
        while (!queue.isEmpty()) {
            System.out.println(queue.deQueue());
        }
    }

}
