package zen.community.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import zen.community.model.Question;

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
}
