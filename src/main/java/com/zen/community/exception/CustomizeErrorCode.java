package com.zen.community.exception;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/6/14 16:08
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
  QUESTION_NOT_FOUND("问题不存在或者已被管理员删除。。。"),
  QUESTION_EDIT_NOT_FOUND("你修改的问题不存在，要不换个试试？"),
  REQUEST_4_SERIES_ERROR("请求错误，要不换个姿势？"),
  REQUEST_5_SERIES_ERROR("服务器冒烟了，稍后再试试吧！");

  private final String message;


  CustomizeErrorCode(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return this.message;
  }
}
