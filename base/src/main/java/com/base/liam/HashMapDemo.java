package com.base.liam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by liam on 2016/11/22.
 */
public class HashMapDemo {

    public static void main(String[] args) throws JsonProcessingException {
       Map<String, String> map = Maps.newHashMap();
       map.put("test1", "test11111111");
       map.put("test2", "test222222");
       map.put("test3", "test3333");
       map.put("test4", "test444444444444444");
       map.put("test5", "test555555");

      ObjectMapper objectMapper = new ObjectMapper();
      String json = objectMapper.writeValueAsString(map);
      System.out.println(json);

      for (int i = 0; i < 10; i++) {
        map.put("test3", "test33--" + i + i);
        json = objectMapper.writeValueAsString(map);
        System.out.println(json);
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
