package com.springdemo.liam.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wangtao
 */
public class ThreadContextInterceptor extends HandlerInterceptorAdapter {

  private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o)
      throws Exception {
    LOGGER.info("request interceptor pre handle");
    MDC.put("test", "test");
//    if (true) {
//      throw new RuntimeException("测试");
//    }
    return true;
  }


  @Override
  public void postHandle(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)
      throws Exception {
    LOGGER.info("request interceptor postHandle");
//    if (true) {
//      throw new RuntimeException("测试");
//    }
  }

  @Override
  public void afterCompletion(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    LOGGER.info("request interceptor afterCompletion");
  }

}
