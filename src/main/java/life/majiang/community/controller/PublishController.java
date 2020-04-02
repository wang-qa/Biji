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

        /**
         * publish_page 获取填写者信息 判断是否登录
         * */
        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {// 判断是用户 Cookie 是否为空 长度不为0 去除空指针
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
        }

        /**
         * publish_page 问题提交校验逻辑 判断填写内容
         * */
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        if (title == null || title == "") { // title 为null或空
            model.addAttribute("error", "问题标题不能为空 请输入");
            return "publish";// 有问题返回 publish_page
        }
        if (description == null || description == "") { // description 为null或空
            model.addAttribute("error", "问题描述不能为空 请输入");
            return "publish";// 有问题返回 publish_page
        }
        if (tag == null || tag == "") { // tag 为null或空
            model.addAttribute("error", "问题标签为空 请输入");
            return "publish";// 有问题返回 publish_page
        }


        System.out.println("--->>> publish_pages_User >>>---" + user);
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";// 有问题返回 publish_page
        }

        /**
         * publish_page 问题提内容
         * */
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        questionMapper.create(question);
        return "redirect:/"; // 成功返回 首页
    }
}
