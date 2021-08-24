package com.mj.字符串;

import com.mj.tools.Assert;

public class Main {
    public static void main(String[] args) {
        Assert.test(BruteForce1.indexOf("Hello World", "or")==7);
        Assert.test(BruteForce1.indexOf("Hello World", "sb")==-1);

        Assert.test(KMP.indexOf("Hello World", "or")==7);
        Assert.test(KMP.indexOf("Hello World", "sb")==-1);

    }
}
