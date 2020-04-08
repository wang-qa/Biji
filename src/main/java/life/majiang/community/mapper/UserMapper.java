package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/*
 * 引入数据库
 * 类会自动放入 不是的类的 需要引入 @Param 注解
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (name, account_ID, token, gmt_Create, gmt_Modified, avatar_url) values (#{name}, #{accountID}, #{token}, #{gmtCreate}, #{gmtModified}, #{avatarUrl})")
    void insert(User user);

    @Select("select * from USER where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from USER where id = #{id}")
    User findByID(@Param("id") Integer id);

}
