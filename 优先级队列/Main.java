package com.mj.优先级队列;

import org.junit.Test;

import java.util.Comparator;

public class Main {

    @Test
    public void test1(){
        PriorityQueue<Person> priorityQueue = new PriorityQueue<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.getWeight()-o1.getWeight();
            }
        });
        priorityQueue.enQueue(new Person("lwb",140));
        priorityQueue.enQueue(new Person("mmj",150));
        priorityQueue.enQueue(new Person("jzk",160));
        while (!priorityQueue.isEmpty())
        System.out.println(priorityQueue.deQueue());

    }
}
