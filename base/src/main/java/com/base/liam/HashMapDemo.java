package com.base.liam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liam on 2016/11/22.
 */
public class HashMapDemo {

    public static void main(String[] args) {
        Map<HashSimple, String> map = new HashMap();
        List<HashSimple> hashSimples = new ArrayList<HashSimple>();
        for (int i = 0; i < 16; i++) {
            HashSimple tmp = new HashSimple();
            hashSimples.add(tmp);
        }
        int idx = 0;
        for (HashSimple hashSimple : hashSimples) {
            map.put(hashSimple, (idx ++) + "");
        }
        for (HashSimple hashSimple : hashSimples) {
            System.out.println(map.get(hashSimple));
        }
    }

    static class HashSimple {
        @Override
        public boolean equals(Object obj) {
            return true;
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }
}
