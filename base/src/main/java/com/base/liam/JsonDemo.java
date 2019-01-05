package com.base.liam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonDemo {

  public static void main(String[] args) {
    testFailOnEmptyField();
  }

  public static void testFailOnEmptyField() {
    TestBean simpleBean = new TestBean();
    simpleBean.setName("test");
    simpleBean.setTb(simpleBean);

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.disable(SerializationFeature.WRAP_EXCEPTIONS);
//    objectMapper.disable(SerializationFeature.FAIL_ON_SELF_REFERENCES);
    try {
      String s = objectMapper.writeValueAsString(simpleBean);
      System.out.println(s);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  private static class TestBean {
    private String name;
    private TestBean tb;

    public String getName() {
      return name;
    }

    public TestBean setName(String name) {
      this.name = name;
      return this;
    }

    public TestBean getTb() {
      return tb;
    }

    public TestBean setTb(TestBean tb) {
      this.tb = tb;
      return this;
    }
  }

}
