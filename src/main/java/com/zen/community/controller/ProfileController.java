package com.zen.community.controller;

import com.zen.community.context.PaginationContext;
import com.zen.community.context.ProfileAction;
import com.zen.community.dto.PaginationDTO;
import com.zen.community.model.User;
import com.zen.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/6/4 21:08
 */
@Controller
public class ProfileController {

  @Autowired
  QuestionService questionService;

  @GetMapping("/profile/{action}")
  public String profile(HttpServletRequest request,
                        @PathVariable(name = "action") String action,
                        @RequestParam(name = "page", defaultValue = PaginationContext.pageDefault) Integer page,
                        @RequestParam(name = "size", defaultValue = PaginationContext.sizeDefault) Integer size,
                        Model model) {
    ProfileAction profileAction;
    PaginationDTO paginationDTO = null;
    switch (action) {
      case "replies":
        profileAction = ProfileAction.REPLIES;
        // 获取 session 中的 user 对象
        break;
      case "question":
      default:
        profileAction = ProfileAction.QUESTION;
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
          // 如果用户未登录则重定向回主页
          return "redirect:/";
        }
        paginationDTO = questionService.paginationDataById(user.getId(), page, size);
        model.addAttribute("pagination", paginationDTO);
        break;
    }
    model.addAttribute("section", profileAction.getSection());
    model.addAttribute("sectionName", profileAction.getSectionName());

    return "profile";
  }
}
