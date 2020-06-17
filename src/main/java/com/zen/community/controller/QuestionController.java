package com.zen.community.controller;

import com.zen.community.dto.QuestionDTO;
import com.zen.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/6/11 13:06
 */
@Controller
public class QuestionController {

  @Autowired(required = false)
  private QuestionService questionService;

  @GetMapping("/question/{id}")
  public String question(@PathVariable("id") Integer questionId,
                         Model model) {
    QuestionDTO question = questionService.getById(questionId);
    // 阅读数增长
    questionService.increaseView(questionId);
    model.addAttribute("question", question);
    return "question";
  }
}
