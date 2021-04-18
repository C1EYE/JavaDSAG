package com.mj.树.BST;


import com.mj.树.printer.BinaryTreeInfo;
import com.mj.树.printer.BinaryTrees;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class MyTree<E> implements BinaryTreeInfo {
    private int size;
    private Node<E> root;
    private Comparator<E> comparator;

    public MyTree() {
        this(null);
    }

    public MyTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    int size() {
        return size;
    }


    boolean isEmpty() {
        return size == 0;
    }


    void clear() {
        root = null;
        size = 0;
    }


    void add(E element) {
        elementNotNullCheck(element);
        if (root == null) {
            root = new Node<E>(element, null);
            size++;
            return;
        }
        Node<E> temp = root;
        Node<E> parentNode = root;
        int compare = 0;
        while (temp != null) {
            compare = compare(temp.element, element);
            parentNode = temp;
            if (compare > 0) {
                temp = temp.left;
            } else if (compare < 0) {
                temp = temp.right;
            } else {
                temp.element = element;
                return;
            }
        }

        Node<E> newNode = new Node<>(element, parentNode);
        if (compare > 0) {
            parentNode.left = newNode;
        } else {
            parentNode.right = newNode;
        }

        size++;
    }

    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable<E>) e1).compareTo(e2);
    }


    void remove(E element) {
        remove(node(element));
    }

    boolean contains(E element) {
        return node(element) != null;
    }

    private void remove(Node<E> node) {
        if (node == null) return;
        size--;
        if (node.hasTwoChildren()) {
            Node<E> successor = successor(node);
            node.element = successor.element;
            node = successor;
        }

        //1
        Node<E> replacement = node.left != null ? node.left : node.right;
        if (replacement != null) {
            replacement.parent = node.parent;

            if (node.parent == null) {
                root = replacement;
            } else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }
        } else if (node.parent == null) {
            root = null;
        } else {
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }


    }

    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
            if (cmp == 0) return node;
            if (cmp > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    private void preorder(Visitor<E> visitor) {
        if (visitor == null) return;
        preorder(root, visitor);
    }

    private void preorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        preorder(node.left, visitor);
        preorder(node.right, visitor);
    }

    private void inorder(Visitor<E> visitor) {
        if (visitor == null) return;
        inorder(root, visitor);
    }

    private void inorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        inorder(node.left, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        inorder(node.right, visitor);
    }

    private void postorder(Visitor<E> visitor) {
        if (visitor == null)
            return;
        postorder(root, visitor);
    }

    private void postorder(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        postorder(node.left, visitor);
        postorder(node.right, visitor);

        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
    }

    private void level_order(Visitor<E> visitor) {
        if (root == null) return;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            if (visitor.visit(node.element)) return;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }


    private void elementNotNullCheck(E element) {
        if (element == null) throw new IllegalArgumentException("element must not be nul");
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node<E>) node).element;
    }

    /**
     * @param <E>
     */
    public static abstract class Visitor<E> {
        protected boolean stop;

        abstract boolean visit(E element);
    }

    private static class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(root, sb, "");
        return sb.toString();
    }

    private void toString(Node<E> node, StringBuilder sb, String prefix) {
        if (node == null) return;
        sb.append(prefix).append(node.element).append("\n");
        toString(node.left, sb, prefix + "L---");
        toString(node.right, sb, prefix + "R---");
    }

    public int height1() {
        return height(root);

    }

    private int height(Node<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public int height2() {
        int height = 0;

        int levSize = 1;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            levSize--;

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }

            if (levSize == 0) {
                levSize = queue.size();
                height++;
            }

        }
        return height;
    }

    public boolean isComplete() {
        if (root == null) return false;
        boolean leaf = false;
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
//        while (!queue.isEmpty()) {
//            Node<E> node = queue.poll();
//            if (leaf && !node.isLeaf()) {
//                return false;
//            }
//            if (node.hasTwoChildren()) {
//                queue.offer(node.left);
//                queue.offer(node.right);
//
//            } else if (node.left == null && node.right != null) {
//                return false;
//            } else {
//                leaf = true;
//                if (node.left != null) {
//                    queue.offer(node.left);
//
//                }
//            }
//        }
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();

            if (true && !node.isLeaf()) {
                return false;
            }

            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) {
                return false;
            }

            if (node.right != null) {
                queue.offer(node.right);
            } else {
                leaf = true;
            }

        }
        return true;
    }

    private Node<E> predecessor(Node<E> node) {
        if (node == null) return null;

        if (node.left != null) {
            node = node.left;
            while (node.right != null) {
                node = node.right;
            }
            return node.right;
        }

        while (node.parent != null && node.parent.left == node) {
            node = node.parent;
        }
        return node.parent;
    }

    private Node<E> successor(Node<E> node) {
        if (node == null) return null;
        if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        while (node.parent != null && node.parent.right == node) {
            node = node.parent;
        }
        return node.parent;
    }


    public static void main(String[] args) {
        MyTree<Integer> BST = new MyTree<>();
        Integer[] data = new Integer[]{7, 4, 9, 2, 5, 8, 11};
        for (int i = 0; i < data.length; i++) {
            BST.add(data[i]);
        }
        BinaryTrees.println(BST);
//        BST.remove(5);
        BST.remove(7);
        BinaryTrees.println(BST);


    }
}
