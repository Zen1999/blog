package com.zen.community;

import com.zen.community.dto.AccessTokenDTO;
import com.zen.community.dto.CommentDTO;
import com.zen.community.mapper.CommentMapper;
import com.zen.community.mapper.QuestionMapper;
import com.zen.community.mapper.UserMapper;
import com.zen.community.service.CommentService;
import com.zen.community.service.QuestionService;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@SpringBootTest
class CommunityApplicationTests {

  @Autowired(required = false)
  private UserMapper userMapper;

  @Autowired(required = false)
  private QuestionService questionService;

  @Autowired(required = false)
  private QuestionMapper questionMapper;

  @Autowired(required = false)
  private CommentService commentService;

  @Autowired(required = false)
  private CommentMapper commentMapper;

  private final String oauthUrl = "https://github.com/login/oauth/access_token";
  private final String userApiUrl = "https://api.github.com/user";

  @Value("${github.oauth.client_id}")
  private String client_id;
  @Value("${github.oauth.client_secret}")
  private String client_secret;
  @Value("${github.oauth.redirect_uri}")
  private String redirect_uri;

  @Test
  void contextLoads() {
    // 测试连接 H2 database
    // H2 database 只能支持单线程读写
  }

}
