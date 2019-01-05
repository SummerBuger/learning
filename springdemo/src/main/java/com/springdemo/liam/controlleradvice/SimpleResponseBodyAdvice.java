//package com.springdemo.liam.controlleradvice;
//
//import com.springdemo.liam.vo.Result;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//
///**
// * Created by liam on 2016/12/21.
// */
//@ControllerAdvice(annotations = RestController.class)
//public class SimpleResponseBodyAdvice implements ResponseBodyAdvice<Result> {
//
//    /**
//     * 校验是否是需要的接入点
//     * @param returnType
//     * @param converterType
//     * @return
//     */
//    public boolean supports(MethodParameter returnType, Class converterType) {
//        return returnType.getMethod().getReturnType().equals(Result.class);
//    }
//
//    /**
//     * 在往 outputStream 中写入返回结果之前，对返回结果进行处理
//     */
//    public Result beforeBodyWrite(Result body, MethodParameter returnType, MediaType selectedContentType,
//                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
//                                  ServerHttpRequest request, ServerHttpResponse response) {
//        return body.withRet(false).withData("被 ResponseBodyAdvice 修改的结果");
//    }
//}
