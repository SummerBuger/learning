package com.springdemo.liam.component;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

import java.util.Date;

/**
 * Created by chaochun.ccc on 2017/1/8.
 */
public class JodaDateEditorRegistrar implements PropertyEditorRegistrar
{

  private String datePattern;

  public void registerCustomEditors(PropertyEditorRegistry registry) {
    registry.registerCustomEditor(Date.class, new JodaDateEditor(datePattern));
  }

  public String getDatePattern() {
    return datePattern;
  }

  public void setDatePattern(String datePattern) {
    this.datePattern = datePattern;
  }
}
