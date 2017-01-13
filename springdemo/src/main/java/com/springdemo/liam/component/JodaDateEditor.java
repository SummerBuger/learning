package com.springdemo.liam.component;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * Created by chaochun.ccc on 2017/1/7.
 */
public class JodaDateEditor extends PropertyEditorSupport {

  private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

  private String dateTimePattern;

  public JodaDateEditor() {
  }

  public JodaDateEditor(String dateTimePattern) {
    this.dateTimePattern = dateTimePattern;
  }

  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    LOGGER.info("the customer joda date time editor");

    DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(dateTimePattern);
    Date date = dateTimeFormatter.parseDateTime(text).toDate();

    super.setValue(date);
  }

  public String getDateTimePattern() {
    return dateTimePattern;
  }

  public void setDateTimePattern(String dateTimePattern) {
    this.dateTimePattern = dateTimePattern;
  }
}
