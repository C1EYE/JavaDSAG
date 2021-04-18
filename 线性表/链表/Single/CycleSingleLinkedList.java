package com.mj.线性表.链表.Single;

import com.mj.线性表.工具.Asserts;
import com.mj.线性表.抽象类.MyAbstractList;

public class CycleSingleLinkedList<E> extends MyAbstractList<E> {
    private Node<E> head;

    @Override
    public boolean contains(E element) {
        return indexOf(element)!=-1;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
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
        if (index == 0) {
            Node<E> newHead = new Node<E>(element, head);
            Node<E> tail = (size == 0) ? newHead : node(size - 1);
            tail.next = newHead;
            head = newHead;
        } else {
            Node<E> node = node(index - 1);
            Node<E> newNode = new Node<E>(element, node.next);
            node.next = newNode;
        }
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> result;
        if (index == 0) {
            result = head;
            if (size == 1) {
                clear();
            } else {
                //注意这里的顺序
                Node<E> tail = node(size - 1);
                head = head.next;
                tail.next = head;
                size--;
            }

        } else {
            Node<E> node = node(index - 1);
            result = node.next;
            node.next = result.next;
            size--;
        }

        return result.element;
    }

    @Override
    public int indexOf(E element) {
        Node<E> p = head;
        for (int i=0;i<size;i++)
        {
            if (p.element==element){
                return i;
            }
            p = p.next;
        }
        return -1;
    }

    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private static final class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(element);
            sb.append("_");
            sb.append(next.element);
            return sb.toString();
        }

    }

    @Override
    public String toString() {
        Node<E> p = head;
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<size;i++)
        {
            sb.append(" ").append(p);
            p = p.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        CycleSingleLinkedList<Integer> integerCycleSingleLinkedList = new CycleSingleLinkedList<>();
        integerCycleSingleLinkedList.add(11);
        integerCycleSingleLinkedList.add(22);
        integerCycleSingleLinkedList.add(33);
        integerCycleSingleLinkedList.add(44);
        System.out.println(integerCycleSingleLinkedList);
        integerCycleSingleLinkedList.add(0, 55);
        integerCycleSingleLinkedList.add(2, 66);
        integerCycleSingleLinkedList.add(integerCycleSingleLinkedList.size, 77);
        System.out.println(integerCycleSingleLinkedList);
        integerCycleSingleLinkedList.remove(0);
        integerCycleSingleLinkedList.remove(2);
        integerCycleSingleLinkedList.remove(integerCycleSingleLinkedList.size - 1);
        System.out.println(integerCycleSingleLinkedList);

        Asserts.test(integerCycleSingleLinkedList.indexOf(44) == 3);
        Asserts.test(integerCycleSingleLinkedList.indexOf(22)==CycleSingleLinkedList.ELEMENT_NOTFOUND);
        Asserts.test(integerCycleSingleLinkedList.get(0) == 11);
        Asserts.test(integerCycleSingleLinkedList.get(1) == 66);
        Asserts.test(integerCycleSingleLinkedList.get(integerCycleSingleLinkedList.size - 1) == 44);

    }


}
