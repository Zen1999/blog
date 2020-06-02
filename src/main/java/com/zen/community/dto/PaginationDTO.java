package com.zen.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/6/2 11:10
 */
@Data
public class PaginationDTO {

  // 可做js逻辑判断 减轻服务器负担
  private List<QuestionDTO> data;
  private Boolean showPre;
  private Boolean showNext;
  private Boolean showFirst;
  private Boolean showEnd;

  private Integer currentPage;
  private Integer totalPage;
  private List<Integer> pages = new ArrayList<>();

  // 页码左右最多显示的标签
  private Integer pageMargin = 3;

  public void setPagination(Integer count, Integer page, Integer size) {
    this.currentPage = page;
    this.totalPage = count / size;
    // 计算页码数
    if (count % size != 0) this.totalPage++;
    // 计算页码逻辑
    // 当页码小于4时 显示第一页 当页码小于totalPage - 4时 显示最后一页
    pages.add(page);
    for (int i = 1; i <= pageMargin; i++) {
      if (page - i > 0) pages.add(0, page - i);
      if (page + i <= totalPage) pages.add(page + i);
    }
    // 计算是否显示上一页
    showPre = !page.equals(1);
    // 计算是否显示下一页
    showNext = !page.equals(totalPage);
    // 是否展示跳转第一页
    showFirst = !pages.contains(1);
    // 是否展示跳转最后一页
    showEnd = !pages.contains(totalPage);
  }
}
