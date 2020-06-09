package com.zen.community.service;

import com.zen.community.context.PaginationContext;
import com.zen.community.dto.PaginationDTO;
import com.zen.community.dto.QuestionDTO;
import com.zen.community.mapper.QuestionMapper;
import com.zen.community.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/6/2 11:16
 */
@Service
public class QuestionService {

  @Autowired
  private QuestionMapper questionMapper;

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

}
