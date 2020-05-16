package zen.community.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/5/15 18:21
 */
@Data
@AllArgsConstructor
public class AccessTokenDTO {
  private String client_id;
  private String client_secret;
  private String code;
  private String redirect_uri;
  private String state;
}
