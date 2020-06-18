package com.zen.community.advice;

import com.alibaba.fastjson.JSON;
import com.zen.community.context.ContentTypeContext;
import com.zen.community.dto.ResultDTO;
import com.zen.community.exception.CustomizeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/6/14 14:39
 */
@Slf4j
@ControllerAdvice
public class CustomizeExceptionHandler {
  private final String DEFAULT_ERROR_MESSAGE = "服务错误，请稍后重试";


  // Spring boot 无法捕获 thymeleaf 的渲染异常
  // 在访问 api 接口时不返回错误页面 而是返回 JSON
  // 区别是访问普通页面时请求的 Content-type: text/html
  // 而 api 接口为 Content-type: application/json
  @ExceptionHandler(Exception.class)
  ModelAndView handle(HttpServletRequest request,
                      HttpServletResponse response, Throwable ex) {
    String contentType = request.getContentType();
    if (ContentTypeContext.APPLICATION_JSON.equals(contentType)) {
      ResultDTO resultDTO = ex instanceof CustomizeException ?
          ResultDTO.errorOf((CustomizeException) ex) : ResultDTO.error();
      response.setContentType(ContentTypeContext.APPLICATION_JSON);
      response.setStatus(200);
      response.setCharacterEncoding("UTF-8");

      // 这里要先设置 response 的字符编码再获取 writer 否则设置无效
      try (PrintWriter writer = response.getWriter()) {
        writer.write(JSON.toJSONString(resultDTO));
      } catch (IOException e) {
        e.printStackTrace();
      }
      // 返回 JSON
      return null;
    } else {
      // 错误页面跳转
      ModelAndView modelAndView = new ModelAndView("error");
      // 如果为自定义异常则返回异常信息，否则则返回默认信息
      modelAndView.addObject("message", ex instanceof CustomizeException ?
              ex.getMessage() : DEFAULT_ERROR_MESSAGE);
      // 打印报错信息 定位异常位置
      log.error(Arrays.toString(ex.getStackTrace()));
      return modelAndView;
    }
  }

}
