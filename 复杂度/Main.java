package com.mj.复杂度;

public class Main {
    public static int fib(int n)
    {
        if (n<=1) return n;
        return fib(n - 1) + fib(n - 2);
    }

    public static int fib2(int n)
    {
        if (n<=1) return n;
        int first = 0;
        int second = 1;
        for (int i=0;i<n-1;i++){
            int sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }

    public static void main(String[] args) {

        Times.test("fib2", new Times.Task() {
            @Override
            public void execute() {
                fib2(50);
            }
        });
    }
}
