package com.together.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class VerificationService {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    private int w = 70;
    private int h = 35;
    private Random r = new Random();
    // {"宋体", "华文楷体", "黑体", "华文新魏", "华文隶书", "微软雅黑", "楷体_GB2312"}
    private String[] fontNames =
            { "宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312" };

    // 背景色
    private Color bgColor = new Color(255, 255, 255);
    private String  pre = "data:image/jpg;base64,";

//    @Override
    public Color randomColor() {
        int red = r.nextInt(150);
        int green = r.nextInt(150);
        int blue = r.nextInt(150);
        return new Color(red, green, blue);
    }

//    @Override
    public Font randomFont() {
        int index = r.nextInt(fontNames.length);
        String fontName = fontNames[index];// 生成随机的字体名称
        int style = r.nextInt(4);// 生成随机的样式, 0(无样式), 1(粗体), 2(斜体), 3(粗体+斜体)
        int size = r.nextInt(5) + 24; // 生成随机字号, 24 ~ 28
        return new Font(fontName, style, size);

    }

//    @Override
    public void drawLine(BufferedImage image) {
        int num = 3;// 一共画3条
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        for (int i = 0; i < num; i++) {// 生成两个点的坐标，即4个值
            int x1 = r.nextInt(w);
            int y1 = r.nextInt(h);
            int x2 = r.nextInt(w);
            int y2 = r.nextInt(h);
            g2.setStroke(new BasicStroke(1.5F));
            g2.setColor(Color.BLUE); // 干扰线是蓝色
            g2.drawLine(x1, y1, x2, y2);// 画线
        }

    }

//    @Override
    public BufferedImage createImage() {
        BufferedImage image = new BufferedImage(w, h,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        g2.setColor(this.bgColor);
        g2.fillRect(0, 0, w, h);
        return image;

    }

//    @Override
    public String getImage(String userId,String str) {
        redisTemplate.opsForValue().set(userId,str,60*10, TimeUnit.SECONDS);
        StringBuilder sb = new StringBuilder();
        BufferedImage image = createImage();// 创建图片缓冲区
        Graphics2D g2 = (Graphics2D) image.getGraphics();// 得到绘制环境

        // 向图片中画4个字符
        for (int i = 0; i < 4; i++) {// 循环四次，每次生成一个字符
            String  s = str.substring(i,i+1);
            float x = i * 1.0F * w / 4; // 设置当前字符的x轴坐标
            g2.setFont(randomFont()); // 设置随机字体
            g2.setColor(randomColor()); // 设置随机颜色
            g2.drawString(s, x, h - 5); // 画图
        }

        drawLine(image); // 添加干扰线

        ByteArrayOutputStream baos = new ByteArrayOutputStream();//io流
        try {
            ImageIO.write(image, "png", baos);//写入流中
        } catch(IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = baos.toByteArray();//转换成字节
        Encoder encoder = Base64.getEncoder();
        String png_base64 = encoder.encodeToString(bytes).trim();//转换成base64串
        png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
        sb.append(pre);
        sb.append(png_base64);
        String result = sb.toString();
        return result;
    }

    public String getCode(String userId){
        return String.valueOf(redisTemplate.opsForValue().get(userId));
    }

    public void delKey(String userId){
        redisTemplate.delete(userId);
    }

    //    @Override
    public char randomChar() {
        // 可选字符
        String codes = "23456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
        int index = r.nextInt(codes.length());
        return codes.charAt(index);
    }

    //    @Override
    @Cacheable(value = "verification",key = "#root.methodName+':'+#userId")
    public String getCode() {
        StringBuilder sb = new StringBuilder();// 用来装载生成的验证码文本
        for (int i = 0; i < 4; i++) {// 循环四次，每次生成一个字符
            String s = randomChar() + "";// 随机生成一个字母
            sb.append(s);
        };
        return sb.toString();
    }
}

