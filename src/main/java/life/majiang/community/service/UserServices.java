package life.majiang.community.service;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdate(User user) {
        User dbuser = userMapper.findByAccountId(user.getAccountID()); // userMapper查找AccountID并返回user对象
        if (dbuser == null) {
            // 未找到 插入操作
            user.setGmtCreate(System.currentTimeMillis()); // set 创建时间
            user.setGmtModified(user.getGmtCreate()); // set 更新时间
            userMapper.insert(user);
        } else {
            // 找到 更新操作
            dbuser.setGmtModified(user.getGmtCreate()); // set 更新时间
            dbuser.setName(user.getName()); // set 用户名
            dbuser.setAvatarUrl(user.getAvatarUrl()); // set 用户头像
            dbuser.setToken(user.getToken()); // set 用户token
            userMapper.update(dbuser);

        }
    }
}
