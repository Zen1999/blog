package com.zen.community.service;

import com.zen.community.context.PaginationContext;
import com.zen.community.dto.PaginationDTO;
import com.zen.community.dto.QuestionDTO;
import com.zen.community.exception.CustomizeException;
import com.zen.community.mapper.QuestionMapper;
import com.zen.community.model.Question;
import com.zen.community.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zen.community.exception.CustomizeErrorCode;

import java.util.List;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/6/2 11:16
 */
@Service
public class QuestionService {

  @Autowired(required = false)
  private QuestionMapper questionMapper;

  // 查询结果为空 List 也不为空 不用做异常处理
  public PaginationDTO paginationData(Integer page, Integer size) {
    if (page <= 0) page = Integer.parseInt(PaginationContext.pageDefault);
    if (size <= 0) size = Integer.parseInt(PaginationContext.sizeDefault);
    PaginationDTO paginationDTO = new PaginationDTO();
    // 计算偏移量
    Integer offset = PaginationUtil.page2Offset(page, size);
    // 查询分页后的数据
    List<QuestionDTO> data = questionMapper.paginationList(offset, size);

    Integer count = questionMapper.count();

    paginationDTO.setData(data);
    paginationDTO.setPagination(count, page, size);
    return paginationDTO;
  }


  // 查询结果为空 List 也不为空 不用做异常处理
  public PaginationDTO paginationDataById(Integer userId, Integer page, Integer size) {
    if (page <= 0) page = Integer.parseInt(PaginationContext.pageDefault);
    if (size <= 0) size = Integer.parseInt(PaginationContext.sizeDefault);
    PaginationDTO paginationDTO = new PaginationDTO();
    // 计算偏移量
    Integer offset = PaginationUtil.page2Offset(page, size);
    // 查询分页后的数据
    List<QuestionDTO> data = questionMapper.paginationListByUserId(userId, offset, size);

    Integer count = questionMapper.countByUserId(userId);

    paginationDTO.setData(data);
    paginationDTO.setPagination(count, page, size);
    return paginationDTO;
  }


  // 判断问题创建还是更新
  public void createOrUpdate(Question question) {
    if (question.getId() == null) {
      // 插入行 更新创建时间及修改时间
      question.setGmtCreate(System.currentTimeMillis());
      question.setGmtModified(question.getGmtCreate());
      questionMapper.insert(question);
    } else {
      // 更新行
      question.setGmtModified(System.currentTimeMillis());
      if (questionMapper.updateByPrimaryKeySelective(question) != 1) {
        // 不等于 1 更新失败 抛出异常
        throw new CustomizeException(CustomizeErrorCode.QUESTION_EDIT_NOT_FOUND);
      }

    }
  }

  public QuestionDTO getById(Integer questionId) {
    QuestionDTO questionDTO = questionMapper.getById(questionId);
    if (questionDTO == null) throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
    return questionDTO;
  }

  public void increaseView(Integer questionId) {
    // 添加数据库字段 避免并发时脏读脏写 使用锁 或者 事务 或者 原子性操作即可
    if (questionMapper.increaseViewCount(questionId) != 1) throw new CustomizeException(CustomizeErrorCode.QUESTION_EDIT_NOT_FOUND);
  }
}
