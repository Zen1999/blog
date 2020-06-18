package com.zen.community.service;

import com.zen.community.dto.CommentDTO;
import com.zen.community.enums.CommentTypeEnum;
import com.zen.community.exception.CustomizeErrorCode;
import com.zen.community.exception.CustomizeException;
import com.zen.community.mapper.CommentMapper;
import com.zen.community.mapper.QuestionMapper;
import com.zen.community.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/6/17 21:52
 */
@Service
public class CommentService {

  @Autowired(required = false)
  CommentMapper commentMapper;

  @Autowired(required = false)
  QuestionMapper questionMapper;


  public void create(CommentDTO commentDTO) {
    if (commentDTO.getParentId() == null || commentDTO.getParentId() == 0)
      throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);

    if (commentDTO.getType() == null || CommentTypeEnum.isExist(commentDTO.getType()))
      throw new CustomizeException(CustomizeErrorCode.COMMENT_TYPE_WRONG);

    if (CommentTypeEnum.QUESTION.getType().equals(commentDTO.getType())) {
      // 回复问题
      if (questionMapper.selectByPrimaryKey(commentDTO.getParentId()) == null)
        throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
      // 添加评论数
      questionMapper.increaseCommentCount(commentDTO.getParentId());
    } else {
      // 回复评论
      if (commentMapper.selectByPrimaryKey(commentDTO.getParentId()) == null)
        throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
    }

    // 构造 model
    Comment comment = new Comment(commentDTO.getParentId(), commentDTO.getType(),
        commentDTO.getParentId(), System.currentTimeMillis(), commentDTO.getContent());
    comment.setGmtModified(comment.getGmtCreate());

    // 插入
    if (commentMapper.insert(comment) != 1)
      throw new CustomizeException(CustomizeErrorCode.COMMENT_INSERT_ERROR);
  }
}
