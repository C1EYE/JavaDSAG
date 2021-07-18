package com.mj.集合;

import java.util.LinkedList;
import java.util.List;

public class ListSet<E> implements Set<E> {
    private List<E> list = new LinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean contains(E element) {
        return list.contains(element);
    }

    @Override
    public void add(E element) {
        if (element==null)return;
        if (!list.contains(element)) {
            list.add(element);
        }
    }

    @Override
    public void remove(E element) {
        list.remove(element);
    }

    @Override
    public void traversal(Visitor<E> visitor) {
        if (visitor==null) return;
        list.stream().forEach(System.out::println);
    }

    public static void main(String[] args) {
        final ListSet<String> stringListSet = new ListSet<>();
        stringListSet.add("ALG");
        stringListSet.add("ALG");
        stringListSet.add("ALG");
        stringListSet.add("GLA");
        stringListSet.add("ALGG");



        stringListSet.traversal(new Visitor<String>() {
            @Override
            public boolean visit(String element) {
                System.out.println(element);
                return false;
            }
        });

    }
}
