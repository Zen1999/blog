package zen.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import zen.community.Mapper.UserMapper;
import zen.community.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/5/13 20:32
 */
@Controller
public class IndexController {

  @Autowired
  private UserMapper userMapper;

  @GetMapping("/")
  public String index(HttpServletRequest request) {
    User user = null;
    Cookie[] cookies = request.getCookies();
    if (!Objects.isNull(cookies)) {
      // 遍历 cookies 判断是否存在 name 为 token 的 value
      for (Cookie cookie : cookies) {
        if ("token".equals(cookie.getName())) {
          // 找到 value 后 查找数据库 找出 user 对象 并 跳出循环
          String value = cookie.getValue();
          user = userMapper.getUserByToken(value);
          break;
        }
      }
       // 如果 user 查询后不等于空 则将 user 对象 添加到请求域
      if (!Objects.isNull(user)) {
        request.getSession().setAttribute("user", user);
      }
    }

    return "index";
  }
}
