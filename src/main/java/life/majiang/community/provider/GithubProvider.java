package life.majiang.community.provider;

import com.alibaba.fastjson.JSON;
import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*
 *
 */
@Component // 把类初始化到spring容器的上下文中
public class GithubProvider {
    // post方法 获取用户token
    public String getAccess_token(AccessTokenDTO assessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(assessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body) // post请求体
                .build();

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.out.println("response响应 >>> " + string); // 输出
//            return string; // 返回 string
            String[] split = string.split("&"); // 通过"&"分割
            String tokenstr = split[0]; // 提取第一个字符串
            String token = tokenstr.split("=")[1]; // 通过"="分割
            System.out.println("用户token >>> " + token);
            return token;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // get方法 获取用户信息
    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        System.out.println("getUser >>> " + accessToken);
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class); // Json解析为类对象
            return githubUser;
        } catch (IOException e) {
        }
        return null;

    }
}

