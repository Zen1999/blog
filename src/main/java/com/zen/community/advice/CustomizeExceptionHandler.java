package com.zen.community.advice;

import com.zen.community.exception.CustomizeException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/6/14 14:39
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
  private final String defaultErrorMessage = "服务错误，请稍后重试";

  // Spring boot 无法捕获 thymeleaf 的渲染异常
  @ExceptionHandler(Exception.class)
  ModelAndView handle(HttpServletRequest request, Throwable ex) {
    ModelAndView modelAndView = new ModelAndView("error");
    // 如果为自定义异常则返回异常信息，否则则返回默认信息
    modelAndView.addObject("message", ex instanceof CustomizeException? ex.getMessage() : defaultErrorMessage);
    return modelAndView;
  }

}
