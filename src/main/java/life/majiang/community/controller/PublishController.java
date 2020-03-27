package life.majiang.community.controller;

import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired // 注入 questionMapper
    private QuestionMapper questionMapper;

    @Autowired // 注入 userMapper
    private UserMapper userMapper;

    // get 去渲染页面
    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    // post 去执行请求
    @PostMapping("/publish")
    public String doQuestion(
            //接收参数
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model
    ) {
        // 获取填写者信息
        User user = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("token")) { // 检查 cookies_key是否为 token
                String token = cookie.getValue();
                user = userMapper.findByToken(token);
                // 如果user != null 写入session
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break; // 命中后结束循环
            }
        }

        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";// 有问题返回 piblish
        }

        // 获取参数
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        questionMapper.create(question);
        return "redirect:/"; // 成功返回 首页
    }
}
