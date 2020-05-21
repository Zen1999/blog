package zen.community.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import zen.community.model.User;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/5/21 18:08
 */
@Repository
@Mapper
public interface UserMapper {

  @Insert("INSERT INTO USER (account_id, name, token, gmt_create, gmt_modified) VALUES (#{account_id}, #{name}, #{token}, #{gmt_create}, #{gmt_modified});")
  void insert(User user);


}
