package com.springdemo.liam;

import com.springdemo.liam.controlleradvice.BaseControllerAdvice;

import java.lang.annotation.Annotation;

/**
 * Created by liam on 2016/12/19.
 */
public class Main {
    public static void main(String[] args) {
        Class<BaseControllerAdvice> clazz = BaseControllerAdvice.class;
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
            Annotation[] an = annotation.getClass().getAnnotations();
            System.out.println("========== " + an.length);
            for (Annotation a : an) {
                System.out.println(a);
            }
        }
    }
}
