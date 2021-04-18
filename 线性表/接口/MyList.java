package com.mj.线性表.接口;

public interface MyList<E> {
    int ELEMENT_NOTFOUND = -1;

    int size();

    boolean isEmpty();

    boolean contains(E element);

    void clear();

    void add(E element);

    E get(int index);

    E set(int index,E element);

    void add(int index, E element);

    E remove(int index);

    int indexOf(E element);

}
