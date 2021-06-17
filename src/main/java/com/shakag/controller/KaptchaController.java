package com.shakag.controller;

import com.shakag.common.Result;
import com.shakag.service.KaptchaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/kaptcha")
public class KaptchaController {

    @Resource
    KaptchaService kaptchaService;

    @GetMapping("")
    public Result getKaptcha(HttpServletResponse response) throws IOException {
        return kaptchaService.getKaptcha(response);
    }
}
