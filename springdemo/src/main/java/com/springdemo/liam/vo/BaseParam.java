package com.springdemo.liam.vo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by liam on 2016/12/12.
 */
public class BaseParam {

    private SubParam subParam;

    private Integer id;

    public SubParam getSubParam() {
        return subParam;
    }

    public void setSubParam(SubParam subParam) {
        this.subParam = subParam;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static void main(String[] args) throws JsonProcessingException {
        BaseParam baseParam = new BaseParam();
        SubParam subParam = new SubParam();
        subParam.setIp("127.0.0.1");
        subParam.setName("tester");
        baseParam.setSubParam(subParam);
        baseParam.setId(1);
        System.out.println(new ObjectMapper().writeValueAsString(baseParam));
    }
}
