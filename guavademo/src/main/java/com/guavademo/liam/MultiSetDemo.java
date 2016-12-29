package com.guavademo.liam;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;

/**
 * Created by chaochun.chen on 15-12-30.
 */
public class MultiSetDemo {

    public static void main(String[] args) {
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("1");
        multiset.add("1");
//        multiset.add("2");
        System.out.println(multiset.count("1"));
        System.out.println(multiset.retainAll(Lists.newArrayList("2")));
        System.out.println(multiset);



    }
}
