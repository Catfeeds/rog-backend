package com.rograndec.feijiayun.chain.common.vo;

import java.awt.image.BufferedImage;

public class VerificationCodeVO {

    /**
     * 绘制的验证码图片
     */
    private BufferedImage image;

    /**
     *验证码图片中的数字,用于校验
     */
    private String number;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
