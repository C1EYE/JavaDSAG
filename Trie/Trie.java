package com.mj.Trie;

import com.mj.哈希表.HashMap;

public class Trie<V> {
    private int size;
    Node<V> root = new Node<>(null);

    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }


    public void clear() {
        root.getChildren().clear();
    }


    public boolean contains(String key) {
        return node(key) != null;
    }


    public V add(String key, V value) {
        int len = key.length();
        Node<V> node = root;
        Node<V> child;
        for (int i = 0; i < len; i++) {
            child = node.getChildren().get(key.charAt(i));
            if (child == null) {
                child = new Node<V>(node);
                child.c = key.charAt(i);
                node.getChildren().put(key.charAt(i), child);
            }
            node = child;
        }

        if (node.word) {
            V oldVal = node.value;
            node.value = value;
            return oldVal;
        }

        node.word = true;
        node.value = value;
        size++;
        return null;
    }


    public V remove(String key) {

        //找到对应字符串节点
        Node<V> node = node(key);

        if (node == null || !node.word) {
            return null;
        }
        size--;
        //如果包含于其他单词则只需删除word标志
        V oldVal = node.value;
        if (node.children != null && !node.children.isEmpty()) {
            node.word = false;
            node.value = null;
            return oldVal;
        }
        //没有子节点
        Node<V> parent = null;
        while ((parent = node.parent) != null) {
            parent.getChildren().remove(node.c);
            if (parent.word || parent.getChildren().size() > 0) {
                break;
            }
            node = parent;
        }

        return oldVal;
    }


    public boolean startsWith(String prefix) {
        keyCheck(prefix);
        int len = prefix.length();
        Node<V> node = root;
        for (int i = 0; i < len; i++) {
            node = node.children.get(prefix.charAt(i));
            if (node == null) {
                return false;
            }
        }
        return true;

    }

    public V get(String key) {
        Node<V> result = node(key);
        return result == null ? null : result.value;
    }

    private Node<V> node(String key) {
        keyCheck(key);
        int length = key.length();

        Node<V> node = root;
        for (int i = 0; i < length; i++) {
            char c = key.charAt(i);
            node = node.getChildren().get(c);
            if (node == null) {
                return null;
            }
        }

        return node.word ? node : null;
    }

    private void keyCheck(String key) {
        if (key == null || key.length() == 0) {
            throw new IllegalArgumentException("key must not be empty");
        }

    }

    private static class Node<V> {
        public Node(Node<V> parent) {
            this.parent = parent;
        }

        HashMap<Character, Node<V>> children;
        Node<V> parent;
        V value;
        boolean word = false;
        Character c;

        public HashMap<Character, Node<V>> getChildren() {
            return children == null ? (children = new HashMap<>()) : children;
        }
    }

}
