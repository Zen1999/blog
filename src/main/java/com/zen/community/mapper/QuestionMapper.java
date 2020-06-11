package com.zen.community.mapper;

import com.zen.community.dto.QuestionDTO;
import com.zen.community.model.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Zen
 * @version 1.0
 * @date 2020/5/25 14:05
 */
@Repository
@Mapper
public interface QuestionMapper {

  @Insert("INSERT INTO question (title, description, gmt_create, gmt_modified, creator_id, tags) " +
      "VALUES (#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creatorId}, #{tags})")
  void create(Question question);


  // 查询的 Question 对象没有用户头像，需要关联查询
  @Results(id = "creatorConstructor",
      value = @Result(column = "creator_id", property = "creator",
          one = @One(select = "com.zen.community.mapper.UserMapper.getUserById")))
  @Select("SELECT * FROM question")
  List<QuestionDTO> list();

  @ResultMap(value = "creatorConstructor")
  @Select("SELECT * FROM question WHERE id = #{questionId}")
  QuestionDTO getById(@Param("questionId") Integer questionId);

  // Mybatis 注解版 使用了方法名作为方法映射id 所以在 Mapper 接口中不能重载方法
  // 查询问题根据修改时间排序
  @ResultMap(value = "creatorConstructor")
  @Select("SELECT * FROM question ORDER BY GMT_MODIFIED DESC LIMIT #{offset}, #{size}")
  List<QuestionDTO> paginationList(@Param("offset") Integer offset, @Param("size") Integer size);

  // 查询问题根据修改时间排序
  @ResultMap(value = "creatorConstructor")
  @Select("SELECT * FROM question WHERE creator_id = #{userId} ORDER BY GMT_MODIFIED DESC LIMIT #{offset}, #{size}")
  List<QuestionDTO> paginationListByUserId(@Param("userId") Integer userId,
                                       @Param("offset") Integer offset,
                                       @Param("size") Integer size);

  @Select("UPDATE question SET title = #{title}, description = #{description}, gmt_modified = #{gmtModified}, tags = #{tags} WHERE id = #{id}")
  Boolean update(Question question);

  @Select("SELECT COUNT(1) FROM QUESTION WHERE creator_id = #{userId};")
  Integer countByUserId(@Param("userId") Integer userId);

  @Select("SELECT COUNT(1) FROM QUESTION;")
  Integer count();
}
