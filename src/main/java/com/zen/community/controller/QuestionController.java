package com.zen.community.controller;

import com.zen.community.dto.CommentDTO;
import com.zen.community.dto.QuestionDTO;
import com.zen.community.service.CommentService;
import com.zen.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/6/11 13:06
 */
@Controller
public class QuestionController {

  @Autowired(required = false)
  private QuestionService questionService;

  @Autowired(required = false)
  private CommentService commentService;

  @GetMapping("/question/{id}")
  public String question(@PathVariable("id") Long questionId,
                         Model model) {
    QuestionDTO question = questionService.getById(questionId);

    List<CommentDTO> comments = commentService.listByQuestionId(questionId);
    // 阅读数增长
    questionService.increaseView(questionId);
    model.addAttribute("question", question);
    model.addAttribute("comments", comments);
    return "question";
  }
}
