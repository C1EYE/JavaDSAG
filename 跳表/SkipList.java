package com.mj.跳表;

import java.util.Comparator;

public class SkipList<K, V> {
    private static final int MAX_LEVEL = 32;
    private static final double P = 0.25;
    private int size;
    private Comparator<K> comparator;
    /**
     * 头结点，不存放值
     */
    private Node<K, V> head;
    /**
     * 有效层数
     */
    private int level;

    public SkipList() {
        this(null);
    }

    public SkipList(Comparator<K> comparator) {
        this.comparator = comparator;
        head = new Node<>();
        head.nexts = new Node[MAX_LEVEL];
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int randomLevel() {
        int level = 1;
        while (Math.random() < P && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    public V put(K key, V value) {
        KeyCheck(key);
        Node<K, V> node = this.head;
        Node<K, V>[] prev = new Node[level];
        for (int lev = level - 1; lev >= 0; lev--) {
            int cmp = -1;
            while (node.nexts[lev] != null && (cmp = cmp(key, node.nexts[lev].key)) > 0) {
                node = node.nexts[lev];
            }
            //节点存在
            if (cmp == 0) {
                V oldV = node.nexts[lev].value;
                node.nexts[lev].value = value;
                return oldV;
            }
            prev[lev] = node;
        }
        Node<K, V> newNode = new Node<K, V>(key, value);

        //随机高度
        int newLevel = randomLevel();
        for (int i = 0; i < newLevel; i++) {
            if (i >= level) {
                //超出当前高度
                head.nexts[i] = newNode;
            } else {
                newNode.nexts[i] = prev[i].nexts[i];
                prev[i].nexts[i] = newNode;
            }

        }
        size++;
        level = Math.max(newLevel, level);
        return value;
    }

    public V get(K key) {
        KeyCheck(key);
        Node<K, V> node = this.head;
        for (int lev = level - 1; lev >= 0; lev--) {
            int cmp = -1;
            while (node.nexts[lev] != null && (cmp = cmp(key, node.nexts[lev].key)) > 0) {
                node = node.nexts[lev];
            }
            if (cmp == 0) { return node.nexts[lev].value; }
        }
        return null;
    }

    public V remove(K key) {
        KeyCheck(key);
        Node<K, V> node = this.head;
        Node<K, V>[] prev = new Node[level];
        boolean exist = false;
        for (int lev = level - 1; lev >= 0; lev--) {
            int cmp = -1;
            while (node.nexts[lev] != null && (cmp = cmp(key, node.nexts[lev].key)) > 0) {
                node = node.nexts[lev];
            }
            if (cmp == 0) {
                exist = true;
            }
            prev[lev] = node;
        }
        if (!exist) { return null; }
        size--;
        Node<K, V> removeNode = node.nexts[0];
        for (int i = 0; i < prev.length; i++) {
            prev[i].nexts[i] = removeNode.nexts[i];
        }
//        更新层数
        int newLevel = level;
        while (--newLevel >= 0 && head.nexts[newLevel] == null) {
            level = newLevel;
        }
        return removeNode.value;
    }

    private void KeyCheck(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key can't be null");
        }
    }

    private int cmp(K k1, K k2) {
        return comparator != null ?
                comparator.compare(k1, k2)
                : ((Comparable<K>) k1).compareTo(k2);
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V>[] nexts;

        public Node() {
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.nexts = new Node[MAX_LEVEL];
        }

        @Override
        public String toString() {
            return key.toString() + ":" + value.toString();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("一共" + level + "层").append("\n");
        for (int i = level - 1; i >= 0; i--) {
            Node<K, V> node = head;
            while (node.nexts[i] != null) {
                sb.append(node.nexts[i]);
                sb.append(" ");
                node = node.nexts[i];
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
