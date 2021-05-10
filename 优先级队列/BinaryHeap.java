package com.mj.优先级队列;

import com.mj.树.printer.BinaryTreeInfo;

import java.util.Comparator;

/**
 * 大顶堆
 *
 * @param <E>
 */
public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public BinaryHeap() {
        this(null,null);
    }

    public BinaryHeap(E[] elements,Comparator<E> comparator){
        super(comparator);
        if(elements==null||elements.length==0){
            this.elements = (E[]) new Object[DEFAULT_CAPACITY];
        }else{
            //批量建堆
            size = elements.length;
            int capacity = Math.max(elements.length, DEFAULT_CAPACITY);
            this.elements = (E[]) new Object[capacity];
            for (int i = 0; i < size; i++) {
                this.elements[i] = elements[i];
            }
            heapIfy();
        }

    }

    private void heapIfy(){
        //自顶向下的上滤
        for (int i = 0; i < size; i++) {
            siftUp(i);
        }
        //自底向上的上滤
        for (int i = (size>>1)-1; i >=0; i--) {
            siftDown(i);
        }
    }

    public BinaryHeap(E[] elements){
        this(elements, null);
    }

    public BinaryHeap(Comparator<E> comparator){
        this(null, comparator);
    }
    @Override
    public void clear() {
        size = 0;
        for (int i = 0; i < elements.length; i++) {
            elements[i] = null;
        }
    }

    @Override
    public void add(E element) {
        ensureCapacity(size + 1);
        elements[size] = element;
        siftUp(size++);
    }

    /**
     * 上滤
     *
     * @param index 从index开始
     */
    private void siftUp(int index) {
        int pIndex;
        E p, e;
        e = elements[index];
        while (index > 0) {
            pIndex = (index - 1) >> 1;
            p = elements[pIndex];
            if (compare(e, p) <= 0) {
                break;
            }
            //exchange
            elements[index] = elements[pIndex];
            index = pIndex;
        }
        elements[index] = e;
    }

    @Override
    public E get() {
        emptyCheck();
        return elements[0];
    }

    @Override
    public E remove() {
        emptyCheck();
        E result = elements[0];
        elements[0] = elements[size - 1];

        elements[size] = null;
        size--;
        siftDown(0);
        return result;
    }

    private void siftDown(int index) {
        E e = elements[index];
        int half = size >> 1;
        while (index < half) {
            //两个子节点或者只有左子节点
            int childIndex = (index << 1) + 1;
            E child = elements[childIndex];
            int rChildIndex = childIndex + 1;
            if(rChildIndex<size && compare(elements[rChildIndex],child)>0){
                childIndex = rChildIndex;
                child = elements[childIndex];
            }
            if (compare(e, child) >= 0) {
                break;
            }
            elements[index] = child;
            index = childIndex;
        }
        elements[index] = e;
    }

    @Override
    public E replace(E e) {
        elementNotNullCheck(e);
        E result = null;
        if(size==0){
            elements[0] = e;
            size++;
        }else{
            result = elements[0];
            elements[0] = e;
            siftDown(0);
        }
        return result;
    }

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity > capacity) {
            return;
        }
        //*1.5
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    private void emptyCheck() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Heap is Empty");
        }
    }

    private void elementNotNullCheck(E e) {
        if (e == null) {
            throw new IllegalArgumentException("Element must not be Null");
        }
    }

    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object node) {
        int length = (((Integer) node) << 1) + 1;
        return length >= size ? null : length;
    }

    @Override
    public Object right(Object node) {
        int length = (((Integer) node) << 1) + 2;
        return length >= size ? null : length;
    }

    @Override
    public Object string(Object node) {
        return elements[(Integer) node];
    }
}
