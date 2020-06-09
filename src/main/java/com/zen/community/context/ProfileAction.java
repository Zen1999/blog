package com.zen.community.context;

import lombok.Getter;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/6/10 1:33
 */
@Getter
public enum ProfileAction {
  QUESTION("question", "我的问题"),
  REPLIES("replies", "我的回复");

  String section;
  String sectionName;

  ProfileAction(String section, String sectionName) {
    this.section = section;
    this.sectionName = sectionName;
  }
}
