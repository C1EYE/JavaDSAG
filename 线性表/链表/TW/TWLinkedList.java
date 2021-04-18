package com.mj.线性表.链表.TW;

import com.mj.线性表.抽象类.MyAbstractList;

public class TWLinkedList<E> extends MyAbstractList<E> {
    private Node<E> head;
    private Node<E> tail;


    @Override
    public boolean contains(E element) {
        return false;
    }

    @Override
    public void clear() {
        size = 0;
        head = null;
        tail = null;
    }

    @Override
    public void add(E element) {
        add(size, element);
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index == size) {
            Node<E> oldTail = tail;
            tail = new Node<E>(element, tail, null);
            if (oldTail == null) {
                head = tail;
            } else {
                oldTail.next = tail;
            }
        } else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> newNode = new Node<>(element, prev, next);
            next.prev = newNode;
            if (prev == null) {
                head = newNode;
            } else {
                prev.next = newNode;
            }
        }
        size++;
    }

    //    @Override
//    public E remove(int index) {
//        rangeCheck(index);
//        Node<E> node;
//        if (index==size-1){
//            node = tail;
//            tail = tail.prev;
//            tail.next = null;
//        }else{
//            node = node(index);
//            if (node.prev==null){
//                head = node.next;
//                node.next.prev = null;
//            }else {
//                Node<E> prev = node.prev;
//                Node<E> next = node.next;
//                prev.next = next;
//                next.prev = prev;
//            }
//        }
//
//
//        return node.element;
//    }
    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> node = node(index);
        Node<E> prev = node.prev;
        Node<E> next = node.next;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
        }

        size--;
        return node.element;
    }

    @Override
    public int indexOf(E element) {
        return 0;
    }

    private Node<E> node(int index) {
        Node<E> node = null;
        if (index < (size >> 1)) {
            node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = tail;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    public static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("我没了...");
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (prev != null) {
                sb.append(prev.element);
            }
            sb.append("_").append(element).append("_");
            if (next != null) {
                sb.append(next.element);
            }

            return sb.toString();
        }
    }

    @Override
    public String toString() {
        Node<E> p = head;
        StringBuilder sb = new StringBuilder();
        sb.append("size: " + size);
        while (p != null) {
            sb.append(" " + p);
            p = p.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        TWLinkedList<String> stringTWLinkedList = new TWLinkedList<>();
        stringTWLinkedList.add("a");
        stringTWLinkedList.add("b");
        stringTWLinkedList.add("c");
        stringTWLinkedList.add("d");
        stringTWLinkedList.add("e");
        stringTWLinkedList.add("f");
        stringTWLinkedList.add("g");
        stringTWLinkedList.add("h");
        stringTWLinkedList.add("i");
        stringTWLinkedList.add("j");
        stringTWLinkedList.add("k");
        System.out.println(stringTWLinkedList);

    }

}
