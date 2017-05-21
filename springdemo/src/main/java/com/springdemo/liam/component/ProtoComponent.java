package com.springdemo.liam.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by liam on 2017-03-07.
 */
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class ProtoComponent {
  private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

  private Long val;

  public ProtoComponent() {
    LOGGER.info("create the new ProtoComponent");
    val = ThreadLocalRandom.current().nextLong(10000l);
  }

  public Long countVal() {
    return val;
  }
}
