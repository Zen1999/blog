package zen.community;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zen.community.Mapper.UserMapper;
import zen.community.model.User;

@SpringBootTest
class CommunityApplicationTests {

  @Autowired
  private UserMapper userMapper;

  @Test
  void contextLoads() {
    // 测试连接 H2 database
    // H2 database 只能支持单线程读写
    User user = new User("1", "1", "1", 1L);
    user.setGmt_modified(1L);
    userMapper.insert(user);
  }

}
