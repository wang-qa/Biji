package life.majiang.community.controller;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.model.User;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

//    @Autowired // 注入 UserMapper
//    private UserMapper userMapper;

    @Autowired // 注入 QuestionService
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action") String action,
                          @RequestParam(name = "page", defaultValue = "1") Integer page, // 默认第1页
                          @RequestParam(name = "size", defaultValue = "5") Integer size, // 默认5页 每页条数
                          Model model) {
//        User user = null;
//        Cookie[] cookies = request.getCookies(); // 获取用户 cookie
//        if (cookies != null && cookies.length != 0) {// 判断是用户 Cookie 是否为空 长度不为0
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("token")) { // 检查 cookies_key是否为 token
//                    String token = cookie.getValue();
//                    user = userMapper.findByToken(token);
//                    // 如果user != null 写入session
//                    if (user != null) {
//                        System.out.println(user.toString());
//                        request.getSession().setAttribute("user", user);
//                    }
//                    break; // 命中后结束循环
//                }
//            }
//        }
        // 获取User
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) { // 如果未登录跳转到 首页
            return "redirect:/"; // 返回 首页;
        }

        if ("questions".equals(action)) { // 如果acton为空
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }

        PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
        model.addAttribute("pagination", paginationDTO); // 此处返回除question信息外还有User信息
        return "profile";
    }
}
