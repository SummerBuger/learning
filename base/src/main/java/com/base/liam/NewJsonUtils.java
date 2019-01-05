package com.base.liam;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 处理json的工具类
 *
 * @author hexiufeng
 */
public final class NewJsonUtils {
  private static final Logger LOGGER = LoggerFactory.getLogger(NewJsonUtils.class);

  private NewJsonUtils() {
  }

  private static final ObjectMapper MAPPER = new ObjectMapper();

  static {
    MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    MAPPER.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);

    MAPPER.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
  }

  /**
   * 转换对象为json
   *
   * @param obj 对象
   * @return json string
   */
  public static String toJson(Object obj) {
    try {
      return MAPPER.writeValueAsString(obj);
    } catch (IOException e) {
      LOGGER.error(null, e);
    }
    return null;
  }
  /**
   * to Map
   */
  public static <T> Map<String, T> fromJsonToMap(String jsonString,
                                                 TypeReference<Map<String, T>> type) {
    try {
      return MAPPER.readValue(jsonString, type);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


}