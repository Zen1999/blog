package com.zen.community;

import com.zen.community.dto.QuestionDTO;
import com.zen.community.mapper.QuestionMapper;
import com.zen.community.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CommunityApplicationTests {

  @Autowired(required = false)
  private UserMapper userMapper;

  @Autowired(required = false)
  private QuestionMapper questionMapper;


  @Test
  void contextLoads() {
    // 测试连接 H2 database
    // H2 database 只能支持单线程读写
    Integer count = questionMapper.count();
    System.out.println(count);
  }

}
