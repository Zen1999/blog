package com.zen.community.utils;


/**
 * @author Zen
 * @version 1.0
 * @date 2020/6/2 10:54
 */
public class PaginationUtil {

  public static Integer page2Offset(Integer page, Integer size) {

    return (page - 1) * size;
  }
}
