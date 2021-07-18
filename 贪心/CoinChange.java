package com.mj.贪心;

import java.util.*;

public class CoinChange {
    public static void main(String[] args) {
        int[] faces = {25, 10, 5, 1};
        Arrays.sort(faces);
        int money = 41, coins = 0;
        Map<Integer, Integer> count = new TreeMap<Integer,Integer>();
        for (int i = faces.length - 1; i >= 0; i--) {
            while (money >= faces[i]) {
                money -= faces[i];
                coins++;
                count.put(faces[i], count.getOrDefault(faces[i], 0) + 1);
            }
        }
        System.out.println("共兑换了" + coins + "枚");
        for (Map.Entry<Integer, Integer> e : count.entrySet()) {
            Integer key = e.getKey();
            Integer value = e.getValue();
            System.out.println(key+":"+value);
        }
    }
}
