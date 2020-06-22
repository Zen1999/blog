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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

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

  // 整个方法体 作为一个事务执行
  // 利用 aop 默认当方法体内抛出 error 或 RuntimeException 时 进行回滚操作
  // Propagation.REQUIRED 支持当前事务 如果当前没有事务 则创建一个事务
  @Transactional(propagation = Propagation.REQUIRED)
  public void create(CommentDTO commentDTO) {
    if (commentDTO.getParentId() == null || commentDTO.getParentId() == 0)
      throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);

    if (commentDTO.getType() == null || !CommentTypeEnum.isExist(commentDTO.getType()))
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
