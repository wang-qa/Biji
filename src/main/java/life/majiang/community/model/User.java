package life.majiang.community.model;

// 数据库表格式
/*create table USER
        (
        ID           INT auto_increment,
        ACCOUNT_ID   VARCHAR,
        NAME         VARCHAR(100),
        TOKEN        CHAR(36),
        GMT_CREATE   BIGINT,
        GMT_MODIFIED BIGINT,
        constraint TABLE_NAME_PK
        primary key (ID)
        );*/
public class User {
    private Integer id; // 用户自增id
    private String name; // username
    private String accountID; // 用户来源渠道id
    private String token; // user_token
    private Long gmtCreate; // 创建时间
    private Long gmtModified; // 更新时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }
}
