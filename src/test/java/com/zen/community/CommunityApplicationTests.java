package com.zen.community;

import com.zen.community.dto.CommentDTO;
import com.zen.community.dto.ResultDTO;
import com.zen.community.mapper.QuestionMapper;
import com.zen.community.mapper.UserMapper;
import com.zen.community.service.CommentService;
import com.zen.community.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommunityApplicationTests {

  @Autowired(required = false)
  private UserMapper userMapper;

  @Autowired(required = false)
  private QuestionService questionService;

  @Autowired(required = false)
  private QuestionMapper questionMapper;

  @Autowired(required = false)
  private CommentService commentService;

  @Test
  void contextLoads() {
    // 测试连接 H2 database
    // H2 database 只能支持单线程读写
  }

}
