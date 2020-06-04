package com.zen.community.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import com.zen.community.model.User;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/5/21 18:08
 */
@Repository
@Mapper
public interface UserMapper {

  @Insert("INSERT INTO user (account_id, name, token, gmt_create, gmt_modified, bio, avatar_url) VALUES (#{accountId}, #{name}, #{token}, #{gmtCreate}, #{gmtModified}, #{bio}, #{avatarUrl});")
  void insert(User user);

  @Select("SELECT * FROM user WHERE token = #{token};")
  User getUserByToken(@Param("token") String token);

  @Select("SELECT * FROM user WHERE account_id = #{accountId};")
  User getUserByAccountId(@Param("accountId")String accountId);

  @Select("SELECT * FROM user WHERE id = #{id};")
  User getUserById(@Param("id")String id);
}
