package com.zen.community;

import com.zen.community.dto.QuestionDTO;
import com.zen.community.mapper.QuestionMapper;
import com.zen.community.mapper.UserMapper;
import com.zen.community.model.Question;
import com.zen.community.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CommunityApplicationTests {

  @Autowired(required = false)
  private UserMapper userMapper;

  @Autowired(required = false)
  private QuestionService questionService;


  @Test
  void contextLoads() {
    // 测试连接 H2 database
    // H2 database 只能支持单线程读写
    Question question = new Question("公众号修改", "gzhxg", 161, "tags");
    question.setId(129);
    questionService.createOrUpdate(question);
  }

}
