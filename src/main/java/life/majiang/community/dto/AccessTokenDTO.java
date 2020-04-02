package life.majiang.community.dto;

import lombok.Data;

/*
 * 第三方应用注册信息
 */
@Data
public class AccessTokenDTO {
    private String client_id; // 应用标识id
    private String client_secret; // 应用密钥
    private String code; // 响应状态码
    private String redirect_uri; // 连接地址
    private String state; // 连接状态

    // 自动创建get, set 方法   快捷键Alt+Instert

}
