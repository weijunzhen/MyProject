package com.azhen.myproject.provider;

import com.azhen.myproject.dto.AccessTokenDTO;
import com.azhen.myproject.dto.GitHubUserDTO;
import okhttp3.*;
import sun.misc.Contended;

import java.io.IOException;

@Contended
public class GithubProvider {
    public String getaccesstoken(AccessTokenDTO a)
    {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string =  response.body().string();
            System.out.println(string);
            return string;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public GitHubUserDTO user(String string){

    }
}
