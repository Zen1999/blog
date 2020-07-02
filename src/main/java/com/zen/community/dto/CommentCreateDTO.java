package com.zen.community.dto;

import lombok.Data;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/6/17 21:21
 */
@Data
public class CommentCreateDTO {
  private Long parentId;
  private String content;
  private Integer type;
}
