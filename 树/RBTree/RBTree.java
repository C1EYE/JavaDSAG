package com.mj.树.RBTree;


import java.util.Comparator;

public class RBTree<E> extends BBST<E> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public RBTree() {
        this(null);
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    private Node<E> color(Node<E> node, boolean color) {
        if (node == null) return node;
        ((RBNode<E>) node).color = color;
        return node;
    }

    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    private boolean colorOf(Node<E> node) {
        return node == null ? BLACK : ((RBNode<E>) node).color;
    }

    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }


    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;
        if (parent == null) {
            black(node);
            return;
        }
        //父节点为黑
        if (isBlack(parent)) {
            return;
        }

        //父节点为红,上溢
        Node<E> uncle = parent.sibling();
        Node<E> grand = red(parent.parent);
        if (isRed(uncle)) {
            black(parent);
            black(uncle);
            //祖父向上合并
            afterAdd(grand);
            return;
        }

        //不上溢，父节点为红，旋转恢复性质
        if (parent.isLeftChild()) {
            if (node.isLeftChild()) {
                black(parent);
            } else {
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else {
            if (node.isLeftChild()) {
                black(node);
                rotateRight(parent);
            } else {
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    @Override
    protected void afterRemove(Node<E> node) {
        //红色不需要处理
//        if (isRed(node)) {
//            return;
//        }
        //有红色子节点，可以直接替换
        if (isRed(node)) {
            black(node);
            return;
        }

        Node<E> parent = node.parent;
        //根节点
        if (parent == null) {
            return;
        }

        //删除的是黑色叶子节点
        boolean left = (parent.left == null)||node.isLeftChild();
        Node<E> sibling = left ? parent.right : parent.left;
        if (left) {
            //被删除的节点在左面，兄弟节点在右面

            //将红色兄弟节点的情况转换为黑色兄弟情况
            if (isRed(sibling)) {
                black(sibling);
                red(parent);
                rotateLeft(parent);
                sibling = parent.right;
            }
            //兄弟节点为黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                //而且子节点都为黑，不能借
                //父节点向下合并
                boolean parentBlack = isBlack(parent);
                //这两步是通用的
                black(parent);
                red(sibling);
                if (parentBlack) {
                    //父节点是黑色
                    afterRemove(parent);
                }

            } else {
                //可以借
                if (isBlack(sibling.right)) {
                    //兄弟节点左边为黑色（NULL），需要先进行LL
                    rotateRight(sibling);
                    //更新兄弟
                    sibling = parent.right;
                }
                //之后操作统一,先染色，后旋转
                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }
        } else {
            //被删除的节点在右面，兄弟节点在左面

            //将红色兄弟节点的情况转换为黑色兄弟情况
            if (isRed(sibling)) {
                black(sibling);
                red(parent);
                rotateRight(parent);
                sibling = parent.left;
            }
            //兄弟节点为黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                //而且子节点都为黑，不能借
                //父节点向下合并
                boolean parentBlack = isBlack(parent);
                //这两步是通用的
                black(parent);
                red(sibling);
                if (parentBlack) {
                    //父节点是黑色
                    afterRemove(parent);
                }

            } else {
                //可以借
                if (isBlack(sibling.left)) {
                    //兄弟节点左边为黑色（NULL），需要先进行LL
                    rotateLeft(sibling);
                    //更新兄弟
                    sibling = parent.left;
                }
                //之后操作统一,先染色，后旋转
                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }


    }


    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
    }

    private static class RBNode<E> extends Node<E> {
        boolean color = RED;

        public RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            String str = "";
            if (color == RED) {
                str = "R_";
            }
            return str + element;
        }
    }

}
