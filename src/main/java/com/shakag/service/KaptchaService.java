package com.shakag.service;

import com.shakag.common.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface KaptchaService {
    public Result getKaptcha(HttpServletResponse response) throws IOException;
}
