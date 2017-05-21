package com.guavademo.liam;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by guavademo on 2016/1/6.
 */
public class ImmutableCollectionDemo {

    public void immutableTest() {
        List<String> list=new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");

        System.out.println(list);

        List<String> unmodifiableList= Collections.unmodifiableList(list);

        System.out.println(unmodifiableList);

        List<String> unmodifiableList1=Collections.unmodifiableList(Arrays.asList("a","b","c"));
        System.out.println(unmodifiableList1);

        String temp=unmodifiableList.get(1);
        System.out.println("unmodifiableList [0]："+temp);

        list.add("baby");
        System.out.println("list add a item after list:"+list);
        System.out.println("list add a item after unmodifiableList:"+unmodifiableList);
        System.out.println("list add a item after unmodifiableList1:"+unmodifiableList1);

        unmodifiableList1.add("bb");
        System.out.println("unmodifiableList add a item after list:"+unmodifiableList1);

        unmodifiableList.add("cc");
        System.out.println("unmodifiableList add a item after list:"+unmodifiableList);
    }

    private static void testImmutableCollection() {
        ImmutableCollection<String> immutableCollection = ImmutableList.of();
        immutableCollection.add("2");
        System.out.println(immutableCollection);
    }

    public static void main(String[] args) {
        testImmutableCollectionToArray();
    }

    private static void testImmutableCollectionToArray() {
        ImmutableList<String> list = ImmutableList.of("a", "nb");
        String[] arr = new String[list.size()];
        System.out.println(arr);
        System.out.println("==================");
        for (String s : arr) {
            System.out.println(s);
        }
        System.out.println("==================");
        String[] toArray = list.toArray(arr);
        System.out.println(toArray);
        System.out.println(arr);
        System.out.println("==================");
        for (String s : toArray) {
            System.out.println(s);
        }
        System.out.println("==================");
        for (String s : arr) {
            System.out.println(s);
        }
    }
}
