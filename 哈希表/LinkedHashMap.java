package com.mj.哈希表;

import java.util.Objects;

public class LinkedHashMap<K, V> extends HashMap<K, V> {
    LinkedNode<K, V> head;
    LinkedNode<K, V> tail;


    @Override
    protected void afterRemove(Node<K, V> willNode, Node<K, V> node) {
        LinkedNode<K, V> node1 = (LinkedNode<K, V>) willNode;
        LinkedNode<K, V> node2 = (LinkedNode<K, V>) node;
        if (willNode != node) {
            //交换链表中的位置
            LinkedNode<K, V> temp = node1.prev;
            node1.prev = node2.prev;
            node2.prev = temp;
            if (node1.prev == null) {
                head = node1;
            } else {
                node1.prev.next = node1;
            }
            if (node2.prev == null) {
                head = node2;
            } else {
                node2.prev.next = node2;
            }

            temp = node1.next;
            node1.next = node2.next;
            node2.next = temp;
            if (node1.next == null) {
                tail = node1;
            } else {
                node1.next.prev = node1;
            }
            if (node2.next == null) {
                tail = node2;
            } else {
                node2.next.prev = node2;
            }
        }

        if (node == null) return;
        LinkedNode<K, V> linkedNode = (LinkedNode<K, V>) node;
        LinkedNode<K, V> prev = linkedNode.prev;
        LinkedNode<K, V> next = linkedNode.next;
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
    }

    @Override
    public Node<K, V> createNode(K key, V value, Node<K, V> parent) {
        LinkedNode<K, V> node = new LinkedNode<>(key, value, parent);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        return node;
    }

    @Override
    public void clear() {
        super.clear();
        head = null;
        tail = null;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        LinkedNode<K, V> node = this.head;
        while (node != null) {
            if (visitor.visit(node.key, node.value)) {
                return;
            }
            node = node.next;
        }
    }

    @Override
    public boolean containsValue(V val) {
        LinkedNode<K, V> node = head;
        while (node != null) {
            if (Objects.equals(val, node.value)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    protected void resize() {
        if (size / table.length <= DEFAULT_LOAD_FACTORY) {
            return;
        }
        table = new Node[table.length << 1];
        LinkedNode<K, V> node = head;
        while (node != null) {
            moveNode(node);
            node = node.next;
        }

    }

    private static class LinkedNode<K, V> extends Node<K, V> {
        LinkedNode<K, V> prev;
        LinkedNode<K, V> next;


        public LinkedNode(K key, V value, Node<K, V> parent) {
            super(key, value, parent);

        }
    }

}
