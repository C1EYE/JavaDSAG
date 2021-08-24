package com.mj.字符串;

public class KMP {
    public static int indexOf(String text, String pattern) {
        if (text == null || pattern == null) { return -1; }
        char[] textChars = text.toCharArray();
        int tlen = textChars.length;
        if (tlen == 0) { return -1; }
        char[] patternCharts = pattern.toCharArray();
        int plen = patternCharts.length;
        if (plen == 0) { return -1; }
        if (tlen < plen) { return -1; }

        int[] next = next(pattern);
        int pi = 0, ti = 0;
        while (pi < plen && ti - pi <= tlen - plen) {
            //如果pi==-1 实际上等于直接向后移动一位ti
            if (pi < 0 || textChars[ti] == patternCharts[pi]) {
                ti++;
                pi++;
            } else {
                pi = next[pi];
            }
        }

        return (pi == plen) ? (ti - pi) : -1;
    }

    private static int[] next(String pattern) {
        char[] chars = pattern.toCharArray();
        int[] next = new int[chars.length];
        int i = 0;
        int n = -1;
        next[0] = -1;
        int iMax = chars.length - 1;
        while (i < iMax) {
            if (n < 0 || chars[i] == chars[n]) {
                next[++i] = ++n;
            }else {
                n = next[n];
            }
        }
        return next;
    }

    private static int[] next2(String pattern) {
        char[] chars = pattern.toCharArray();
        int[] next = new int[chars.length];
        int i = 0;
        int n = -1;
        next[0] = -1;
        int iMax = chars.length - 1;
        while (i < iMax) {
            if (n < 0 || chars[i] == chars[n]) {
                ++i;
                ++n;
                if(chars[i]==chars[n]){
                    next[i] = next[n];
                }else{
                    next[i] = n;
                }
            }else {
                n = next[n];
            }
        }
        return next;
    }

}
