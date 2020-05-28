package zen.community.dto;

import lombok.Data;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/5/15 19:54
 */
@Data
public class GithubUser {
  private String name;
  private Long id;
  private String bio;
  private String avatar_url;
}
