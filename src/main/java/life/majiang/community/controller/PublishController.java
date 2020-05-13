package life.majiang.community.controller;

import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("publish/{id}") // 编辑问题
    public String edit(@PathVariable(name = "id") Integer id,
                       Model model) {
        QuestionDTO question = questionService.getById(id);
        // 回显到页面
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());
        return "publish";
    }

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
            @RequestParam(value = "id", required = false) Integer id,
            HttpServletRequest request,
            Model model
    ) {

        /**
         * publish_page 获取填写者信息 判断是否登录
         * 移动到 SessionController
         * */

        // 获取User
        User user = (User) request.getSession().getAttribute("user");

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
        question.setTitle(title);// set 标题
        question.setDescription(description);// set 内容
        question.setTag(tag);// set 标签
        question.setCreator(user.getId());// set 提问者id
        question.setId(id); // set 问题id
        questionService.createOrUpdate(question); // 执行SQL 插入或更新question
        return "redirect:/"; // 成功返回 首页
    }
}
