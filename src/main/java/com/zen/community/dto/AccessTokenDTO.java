package com.zen.community.dto;

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
  private String clientId;
  private String clientSecret;
  private String code;
  private String redirectUri;
  private String state;
}
