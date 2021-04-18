package com.mj.线性表.链表.Single;

import com.mj.线性表.抽象类.MyAbstractList;


public class SingleLinkedList<E> extends MyAbstractList<E> {

    private Node<E> firstNode;


    @Override
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOTFOUND;
    }

    @Override
    public void clear() {
        size = 0;
        firstNode = null;
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
        Node<E> temp = node(index);
        E result = temp.element;
        temp.element = element;
        return result;
    }

    @Override
    public void add(int index, E element) {
        //注意这里的处理
        if (index == 0) {
            firstNode = new Node<E>(element, firstNode);
        } else {
            Node<E> prev = node(index - 1);
            prev.next = new Node<E>(element, prev.next);
        }
        size++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> result = firstNode;
        if (index == 0) {
            firstNode = firstNode.next;
        } else {
            Node<E> prev = node(index-1);
            result = prev.next;
            prev.next = prev.next.next;
        }
        size--;
        return result.element;
    }

    @Override
    public int indexOf(E element) {
        int index = 0;
        Node<E> temp = firstNode;
        if (element == null) {
            while (temp.element != element) {
                index++;
                temp = temp.next;
            }
        } else {
            while (!temp.element.equals(element)) {
                index++;
                temp = temp.next;
            }
        }
        return index;
    }

    //返回索引位置的节点
    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = firstNode;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size=").append(size).append(",[");
        Node<E> node = this.firstNode;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(node.element);
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.add(20);
        list.add(0, 10);
        list.add(30);
        list.add(list.size, 40);
        list.remove(1);
        //10,20,40
        System.out.println(list);
    }
}
