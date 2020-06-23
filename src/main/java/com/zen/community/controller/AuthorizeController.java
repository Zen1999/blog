package com.zen.community.controller;

import com.zen.community.dto.AccessTokenDTO;
import com.zen.community.dto.GithubUser;
import com.zen.community.mapper.UserMapper;
import com.zen.community.model.User;
import com.zen.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


/**
 * @author Zen
 * @version 1.0
 * @date 2020/5/15 18:12
 */
@Controller
public class AuthorizeController {

  @Autowired
  private GithubProvider githubProvider;

  @Value("${github.oauth.client_id}")
  private String client_id;
  @Value("${github.oauth.client_secret}")
  private String client_secret;
  @Value("${github.oauth.redirect_uri}")
  private String redirect_uri;

  @Autowired(required = false)
  private UserMapper userMapper;

  // Github 授权登录
  @GetMapping("/callback")
  public String callback(@RequestParam(name = "code") String code,
                         @RequestParam(name = "state") String state,
                         HttpServletResponse response) {
    User user = null;
    AccessTokenDTO accessTokenDTO = new AccessTokenDTO(client_id, client_secret, code, redirect_uri, state);
    String accessToken = githubProvider.getAccessToken(accessTokenDTO);
    GithubUser githubUser = githubProvider.getUser(accessToken);
    // 获取用户成功并且在数据库中没有该用户的字段
    if (githubUser != null) {
      // 获取 githubUser 的 id
      String accountId = String.valueOf(githubUser.getId());
      if ((user = userMapper.getByAccountId(accountId)) == null) {
        // token 字段的数据结构为 CHAR(36) 使用 UUID.randomUUID() 生成的通用唯一识别码 插入记录
        user = new User(githubUser.getName(),
            accountId,
            UUID.randomUUID().toString(),
            githubUser.getBio(),
            githubUser.getAvatarUrl(),
            System.currentTimeMillis());
        user.setGmtModified(user.getGmtCreate());
        userMapper.insert(user);
      }
      // 用户信息获取成功，改变登录态
      Cookie cookie = new Cookie("token", user.getToken());
      // cookie 存活时间 三个月
      cookie.setMaxAge(60 * 60 * 24 * 90);
      response.addCookie(cookie);
    }
    return "redirect:/";
  }

  // 登出
  @GetMapping("/logout")
  public String logout(HttpServletRequest request, HttpServletResponse response) {
    request.getSession().removeAttribute("user");
    Cookie cookie = new Cookie("token", null);
    // 删除 cookie
    cookie.setMaxAge(0);
    response.addCookie(cookie);
    return "redirect:/";
  }
}
