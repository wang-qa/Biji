package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.*;

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

    @Select("select * from USER where account_id = #{accountID}")
    User findByAccountId(@Param("accountID") String accountID); // 根据accountID查询用户

    @Update("update user set name = #{name}, token = ${token} gmt_modified = #{gmt_Modified}, avatar_url = #{avatarUrl}) where id = #{id v}")
    void update(User user); // 更新用户信息

}
