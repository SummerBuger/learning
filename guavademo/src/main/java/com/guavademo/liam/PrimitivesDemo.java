package com.guavademo.liam;

import com.google.common.primitives.Booleans;

/**
 * Created by chaochun.chen on 15-12-27.
 */
public class PrimitivesDemo {

    public static void main(String[] args) {
        boolean[] arr = {true, false, true, true};
        System.out.println(arr.length);
        boolean[] arr2 = Booleans.ensureCapacity(arr, 6, 3);
        System.out.println(arr2.length);
        for (boolean b : arr2) {
            System.out.println(b);
        }
    }
}
