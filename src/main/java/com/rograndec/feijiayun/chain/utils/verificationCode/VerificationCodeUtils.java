package com.rograndec.feijiayun.chain.utils.verificationCode;

import com.rograndec.feijiayun.chain.common.vo.VerificationCodeVO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class VerificationCodeUtils {

    /**
     * 绘制验证码图片
     * @return
     */
    public static VerificationCodeVO drawVerificationCode(){

        //0.创建空白图片
        BufferedImage image = new BufferedImage(100,30,BufferedImage.TYPE_INT_RGB);
        //1.获取图片画笔
        Graphics g = image.getGraphics();
        Random r = new Random();
        //2.设置画笔颜色
        g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
        //3.绘制矩形的背景
        g.fillRect(0, 0, 100, 30);
        //4.调用自定义的方法，获取长度为5的字母数字组合的字符串
        String number = getNumber(5);

        g.setColor(new Color(0,0,0));
        g.setFont(new Font(null,Font.BOLD,24));
        //5.设置颜色字体后，绘制字符串
        g.drawString(number, 5, 25);
        //6.绘制8条干扰线
        for(int i=0;i<8;i++){
            g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255),r.nextInt(255)));
            g.drawLine(r.nextInt(100), r.nextInt(30), r.nextInt(100), r.nextInt(30));
        }

        VerificationCodeVO scode = new VerificationCodeVO();
        scode.setImage(image);
        scode.setNumber(number);
        return scode;
    }

    private static String getNumber(int size){
        String str = "qwertyuioplkjhgfdsazxcvbnmABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String number = "";
        Random r = new Random();
        for(int i=0;i<size;i++){
            number+=str.charAt(r.nextInt(str.length()));
        }
        return number;
    }
}
