package life.majiang.community.dto;

import life.majiang.community.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private Integer id; // 问题自增id
    private String title; // title
    private String description; // 描述
    private String tag; // 标签
    private Long gmtCreate; // 创建时间
    private Long gmtModified; // 更新时间
    private Integer creator; // 创建者
    private Integer viewCount; // 阅读数
    private Integer commentCount; // 提交数
    private Integer likeCount; // 点赞数
    private User user; // 用户头像

}
