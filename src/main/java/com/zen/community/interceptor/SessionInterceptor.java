package com.zen.community.interceptor;

import com.zen.community.mapper.UserMapper;
import com.zen.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/6/10 2:44
 */
@Service
public class SessionInterceptor implements HandlerInterceptor {

  // 自动注入失败原因，需要将 bean 交于 spring 接管
  @Autowired(required = false)
  UserMapper userMapper;

  // 在请求处理之前 先进行登录判断逻辑
  @Override
  public boolean preHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler) throws Exception {
    // 如果从 session 中能获取到 user 对象证明已经登录，直接放行
    if (Objects.isNull(request.getSession().getAttribute("user"))) {
      // 创建空的 user 对象
      User user = null;
      Cookie[] cookies = request.getCookies();
      if (!Objects.isNull(cookies)) {
        // 遍历 cookies 判断是否存在 name 为 token 的 value
        for (Cookie cookie : cookies) {
          if ("token".equals(cookie.getName())) {
            // 找到 value 后 查找数据库 找出 user 对象 并 跳出循环
            user = userMapper.getByToken(cookie.getValue());
            break;
          }
        }
        // 如果 user 查询后不等于空 则将 user 对象 添加到请求域
        if (user != null) {
          request.getSession().setAttribute("user", user);
        }
      }
    }
    // 放行
    return true;
  }
}
