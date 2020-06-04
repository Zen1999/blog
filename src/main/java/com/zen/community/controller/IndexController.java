package com.zen.community.controller;

import com.zen.community.context.PaginationContext;
import com.zen.community.dto.PaginationDTO;
import com.zen.community.mapper.UserMapper;
import com.zen.community.model.User;
import com.zen.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

  @Autowired
  private QuestionService questionService;


  // 增加分页功能
  @GetMapping("/")
  public String index(HttpServletRequest request,
                      Model model,
                      @RequestParam(name = "page", defaultValue = PaginationContext.pageDefault) Integer page,
                      @RequestParam(name = "size", defaultValue = PaginationContext.sizeDefault) Integer size) {
    // 做出 page 和 size 正数判断
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
      if (user != null) {
        request.getSession().setAttribute("user", user);
      }
    }

    // 查询问题信息
    PaginationDTO paginationDTO = questionService.paginationData(page, size);


    // 放入 model 并传入 thymeleaf
    model.addAttribute("pagination", paginationDTO);
    return "index";
  }
}
