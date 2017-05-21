package com.base.liam.javassist.simpledemo;

import javassist.*;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by liam on 2017-01-23.
 */
public class Demo {

  public static void main(String[] args) {

    try {
      ClassPool classPool = ClassPool.getDefault();


      long r = ThreadLocalRandom.current().nextLong();

      // 从当前的class loader 中获取一个类
      CtClass baseClass = classPool.get("com.base.liam.javassist.simpledemo.Demo");

      // 构造制定类名和父类 的类
      CtClass ctClass =  classPool.makeClass("Test$generator$" + r +"$", baseClass);

      // 在 classpath 中查找 @see java.util.String
      CtClass stringClass = classPool.get(String.class.getName());

      // 新增name 属性
      CtField nameField = new CtField(stringClass, "name", ctClass);
      nameField.setModifiers(Modifier.PRIVATE);
      ctClass.addField(nameField);

      // 新增name getter setter 方法
      ctClass.addMethod(CtNewMethod.getter("getName", nameField));
      ctClass.addMethod(CtNewMethod.setter("setName", nameField));

      ctClass.writeFile();
      System.out.println(ctClass);
    } catch (Exception e) {
      e.printStackTrace();
    }



  }

}
