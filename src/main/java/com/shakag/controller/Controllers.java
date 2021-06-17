package com.shakag.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.concurrent.TimeUnit;

@RestController
public class Controllers {

}
