package zen.community.Mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
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

  @Insert("INSERT INTO user (account_id, name, token, gmt_create, gmt_modified) VALUES (#{accountId}, #{name}, #{token}, #{gmtCreate}, #{gmtModified});")
  void insert(User user);

  @Select("SELECT * FROM user WHERE token = #{token};")
  User getUserByToken(@Param("token") String token);
}
