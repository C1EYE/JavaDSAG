package com.mj.线性表.队列;

public class CircleDeque<E> {
    private int front;
    private int size;
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public CircleDeque() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        return size == 0;
    }

    void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
        front = 0;
    }

    void enQueueFront(E element) {
        ensureCapacity(size + 1);
        front = index(-1);
        elements[front] = element;

        size++;
    }

    void enQueueRear(E element) {
        ensureCapacity(size + 1);
        elements[index(size)] = element;
        size++;
    }

    E deQueueFront() {
        E result = elements[front];
        elements[front] = null;
        front = index(1);
        size--;
        return result;
    }

    E deQueueRear() {
        int rear = index(size - 1);
        E result = elements[rear];
        elements[rear] = null;
        size--;
        return result;
    }

    E front() {
        return elements[front];
    }


    E rear() {
        return elements[index(size - 1)];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < elements.length; i++) {
            sb.append(" " + elements[i]);
        }
        return sb.toString();
    }

    //核心代码，索引转换
    private int index(int index) {
        index += front;
        return index < 0 ? index + elements.length : (index-((elements.length > index) ? 0 : elements.length));
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

    public static void main(String[] args) {
        CircleDeque<Integer> deque = new CircleDeque<>();
        for (int i=0;i<10;i++)
        {
            deque.enQueueFront(i+1);
            deque.enQueueRear(i+100);
        }
        System.out.println(deque);
        while (!deque.isEmpty()){
            System.out.println(deque.deQueueRear());
        }

    }

}
