package zen.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zen.community.Mapper.UserMapper;
import zen.community.dto.AccessTokenDTO;
import zen.community.dto.GithubUser;
import zen.community.model.User;
import zen.community.provider.GithubProvider;

import javax.servlet.http.HttpServletRequest;
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

  @Autowired
  private UserMapper userMapper;

  @GetMapping("/callback")
  public String callback(@RequestParam(name = "code") String code,
                         @RequestParam(name = "state") String state,
                         HttpServletRequest request) {
    AccessTokenDTO accessTokenDTO = new AccessTokenDTO(client_id, client_secret, code, redirect_uri, state);
    String accessToken = githubProvider.getAccessToken(accessTokenDTO);
    GithubUser githubUser = githubProvider.getUser(accessToken);
    if (githubUser != null) {
      // token 字段的数据结构为 CHAR(36) 使用 UUID.randomUUID() 生成的通用唯一识别码 插入记录
      User user = new User(githubUser.getName(),
          String.valueOf(githubUser.getId()),
          UUID.randomUUID().toString(),
          System.currentTimeMillis());
      user.setGmt_modified(user.getGmt_create());
      userMapper.insert(user);
      // 用户信息获取成功，改变登录态
      request.getSession().setAttribute("user", githubUser);
    }
    return "redirect:/";
  }
}
