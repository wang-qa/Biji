package life.majiang.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProfileController {
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model) {

        if ("questions".equals(action)) { // 如果acton为空
            model.addAttribute("section", "questions");
            model.addAttribute("section", "questions");
        }

        return "profile";
    }
}
