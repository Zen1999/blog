package com.zen.community.exception;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/6/14 15:41
 */
// 继承 RuntimeException 只需要 throw 出来 上层不用 try catch
public class CustomizeException extends RuntimeException {
  private String message;
  private Integer code;

  public CustomizeException(ICustomizeErrorCode errorCode) {
    this.code = errorCode.getCode();
    this.message = errorCode.getMessage();
  }

  @Override
  public String getMessage() {
    return message;
  }

  public Integer getCode() {
    return code;
  }
}
