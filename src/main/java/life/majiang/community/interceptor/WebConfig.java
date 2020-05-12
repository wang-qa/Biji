package life.majiang.community.interceptor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc // 全面接管
/**
 * 通过 Configuration 注解注入Interceptors拦截器
 * 访问任何地址时SessionInterceptor拦截到请求 做通用的 Cookie 获取 session
 * 并写入到session
 */
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SessionInterceptor SessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(SessionInterceptor).addPathPatterns("/**"); // 拦截所有目录
    }
}












