package com.mj.线性表.链表.TW;

import com.mj.树.printer.Printer;
import com.mj.线性表.抽象类.MyAbstractList;

public class TWCycleLinkedList<E> extends MyAbstractList<E> {
    Node<E> head;
    Node<E> tail;
    Node<E> current;

    public void reset() {
        current = head;
    }

    public E next() {
        if (current == null) return null;
        current = current.next;
        return current.element;
    }

    public E removeCurrent() {
        if (current == null) return null;
        E result = remove(current);
        if (size == 0) {
            current = null;
        } else {
            current = current.next;
        }
        return result;
    }

    private E remove(Node<E> node) {
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            Node<E> prev = node.prev;
            Node<E> next = node.next;
            prev.next = next;
            next.prev = prev;
            if (node == head) {
                head = next;
            }
            if (node == tail) {
                tail = prev;
            }

        }


        size--;
        return node.element;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        return remove(node(index));
    }


    @Override
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    @Override
    public void clear() {

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
        return null;
    }

    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index == size) {
            Node<E> oldTail = tail;
            tail = new Node<>(element, oldTail, head);
            if (oldTail == null) {
                head = tail;
                head.prev = head;
                head.next = head;
            } else {
                oldTail.next = tail;
                head.prev = tail;
            }
        } else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> newNode = new Node<>(element, prev, next);
            next.prev = newNode;
            prev.next = newNode;
            if (index == 0) {
                head = newNode;
            }
        }
        size++;
    }


    @Override
    public int indexOf(E element) {
        Node<E> temp = head;

        for (int i = 0; i < size; i++) {
            if (temp.element == element) {
                return i;
            }
            temp = temp.next;
        }

        return -1;
    }

    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> result;

        if (index < (size >> 1)) {
            result = head;
            for (int i = 0; i < index; i++) {
                result = result.next;
            }

        } else {
            result = tail;
            for (int i = size - 1; i > index; i--) {
                result = result.prev;
            }
        }
        return result;
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
        for (int i = 0; i < size; i++) {
            sb.append(" " + p);
            p = p.next;
        }
        return sb.toString();
    }

    static void josephus(int num, int flag) {
        TWCycleLinkedList<Integer> list = new TWCycleLinkedList<>();
        for (int i = 1; i <= num; i++) {
            list.add(i);
        }
        list.reset();
        while (list.size() != 1) {
            for (int i = 0; i < flag - 1; i++) {
                list.next();
            }
            System.out.println("砰！" + list.removeCurrent() + "号挂了");
        }
        System.out.println(list.removeCurrent() + "号活了下来...");

    }

    public static void main(String[] args) {
//        TWCycleLinkedList<Object> list = new TWCycleLinkedList<>();
//        list.add("a");
//        list.add("b");
//        list.add("c");
//        list.add("d");
//        list.add("e");
//        list.add("f");
//        list.add("g");
//        list.add("h");
//        list.add("i");
//        list.add("j");
//        list.add("k");
//        list.remove(list.size() - 1);
//        System.out.println(list);
        josephus(1000, 13);
    }

}
