package com.zen.community.controller;

import com.zen.community.context.ContentTypeContext;
import com.zen.community.dto.CommentDTO;
import com.zen.community.dto.ResultDTO;
import com.zen.community.exception.CustomizeErrorCode;
import com.zen.community.exception.CustomizeException;
import com.zen.community.mapper.CommentMapper;
import com.zen.community.model.Comment;
import com.zen.community.model.User;
import com.zen.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/6/17 21:13
 */
@Controller
public class CommentController {

  @Autowired(required = false)
  CommentService commentService;

  // json
  // 评论功能
  @ResponseBody
  @PostMapping("/comment")
  public Object comment(@RequestBody CommentDTO commentDTO,
                        HttpServletRequest request) {
    if (!ContentTypeContext.APPLICATION_JSON.equals(request.getContentType()))
      return ResultDTO.errorOf(CustomizeErrorCode.REQUEST_4_SERIES_ERROR);
    User user = (User) request.getSession().getAttribute("user");
    if (user == null) return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN_OPERATE_ERROR);
    commentService.create(commentDTO);

    return ResultDTO.ok();
  }
}
