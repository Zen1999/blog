package com.zen.community;

import com.zen.community.dto.PaginationDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.zen.community.mapper.QuestionMapper;
import com.zen.community.mapper.UserMapper;

import java.util.List;

@SpringBootTest
class CommunityApplicationTests {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private QuestionMapper questionMapper;


  @Test
  void contextLoads() {
    // 测试连接 H2 database
    // H2 database 只能支持单线程读写
    PaginationDTO paginationDTO = new PaginationDTO();
    paginationDTO.setPagination(100, 4, 10);
  }

}
