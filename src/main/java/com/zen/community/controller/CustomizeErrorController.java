package com.zen.community.controller;

import com.zen.community.exception.CustomizeErrorCode;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/6/14 16:45
 */
// 当 ErrorController 不存在时 BasicErrorController 就会启用
@Controller
// 重定向至 error 控制器
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomizeErrorController implements ErrorController {

  // 获取错误模板地址 /templates/error.html
  @Override
  public String getErrorPath() {
    return "error";
  }

  @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
  public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
    HttpStatus status = getStatus(request);
    ModelAndView modelAndView = new ModelAndView("error");
    if (status.is4xxClientError()) {
      // 400 Client Error
      modelAndView.addObject("message", CustomizeErrorCode.REQUEST_4_SERIES_ERROR.getMessage());
    }
    if (status.is5xxServerError()) {
      // 500 Server Error
      modelAndView.addObject("message", CustomizeErrorCode.REQUEST_5_SERIES_ERROR.getMessage());
    }
    return modelAndView;
  }

  private HttpStatus getStatus(HttpServletRequest request) {
    Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
    if (statusCode == null) {
      return HttpStatus.INTERNAL_SERVER_ERROR;
    }
    try {
      return HttpStatus.valueOf(statusCode);
    }
    catch (Exception ex) {
      return HttpStatus.INTERNAL_SERVER_ERROR;
    }
  }
}
