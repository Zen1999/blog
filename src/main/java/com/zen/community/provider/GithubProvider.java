package com.zen.community.provider;

import com.alibaba.fastjson.JSON;
import com.zen.community.dto.AccessTokenDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;
import com.zen.community.dto.GithubUser;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/5/15 18:20
 */
// 组件 扫描组件 放入 spring 的 ioc 容器中
@Component
public class GithubProvider {

  private final String oauthUrl = "https://github.com/login/oauth/access_token";

  private OkHttpClient client = new OkHttpClient().newBuilder()
      .connectTimeout(60, TimeUnit.SECONDS)
      .readTimeout(60, TimeUnit.SECONDS)
      .writeTimeout(60, TimeUnit.SECONDS)
      .build();

  public String getAccessToken(AccessTokenDTO accessTokenDTO) {
    MediaType mediaType = MediaType.get("application/json; charset=utf-8");
    RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
    Request request = new Request.Builder()
        .url(oauthUrl)
        .post(body)
        .build();
    try (Response response = client.newCall(request).execute()) {
      String responseBody = Objects.requireNonNull(response.body()).string();
      return responseBody.split("&")[0].split("=")[1];
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public GithubUser getUser(String accessToken) {
    Request request = new Request.Builder()
        .url("https://api.github.com/user")
        .header("Authorization", "token " + accessToken)
        .build();
    try (Response response = client.newCall(request).execute()) {
      // fastJSON 可以将json对象的属性名以驼峰命名法的方式映射到 bean 中
      return JSON.parseObject(response.body().string(), GithubUser.class);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
