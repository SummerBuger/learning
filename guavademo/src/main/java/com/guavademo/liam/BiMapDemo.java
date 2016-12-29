package com.guavademo.liam;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * Created by chaochun.chen on 15-12-28.
 */
public class BiMapDemo {

    public static void hashBiMapTest() {
        BiMap<String, String> map = HashBiMap.create();

        for (int i = 0; i < 10; i++) {
            map.put("reimburse" + i, "product" + i);
        }
        System.out.println(map);
        map.remove("reimburse1");
        BiMap<String, String> inverse = map.inverse();
        System.out.println(inverse);

    }


    public static void main(String[] args) {
        hashBiMapTest();
    }
}
