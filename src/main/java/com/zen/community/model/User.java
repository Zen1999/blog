package com.zen.community.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/5/21 18:14
 */

@Data
@AllArgsConstructor
public class User {
  private Integer id;
  private String accountId;
  private String name;
  private String token;
  private Long gmtCreate;
  private Long gmtModified;
  private String bio;
  private String avatarUrl;

  public User(String name, String accountId, String token, String bio, String avatarUrl, Long gmtCreate) {
    this.name = name;
    this.accountId = accountId;
    this.token = token;
    this.bio = bio;
    this.avatarUrl = avatarUrl;
    this.gmtCreate = gmtCreate;
  }
}
