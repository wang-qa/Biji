package life.majiang.community.model;

import lombok.Data;

@Data
public class User {
    private Integer id; // 用户自增id
    private String name; // username
    private String accountID; // 用户来源渠道id
    private String token; // user_token
    private Long gmtCreate; // 创建时间
    private Long gmtModified; // 更新时间
    private String avatarUrl; // 用户头像

}