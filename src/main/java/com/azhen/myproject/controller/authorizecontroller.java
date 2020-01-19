package com.azhen.myproject.controller;

import com.azhen.myproject.dto.AccessTokenDTO;
import com.azhen.myproject.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class authorizecontroller {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        final AccessTokenDTO a = new AccessTokenDTO();
        a.setClient_id("0046f7fbb16aed70ba5a");
        a.setClient_secert("93982fa0cfc755eaf93a7b11c93c92ed839c1082");
        a.setCode(code);
        a.setRedirect_uri("http://localhost:8080/callback");
        a.setState(state);
        githubProvider.getaccesstoken(a);
        return "index";
    }
}
