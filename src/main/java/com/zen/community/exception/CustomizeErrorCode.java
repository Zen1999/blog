package com.zen.community.exception;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/6/14 16:08
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
  QUESTION_NOT_FOUND(2001, "问题不存在或者已被管理员删除。。。"),
  QUESTION_EDIT_NOT_FOUND(2002, "你修改的问题不存在，要不换个试试？"),
  TARGET_PARAM_NOT_FOUND(2003, "未选中任何问题或评论进行回复。。"),
  COMMENT_INSERT_ERROR(2004, "评论失败惹。。请稍后再试"),
  NO_LOGIN_OPERATE_ERROR(2005, "未登录无法进行评论，请先登录"),
  COMMENT_TYPE_WRONG(2006, "评论类型错误或者不存在"),
  COMMENT_NOT_FOUND(2007, "你评论的问题不在了。。。"),
  REQUEST_4_SERIES_ERROR(3001, "请求错误，要不换个姿势？"),
  REQUEST_5_SERIES_ERROR(3002, "服务器冒烟了，稍后再试试吧！"),
  GITHUB_OAUTH_LINK_ERROR(4001, "连接github获取token失败"),
  GITHUB_API_LINK_ERROR(4002, "连接github获取授权用户信息失败");


  private final String message;
  private final Integer code;



  CustomizeErrorCode(Integer code, String message) {
    this.message = message;
    this.code = code;
  }

  @Override
  public String getMessage() {
    return this.message;
  }

  @Override
  public Integer getCode() {
    return this.code;
  }
}
