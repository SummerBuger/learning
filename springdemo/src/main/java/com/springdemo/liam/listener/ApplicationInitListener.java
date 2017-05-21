package com.springdemo.liam.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

/**
 * Created by liam on 2017-02-17.
 */
public class ApplicationInitListener extends ContextLoaderListener {

  private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

  @Override
  public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
    String profile = System.getProperty("profile");
    LOGGER.info("the profile of tomcat:{}", profile);

    if ("preonline".equals(profile)) {
      System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, profile);
    }
    return super.initWebApplicationContext(servletContext);
  }
}
