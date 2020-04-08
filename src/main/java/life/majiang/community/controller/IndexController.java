package life.majiang.community.controller;

/*
 * 传输层
 * 定义自己的 index
 */

import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired // 注入 UserMapper
    private UserMapper userMapper;

    @Autowired // 注入 UserMapper
    private QuestionService questionService;

    /**
     * 访问首页的时候 循环看所有的Cookie
     * 找到 Cookie 为 token 的值
     * 用这个值去数据库中查找是否存在
     * 如果有 把 user放入 session
     * 前端通过页面数据判断是否登录
     */
    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model) {
        Cookie[] cookies = request.getCookies(); // 获取用户 cookie
        if (cookies != null && cookies.length != 0) {// 判断是用户 Cookie 是否为空 长度不为0
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) { // 检查 cookies_key是否为 token
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    // 如果user != null 写入session
                    if (user != null) {
                        System.out.println(user.toString());
                        request.getSession().setAttribute("user", user);
                    }
                    break; // 命中后结束循环
                }
            }
        }
        // 获取 question_list
//        List<Question> questionList = questionMapper.list(); // question表不能返回用户头像
        List<QuestionDTO> questionList = questionService.list();
        model.addAttribute("questions", questionList); // 此处返回除question信息外还有User信息
        return "index";
    }
}
