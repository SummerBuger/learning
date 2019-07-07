package com.base.liam;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.sun.tools.corba.se.idl.constExpr.Or;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.darwin.genericDao.operate.Order;

/**
 * Created by guavademo on 2016/11/19.
 */
public class SortMapDemo {

    private static final char[][] KEYNOTES = new char[][] {
      {'Q','W','E','R','T','Y','U','I','O','P'},
      {'A','S','D','F','G','H','J','K','L'},
      {'Z','X','C','V','B','N','M'}
    };

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

        System.out.println("==============================================");

        List<String> result = new ArrayList<>();
        result.add("test");
        String[] res = result.toArray(new String[]{});
        System.out.println(NewJsonUtils.toJson(res));

        String a = "cba";
        char[] chars = a.toCharArray();

        Ordering<Character> ordering = Ordering.explicit(Lists.newArrayList('c', 'b', 'a'));
        ordering.sortedCopy(Lists.newArrayList('a', 'd' , 's', 'e', 'b'));

    }


}
