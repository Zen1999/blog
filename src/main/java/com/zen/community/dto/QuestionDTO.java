package com.zen.community.dto;

import com.zen.community.model.User;
import lombok.Data;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/6/1 13:50
 */
@Data
public class QuestionDTO {
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
  private User creator;
}
