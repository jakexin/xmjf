package com.shsxt.xmjf.web.controller;

import com.google.code.kaptcha.Producer;
import com.shsxt.xmjf.api.constant.P2pConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 图形验证码生成
 */
@Controller
public class ImageController {

    @Autowired
    private Producer producer;

    @RequestMapping("/getKaptchaCode")
    public void getKaptchaCode(HttpServletRequest request, HttpServletResponse response) {
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");

        // -----------------生成验证码----------begin--------------
        String text = producer.createText();
        System.out.println("验证码：" + text);

        // 将验证码存入session
        request.getSession().setAttribute(P2pConstant.PICTURE_VERIFY_KEY, text);

        ServletOutputStream out = null;
        try {
            // 将验证码生成为图形验证码
            BufferedImage bi = producer.createImage(text);
            out = response.getOutputStream();

            ImageIO.write(bi, "jpg", out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // -----------------生成验证码----------end--------------
    }

}
