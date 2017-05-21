package com.springdemo.liam.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Created by liam on 2017-02-20.
 */
public class GrayUserLimitInterceptor extends HandlerInterceptorAdapter {

  private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    LOGGER.info("the GrayUserLimitInterceptor pre ");
    return true;
  }
}
