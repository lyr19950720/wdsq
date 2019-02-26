package com.wjl.wdsq.dao;

import com.wjl.wdsq.model.Question;
import com.wjl.wdsq.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionDAO {
    // 注意空格
    String TABLE_NAME = " question ";
    String INSERT_FIELDS = " title, content, created_date, user_id,comment_count ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{title},#{content},#{createdDate},#{userId},#{commentCount})"})
    int addQuestion(Question question);

    List<Question> selectLatestQuestions(@Param("userId") int userId,
                                         @Param("offset") int offset,
                                         @Param("limit") int limit);

    @Update({"update",TABLE_NAME," set password=#{password} where id=#{id}"})
    void updatePassword(User user);

    @Delete({"delete from",TABLE_NAME," where id=#{id}"})
    void deleteById(int id);
}
