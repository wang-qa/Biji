package life.majiang.community.dto;

/*
 * 第三方应用注册信息
 */
public class AccessTokenDTO {
    private String client_id; // 应用标识id
    private String client_secret; // 应用密钥
    private String code; // 响应状态码
    private String redirect_uri; // 连接地址
    private String state; // 连接状态

    // 自动创建get, set 方法   快捷键Alt+Instert
    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
