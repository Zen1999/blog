package com.zen.community.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/5/25 14:06
 */
@Data
@AllArgsConstructor
public class Question {
  private Integer id;
  private String title;
  private String description;
  private Long gmtCreate;
  private Long gmtModified;
  private Integer creatorId;
  private Integer commentCount;
  private Integer viewCount;
  private Integer likeCount;
  private String tags;

  public Question(String title, String description, Long gmtCreate, Integer creatorId, String tags) {
    this.title = title;
    this.description = description;
    this.gmtCreate = gmtCreate;
    this.creatorId = creatorId;
    this.tags = tags;
  }
}
