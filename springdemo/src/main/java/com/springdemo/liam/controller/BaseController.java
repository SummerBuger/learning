//package com.springdemo.liam.controller;
//
//import com.springdemo.liam.component.ProtoComponent;
//import com.springdemo.liam.service.MultiSubService;
//import com.springdemo.liam.util.LoggerSupport;
//import com.springdemo.liam.vo.BaseParam;
//import com.springdemo.liam.vo.Result;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.LineNumberReader;
//import java.lang.reflect.Field;
//import java.util.List;
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.IOUtils;
//import org.apache.tika.exception.TikaException;
//import org.apache.tika.metadata.Metadata;
//import org.apache.tika.parser.AutoDetectParser;
//import org.apache.tika.parser.ParseContext;
//import org.apache.tika.parser.Parser;
//import org.apache.tika.sax.BodyContentHandler;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//import org.xml.sax.ContentHandler;
//import org.xml.sax.SAXException;
//
///**
// * Created by liam on 2016/11/26.
// */
//@Controller
//@RequestMapping("/spring/demo")
//public class BaseController extends LoggerSupport {
//
//  private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);
//
//  @Value("#{'${test.list}'.split(',')}")
//  private List<String> testList;
//
//  @Resource
//  private ProtoComponent protoComponent;
//
//  @Resource
//  private List<MultiSubService> multiSubServiceList;
//
//  @Resource
//  private void initService(List<MultiSubService> multiSubServiceList) {
//    for (MultiSubService multiSubService : multiSubServiceList) {
//      System.out.println("=====================================");
//      multiSubService.test();
//      System.out.println("=====================================");
//    }
//  }
//
//  @RequestMapping(value = "/upload")
//  @ResponseBody
//  public Result<String> upload(@RequestParam("file") MultipartFile file)
//    throws IOException, TikaException, SAXException {
//    ContentHandler contenthandler = new BodyContentHandler();
//    Metadata metadata = new Metadata();
//    metadata.set(Metadata.RESOURCE_NAME_KEY, file.getName());
//    Parser parser = new AutoDetectParser();
//    // OOXMLParser parser = new OOXMLParser();
//    ParseContext parseContext = new ParseContext();
//    parser.parse(file.getInputStream(), contenthandler, metadata, parseContext);
//    System.out.println("===================== Mime: " + metadata.get(Metadata.CONTENT_TYPE));
//    System.out.println("===================== content: " + contenthandler.toString());
//    validateFile(1, file);
//
//    File local = new File("/Users/liam/mlearn/projs/learning/base/src/main/resources/test2.csv");
//    FileUtils.touch(local);
//    file.transferTo(local);
//
//    return Result.success(true, file.getContentType());
//  }
//
//  private void validateFile(long userId, MultipartFile file) {
//    LineNumberReader reader = null;
//    try {
//      reader = new LineNumberReader(new InputStreamReader(file.getInputStream()));
//      int lineNumber = 0;
//      while (reader.readLine() != null) {
//        lineNumber++;
//      }
//      if (lineNumber > 20000) {
//        LOGGER.warn("User upload a feed file out of size limit! userId:{}", userId);
//        throw new IllegalArgumentException("out of limit");
//      }
//    } catch (IOException e) {
//      LOGGER.warn("Parse feed file get err, userId:{}, err:{}", userId, e);
//      throw new IllegalArgumentException(e);
//    } finally {
//      IOUtils.closeQuietly(reader);
//    }
//  }
//
//
//  @RequestMapping("/hello")
//  @ResponseBody
//  public Object hello(HttpServletRequest request) {
//    String test = (String) request.getAttribute("test");
//    Long val = protoComponent.countVal();
//    logger.info("the test:{} val:{}", test, val);
//    for (MultiSubService service : multiSubServiceList) {
//      service.test();
//    }
//    return testList;
//  }
//
//  @RequestMapping("/test/requestParam")
//  @ResponseBody
//  public String testRequestParam(@RequestParam(name = "name") String name,
//    HttpServletRequest request) {
//    String test = (String) request.getAttribute("test");
//    logger.info("the test:{}", test);
//    return "hello " + name;
//  }
//
//  @RequestMapping("/test/model")
//  @ResponseBody
//  public Object testModelParam(BaseParam param) {
//    return param;
//  }
//
//}
