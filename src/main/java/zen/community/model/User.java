package zen.community.model;

import lombok.Data;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/5/21 18:14
 */

@Data
public class User {
  private Integer id;
  private String name;
  private String account_id;
  private String token;
  private Long gmt_create;
  private Long gmt_modified;

  public User(String name, String account_id, String token, Long gmt_create) {
    this.name = name;
    this.account_id = account_id;
    this.token = token;
    this.gmt_create = gmt_create;
  }
}
