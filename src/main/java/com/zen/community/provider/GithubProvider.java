package com.zen.community.provider;

import com.alibaba.fastjson.JSON;
import com.zen.community.dto.AccessTokenDTO;
import com.zen.community.dto.GithubUser;
import com.zen.community.exception.CustomizeErrorCode;
import com.zen.community.exception.CustomizeException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpVersion;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/5/15 18:20
 */
// 组件 扫描组件 放入 spring 的 ioc 容器中
@Component
public class GithubProvider {

  private final String oauthUrl = "https://github.com/login/oauth/access_token";
  private final String userApiUrl = "https://api.github.com/user";

  // 配置 HttpClient
  private RequestConfig defaultRequestConfig = RequestConfig.custom()
      .setSocketTimeout(60 * 1000)
      .setConnectTimeout(60 * 1000)
      .setConnectionRequestTimeout(60 * 1000)
      .build();

  // GET https://github.com/login/oauth/access_token
  public String getAccessToken(AccessTokenDTO accessTokenDTO) {
    URIBuilder uriBuilder = null;
    HttpGet httpGet = null;
    try {
      uriBuilder = new URIBuilder(oauthUrl);
      uriBuilder.addParameter("client_id", accessTokenDTO.getClientId());
      uriBuilder.addParameter("client_secret", accessTokenDTO.getClientSecret());
      uriBuilder.addParameter("code",  accessTokenDTO.getCode());
      // 构建 URI
      httpGet = new HttpGet(uriBuilder.build());
      // 配置超时时间
      httpGet.setConfig(defaultRequestConfig);
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }

    try (CloseableHttpClient httpClient = HttpClients.createDefault();
         CloseableHttpResponse response = httpClient.execute(httpGet)) {
      // 执行 GET 请求
      if (response.getStatusLine().getStatusCode() == 200) {
        HttpEntity entity = Objects.requireNonNull(response.getEntity());
        String entityContent = EntityUtils.toString(entity);
        return entityContent.split("&")[0].split("=")[1];
      }
      throw new CustomizeException(CustomizeErrorCode.GITHUB_OAUTH_LINK_ERROR);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  // GET https://api.github.com/user
  public GithubUser getUser(String accessToken) {
    HttpGet httpGet = new HttpGet(userApiUrl);
    httpGet.setHeader(new BasicHeader("Authorization", "token " + accessToken));
    // HTTP 1.0
    httpGet.setProtocolVersion(HttpVersion.HTTP_1_0);
    httpGet.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
    httpGet.setConfig(defaultRequestConfig);

    try (CloseableHttpClient httpClient = HttpClients.createDefault();
         CloseableHttpResponse response = httpClient.execute(httpGet)) {
      if (response.getStatusLine().getStatusCode() == 200) {
        HttpEntity entity = Objects.requireNonNull(response.getEntity());
        String entityContent = EntityUtils.toString(entity);
        return JSON.parseObject(entityContent, GithubUser.class);
      }
      throw new CustomizeException(CustomizeErrorCode.GITHUB_API_LINK_ERROR);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
