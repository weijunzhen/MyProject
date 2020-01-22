package com.azhen.myproject.provider;

import com.alibaba.fastjson.JSON;
import com.azhen.myproject.dto.AccessTokenDTO;
import com.azhen.myproject.dto.GitHubUserDTO;
import okhttp3.*;
import org.springframework.stereotype.Component;
import sun.misc.Contended;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getaccesstoken(AccessTokenDTO accessTokenDTO)
    {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string =  response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GitHubUserDTO getUser(String access_token){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+access_token)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GitHubUserDTO gitHubUserDTO = JSON.parseObject(string, GitHubUserDTO.class);
            //将string类型的变量直接转换成GithubUserDTO的类对象
            return gitHubUserDTO;
        } catch (IOException e) {
        }
        return null;
    }
}
