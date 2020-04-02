package life.majiang.community.controller;

/**
 * 获取code
 */

import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller // 把类作为路由Api的承载者
public class AuthorizeController {

    @Autowired // 把spring容器的实例加载到当前上下文
    private GithubProvider githubProvider;

    // 加载配置文件--github.client
    // 注入 application.properties 的 GitHub 参数
    @Value("${github.client.id}")
    private String clientID;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.client.uri}")
    private String clientUri;

    @Autowired // 把 Usermapper 对象放入容器内
    private UserMapper userMapper;

    @GetMapping("/callback") // 登录成功后返回到登录页
    // 接收返回后的参数 code, state
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           // HttpServletRequest request,//不需要，可删除
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientID);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(clientUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccess_token(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken); // 使用 GitHub 登录
//        System.out.println("用户昵称 >>> " + user.getName()); // 输出 User昵称

        /** 登录跳转 */
        if (githubUser != null && githubUser.getId() != null) {//githubUser 和 getId 不为空
            /** 使用 GitHub 登录成功 */
            User user = new User();
            // 获取用户信息生成>>>用户 token
            String token = UUID.randomUUID().toString();
            /** token 放入 User 对象中并存储到数据库 */
            user.setToken(token); // 生成唯一的标识码
            user.setName(githubUser.getName());
            user.setAccountID(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatar_url());
            System.out.println("--->>> usermapper >>>--- " + userMapper);
            System.out.println("--->>> insertuser >>>--- " + user);
            userMapper.insert(user);
            /** Cookie 写入 */
            // 自动写入  服务器生成 Token 放入 Cookie
            response.addCookie(new Cookie("token", token));
            // 手动写入  登录成功 >>> 写 cookies 和 session
//            request.getSession().setAttribute("user", githubUser); // session存入 user对象
            return "redirect:/";
        } else {
            // 登录失败 >>> 重新登录 原因
            return "redirect:/";
        }
    }
}

