package life.majiang.community.interceptor;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
//@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;


    @Override // 请求前执行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
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
        return true; // 返回 true 继续执行
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
