package com.zen.community.dto;

import com.zen.community.model.User;
import lombok.Data;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/6/24 9:16
 */
@Data
public class CommentDTO {
  private Long id;
  private Long parentId;
  private Integer type;
  private Long commentator;
  private Long gmtCreate;
  private Long gmtModified;
  private Integer likeCount;
  private String content;
  private User commentUser;
}
