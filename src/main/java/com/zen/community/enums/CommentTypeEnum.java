package com.zen.community.enums;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/6/18 9:25
 */
public enum CommentTypeEnum {
  QUESTION(1),
  COMMENT(2);

  private Integer type;

  CommentTypeEnum(Integer type) {
    this.type = type;
  }

  public static boolean isExist(Integer type) {
    for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
      if (commentTypeEnum.getType().equals(type)) return true;
    }
    return false;
  }

  public Integer getType() {
    return type;
  }
}
