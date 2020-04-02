package life.majiang.community.dto;

import lombok.Data;

/*
 * 获取Github 用户信息
 */
@Data
public class GithubUser {
    private String name; // 用户昵称
    private Long id; // 用户id
    private String bio; // 个人签名
    private String avatar_url; // 用户头像



    @Override
    public String toString(){
        return "GithubUser{" +
                "'neme='" + name + '\'' +
                ", id=" + id + '\'' +
                ", bio=" + bio + '\'' +
                "}";
    }
}
