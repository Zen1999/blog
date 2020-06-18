package com.zen.community.controller;

import com.zen.community.dto.QuestionDTO;
import com.zen.community.model.Question;
import com.zen.community.model.User;
import com.zen.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/5/25 14:50
 */
@Controller
public class PublishController {

  @Autowired
  QuestionService questionService;

  // 发布问题页面
  @GetMapping("/publish")
  public String publish() {
    return "publish";
  }

  // 添加问题请求
  @PostMapping("/publish")
  public String doPublish(@RequestParam(name = "title", required = false) String title,
                          @RequestParam(name = "description", required = false) String description,
                          @RequestParam(name = "tags", required = false) String tags,
                          @RequestParam(name = "questionId", required = false) Long questionId,
                          HttpServletRequest request,
                          Model model) {
    // 获取作者信息
    User user = (User)request.getSession().getAttribute("user");
    if (!Objects.isNull(user)) {
      if (title.equals("")) {
        model.addAttribute("error", "标题不能为空");
        return "publish";
      }
      if (description.equals("")) {
        model.addAttribute("error", "问题描述不能为空");
        return "publish";
      }
      if (tags.equals("")) {
        model.addAttribute("error", "标签不能为空");
        return "publish";
      }
      Question question = new Question(title, description, user.getId(), tags);
      question.setId(questionId);
      questionService.createOrUpdate(question);
    } else {
      model.addAttribute("error", "用户未登录");
      model.addAttribute("title", title);
      model.addAttribute("description", description);
      model.addAttribute("tags", tags);
      return "publish";
    }
    return "redirect:/";
  }

  // 问题编辑
  @GetMapping("/publish/{questionId}")
  public String publishEdit(@PathVariable("questionId") Long questionId,
                            Model model) {
    QuestionDTO question = questionService.getById(questionId);
    model.addAttribute("title", question.getTitle());
    model.addAttribute("description", question.getDescription());
    model.addAttribute("tags", question.getTags());
    model.addAttribute("questionId", question.getId());
    return "/publish";
  }
}
