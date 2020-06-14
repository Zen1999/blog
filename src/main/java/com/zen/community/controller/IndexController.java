package com.zen.community.controller;

import com.zen.community.context.PaginationContext;
import com.zen.community.dto.PaginationDTO;
import com.zen.community.mapper.UserMapper;
import com.zen.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/5/13 20:32
 */
@Controller
public class IndexController {

  @Autowired(required = false)
  private QuestionService questionService;


  // 增加分页功能
  @GetMapping("/")
  public String index(Model model,
                      @RequestParam(name = "page", defaultValue = PaginationContext.pageDefault) Integer page,
                      @RequestParam(name = "size", defaultValue = PaginationContext.sizeDefault) Integer size) {
    // 添加拦截器 实现判断登录逻辑
    // 查询问题信息
    PaginationDTO paginationDTO = questionService.paginationData(page, size);

    // 放入 model 并传入 thymeleaf
    model.addAttribute("pagination", paginationDTO);
    return "index";
  }
}
