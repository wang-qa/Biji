# Spring boot
练习参考资料
>[Spring Boot 文档](https://spring.io/projects/spring-boot)
>[Spring Web](https://spring.io/guides/gs/serving-web-content/)
>[Bootstrap 前端框架](https://v3.bootcss.com/getting-started/#download)
>[Bootstrap 样式表](https://v3.bootcss.com/css/)
>[ES](https://elasticsearch.cn/explore)
>[GitHub OAuth2](https://devoloper.github.com/aoos/building-oauth-apps/creating-an-oauth-app/)
>[Okhttp](https://square.github.io/okhttp)
>[Spring Mybaits](https://mybatis.org/mybatis-3/zh/index.html)
>[Flyway Migration](https://flywaydb.org/getstarted/firststeps/maven)
>[Project Lombok](https://projectlombok.org/)
>[Thymeleaf](https://thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#setting-srreibute-values)
>[Springboot文档手册](https://docs.spring.io/spring-boot/docs/)

工具
>代码版本管理[GitHub](https://github.com)
>流程图[Visual Paradigm](https://www.visual-paradigm.com)
>数据库版本脚本管理[Flyway Migration](https://flywaydb.org/getstarted/firststeps/maven)
>自动插入编辑器并构建工具[Project Lombok](https://projectlombok.org/)
>

> # Git 使用
> ```shell script
> # Git 使用
> git init # 初始化本地仓库
> git add . # 添加当前目录文件
> git state # 查看状态
> git commit -m "备注" # 提交到本地仓库
> git push origin master # 推送到名为origin的远程仓库的master分支
> git pull origin master # 拉取名为origin的远程仓库的master分支
> git commit --amend --no-edit # 提交 > 追加文件到上次本地提交 不需要改备注
> git push -f origin master # 本地强制提交
> git clone [Url] 复制项目
> ```

## 进度
[参考手册地址](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/)
用户信息地址 http://api.github.com/users/用户昵称
> 1. `GitHub`登录之调用 `authorize`
> 2. `GitHub`登录之获取 用户信息`code``token``UserName`
> 3. 配置文件`application.properties`新增配置参数 GitHub.client 信息
> 4. 使用 `MyBaits` 链接数据库并插入数据
> 5. 实现持久化登录 服务器下发 user_token 并在数据库查询
> 6. 集成 `Flyway Migration` 统一数据库结构脚本(数据库版本控制)
> 7. 添加 `Lombok` 支持 自动构建
> 8. 完善首页问题列表展示
> 9. 使用 `developer tools` 添加配置 `spring.devtools.restart.exclude=static/**,public/**` 完成自动部署（热更新）
> 10. 添加分页功能
> 11. 添加拦截器 `WebConfig`
> 12. 完善问题详情页 & 修复登录持久化每次插入不更新用户信息 增加问题更新功能


> > 排查问题先检查`debug` 信息中的 `Caused by`

```markdown
*Flyway Migration*
创建第一次迁移
> 创建迁移目录 `src/main/resources/db/migration`(目标文件路径)
> 进行第一次迁移 `src/main/resources/db/migration/V1__Create_person_table.sql`(SQL语句)
> 执行Flyway迁移数据库 `mvn flyway:migrate`(执行语句)

```

## 浏览器插件[下载Chrome扩展插件Crx离线安装包](https://crxdl.com/)
1. Octotree：快速以tree的方式展示github上的项目文件[ctotree](https://www.octotree.io/) 
2. Table of contents sidebar：快速展示文章大纲[Table of content sidebar](https://chrome.google.com/webstore/detail/table-of-contents-sidebar/ohohkfheangmbedkgechjkmbepeikkej) 
3. OneTab：快速记录chrome打开页面，方便下次直接展开[One Tab](https://chrome.google.com/webstore/detail/chphlpgkkbolifaimnlloiipkdnihall) 



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
    AVATAR_URL   VARCHAR(100),
    constraint TABLE_NAME_PK
        primary key (ID)
);

-- ----------------------------
-- Table structure for Question 
-- ----------------------------
create table question
(
	id int auto_increment,
	title varchar(50),
	description text,
	gmt_create bigint,
	gmt_modified bigint,
	creator int,
	comment_count int default 0,
	view_count int default 0,
	like_count int default 0,
	tag varchar(256),
	constraint question_pk
		primary key (id)
);


```


