package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/*
 * 引入数据库
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (name, account_ID, token, gmt_Create, gmt_Modified) value (#{name}, #{accountID}, #{token}, #{gmtCreate}, #{gmtModified})")
    void insert(User user);
}
