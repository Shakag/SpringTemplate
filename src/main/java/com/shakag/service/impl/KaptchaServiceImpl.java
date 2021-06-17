package com.shakag.service.impl;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.shakag.common.Result;
import com.shakag.service.KaptchaService;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class KaptchaServiceImpl implements KaptchaService {

    @Resource
    DefaultKaptcha defaultKaptcha;

    @Resource
    RedisTemplate redisTemplate;

    @Override
    public Result getKaptcha(HttpServletResponse response) throws IOException {
        response.setContentType("image/jpg;charset=UTF-8");
        response.setHeader("Content-Disposition", "inline;fileName=validateCode.jpg");

        String text = defaultKaptcha.createText();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.opsForValue().set(uuid,text,3, TimeUnit.MINUTES);
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("uuid",uuid);
        BufferedImage image = defaultKaptcha.createImage(text);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image,"jpg",baos);
        String base64Image = Base64.encode(baos.toByteArray());
        return new Result(200,"data:image/png;base64,"+base64Image,hashMap);

    }
}
