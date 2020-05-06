package life.majiang.community.controller;

/*
 * 传输层
 * 定义自己的 index
 */

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

//    @Autowired // 注入 UserMapper
//    private UserMapper userMapper;

    @Autowired // 注入 QuestionService
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
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page, // 默认第1页
                        @RequestParam(name = "size", defaultValue = "5") Integer size // 默认5页 每页条数
    ) {
        // 用户 cookies 由拦截器SessionInterceptor执行
//        Cookie[] cookies = request.getCookies(); // 获取用户 cookie
//        if (cookies != null && cookies.length != 0) {// 判断是用户 Cookie 是否为空 长度不为0
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("token")) { // 检查 cookies_key是否为 token
//                    String token = cookie.getValue();
//                    User user = userMapper.findByToken(token);
//                    // 如果user != null 写入session
//                    if (user != null) {
//                        System.out.println(user.toString());
//                        request.getSession().setAttribute("user", user);
//                    }
//                    break; // 命中后结束循环
//                }
//            }
//        }
        // 获取 question_list
//        List<Question> questionList = questionMapper.list(); // question表不能返回用户头像
        PaginationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination); // 此处返回除question信息外还有User信息
        return "index";
    }
}
