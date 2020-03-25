# Spring boot
练习资料
>[Spring Boot 文档](https://spring.io/projects/spring-boot)
>[Spring Web](https://spring.io/guides/gs/serving-web-content/)
>[Bootstrap 前端框架](https://v3.bootcss.com/getting-started/#download)
>[ES](https://elasticsearch.cn/explore)
>[GitHub OAuth2](https://devoloper.github.com/aoos/building-oauth-apps/creating-an-oauth-app/)
>[Okhttp](https://square.github.io/okhttp)
>[Spring Mybaits](https://mybatis.org/mybatis-3/zh/index.html)

工具
>[Git](https://github.com)
>[Visual Paradigm](https://www.visual-paradigm.com)

> # Git 使用
> ```shell script
> git init # 初始化本地仓库
> git add . # 添加当前目录文件
> git state # 查看状态
> git commit -m "备注" # 提交到本地仓库
> git push origin master # 推送到名为origin的远程仓库的master分支
> git pull origin master # 拉取名为origin的远程仓库的master分支
> git commit --amend --no-edit # 提交 > 追加文件到上次本地提交 不需要改备注
> git push -f origin master # 本地强制提交
> ```

## 进度
> 1. `GitHub`登录之调用 `authorize`
> 2. `GitHub`登录之获取 用户信息`code``token``UserName`
> 3. 配置文件`application.properties`新增配置参数 GitHub.client 信息
> 4. 使用 `MyBaits` 链接数据库并插入数据
> 5. 实现持久化登录 服务器下发 user_token 并在数据库查询
> 6. 集成 `Flyway Migration` 统一数据库结构脚本(数据库版本控制)
>

```markdown
Creating the first migration
We create the migration directory `src/main/resources/db/migration`
Followed by a first migration called `src/main/resources/db/migration/V1__Create_person_table.sql`
```
## 数据库脚本

```sql
table For H2
-- ----------------------------
-- Table structure for User 
-- ----------------------------
create table USER
(
    ID           INT auto_increment,
    ACCOUNT_ID   VARCHAR,
    NAME         VARCHAR(100),
    TOKEN        CHAR(36),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
    BIO          VARCHAR(256),
    constraint TABLE_NAME_PK
        primary key (ID)
);

```


