package life.majiang.community.controller;

/*
 * 定义自己的 index
 */

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired // 注入 UserMapper
    private UserMapper userMapper;

    /**
     * 访问首页的时候 循环看所有的Cookie
     * 找到 Cookie 为 token 的值
     * 用这个值去数据库中查找是否存在
     * 如果有 把 user放入 session
     * 前端通过页面数据判断是否登录
     */
    @GetMapping("/")
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies(); // 获取用户 cookie
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) { // 检查 cookies_key是否为 token
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    // 如果user != null 写入session
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break; // 命中后结束循环
                }
            }
        }
        return "index";
    }
}
