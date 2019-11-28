package life.majiang.community.controller;

/*
 * 获取code
 */

import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // 把类作为路由Api的承载者
public class AuthorizeController {

    @Autowired // 把spring容器的实例加载到当前上下文
    private GithubProvider githubProvider;


    @GetMapping("/callback") // 登录成功后返回到登录页
    // 接收返回后的参数 code, state
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("639f4c8d6441c6136b44");
        accessTokenDTO.setClient_secret("024fb99f36d3ce6764d6e00eb15c9545a284cb9");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDTO.setState(state);
        githubProvider.getAccess_token(accessTokenDTO);
        return "index";
    }


}




