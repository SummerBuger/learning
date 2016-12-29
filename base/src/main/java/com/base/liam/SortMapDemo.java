package com.base.liam;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by guavademo on 2016/11/19.
 */
public class SortMapDemo {

    public static void main(String[] args) {
        Map<Integer, String> map = new LinkedHashMap<Integer, String>();
        map.put(1, "qqqq");
        map.put(5, "aaa");
        map.put(2, "ccc");
        map.put(3, "eeee");
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }

        Map<Integer, String> treeMap = new TreeMap<Integer, String>(map);
        for (Map.Entry<Integer, String> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
