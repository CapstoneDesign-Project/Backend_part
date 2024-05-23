package com.example.una.kakaoLogin.controller;

import com.example.una.kakaoLogin.service.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {

    private final KakaoService kakaoService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index(){
        return "redirect:" + kakaoService.getKakaoLogin();
    }
}
