package com.mj.字符串;

public class BruteForce1 {


    public static int indexOf(String text, String pattern) {
        if (text == null || pattern == null) { return -1; }
        char[] textChars = text.toCharArray();
        int tlen = textChars.length;
        if (tlen == 0) { return -1; }
        char[] patternCharts = pattern.toCharArray();
        int plen = patternCharts.length;
        if (plen == 0) { return -1; }
        if (tlen < plen) { return -1; }

//        int pi = 0, ti = 0;
//        while (pi < plen && ti -pi <= tlen-plen) {
//            if (textChars[ti] == patternCharts[pi]) {
//                ti++;
//                pi++;
//            } else {
//                ti -= pi - 1;
//                pi = 0;
//            }
//        }
//
//        return (pi == plen) ? (ti - pi) : -1;

        int tiMax = tlen - plen;
        for (int ti = 0; ti <= tiMax; ti++) {
            int pi;
            for (pi = 0; pi < plen; pi++) {
                if (textChars[ti + pi] != patternCharts[pi]) {
                    break;
                }
            }
            if (pi == plen) { return ti; }
        }
        return -1;

    }

}