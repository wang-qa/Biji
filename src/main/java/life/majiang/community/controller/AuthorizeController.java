package life.majiang.community.controller;

/*
 * 获取code
 */

import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import life.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // 把类作为路由Api的承载者
public class AuthorizeController {

    @Autowired // 把spring容器的实例加载到当前上下文
    private GithubProvider githubProvider;

    // 加载配置文件--github.client
    @Value("${github.client.id}")
    private String clientID;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.client.uri}")
    private String clientUri;

  @GetMapping("/callback") // 登录成功后返回到登录页
    // 接收返回后的参数 code, state
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientID);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(clientUri);
        accessTokenDTO.setState(state);
        githubProvider.getAccess_token(accessTokenDTO);
        String accessToken = githubProvider.getAccess_token(accessTokenDTO);
        GithubUser user =  githubProvider.getUser(accessToken);
        System.out.println("用户昵称 >>> " + user.getName()); // 输出 User昵称
        return "index";
    }


}




