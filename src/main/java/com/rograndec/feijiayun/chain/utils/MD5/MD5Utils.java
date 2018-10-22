package com.rograndec.feijiayun.chain.utils.MD5;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * Created by zhaiwei on 2017/8/31.
 */
public class MD5Utils {

    /**
     * 16进制字符集
     */
    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    /**
     * 指定算法为MD5的MessageDigest
     */
    private static MessageDigest messageDigest = null;

    /**
     * 初始化messageDigest的加密算法为MD5
     */
    static {
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文件的MD5值
     *
     * @param file
     *            目标文件
     * @return MD5字符串
     */
    public static String getFileMD5String(File file) throws IOException {
        String ret = "";
        FileInputStream in = null;
        FileChannel ch = null;

        try {
            in = new FileInputStream(file);
            ch = in.getChannel();
            ByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            messageDigest.update(byteBuffer);
            ret = bytesToHex(messageDigest.digest());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            in.close();
            ch.close();
        }

        return ret;
    }

    /**
     *
     * 获取文件的MD5值
     *
     * @param fileName
     *            目标文件的完整名称
     * @return MD5字符串
     */
    public static String getFileMD5String(String fileName) throws IOException {
        return getFileMD5String(new File(fileName));
    }

    /**
     *
     * MD5加密字符串
     *
     * @param str
     *            目标字符串
     * @return MD5加密后的字符串
     */
    public static String getMD5String(String str) {
        return getMD5String(str.getBytes());
    }

    /**
     *
     * MD5加密以byte数组表示的字符串
     *
     * @param bytes
     *            目标byte数组
     * @return MD5加密后的字符串
     */
    public static String getMD5String(byte[] bytes) {
        messageDigest.update(bytes);
        return bytesToHex(messageDigest.digest());
    }

    /**
     *
     * 校验密码与其MD5是否一致
     *
     * @param pwd
     *            密码字符串
     * @param md5
     *            基准MD5值
     * @return 检验结果
     */
    public static boolean checkPassword(String pwd, String md5) {
        return getMD5String(pwd).equalsIgnoreCase(md5);
    }

    /**
     *
     * 校验密码与其MD5是否一致
     *
     * @param pwd
     *            以字符数组表示的密码
     * @param md5
     *            基准MD5值
     * @return 检验结果
     */
    public static boolean checkPassword(char[] pwd, String md5) {
        return checkPassword(new String(pwd), md5);
    }

    /**
     *
     * 检验文件的MD5值
     *
     * @param file
     *            目标文件
     *
     * @param md5
     *            基准MD5值
     *
     * @return 检验结果
     *
     */
    public static boolean checkFileMD5(File file, String md5) throws IOException {
        return getFileMD5String(file).equalsIgnoreCase(md5);
    }

    /**
     *
     * 检验文件的MD5值
     *
     * @param fileName
     *            目标文件的完整名称
     *
     * @param md5
     *            基准MD5值
     *
     * @return 检验结果
     *
     */
    public static boolean checkFileMD5(String fileName, String md5) throws IOException {
        return checkFileMD5(new File(fileName), md5);
    }

    /**
     *
     * 将字节数组转换成16进制字符串
     *
     * @param bytes
     *            目标字节数组
     *
     * @return 转换结果
     *
     */
    public static String bytesToHex(byte bytes[]) {
        return bytesToHex(bytes, 0, bytes.length);
    }

    /**
     *
     * 将字节数组中指定区间的子数组转换成16进制字符串
     *
     * @param bytes
     *            目标字节数组
     *
     * @param start
     *            起始位置（包括该位置）
     *
     * @param end
     *            结束位置（不包括该位置）
     * @return 转换结果
     *
     */
    public static String bytesToHex(byte bytes[], int start, int end) {
        StringBuilder sb = new StringBuilder();

        for (int i = start; i < start + end; i++) {
            sb.append(byteToHex(bytes[i]));
        }

        return sb.toString();
    }

    /**
     * 双层加密的MD5值
     * @param strSrc
     */
    public static String getSimenMD5(String strSrc) {
        String str1 = getMD5String(strSrc).toLowerCase();

        StringBuffer str2 = new StringBuffer("");
        str2.append(str1.substring(0, 2)).append("|")
                .append(str1.substring(4, 10)).append("|")
                .append(str1.substring(8, 21)).append("|")
                .append(str1.substring(14, 19)).append("|")
                .append(str1.substring(1, 9)).append("|")
                .append(str1.substring(24, 27));

        return getMD5String(str2.toString());
    }

    /**
     * 将单个字节码转换成16进制字符串
     * @param bt
     *            目标字节
     * @return 转换结果
     */
    public static String byteToHex(byte bt) {
        return HEX_DIGITS[(bt & 0xf0) >> 4] + "" + HEX_DIGITS[bt & 0xf];
    }

    public static void main(String[] args) {
        String password = "feijiayun123456";
        String md5Password = getMD5String(password);
        System.out.println(md5Password);

        boolean isCheck = checkPassword(password,md5Password);
        System.out.println(isCheck);
    }


    public static boolean checkMd5Base64(Map<String,Object> paramMap,String token) throws UnsupportedEncodingException {
        JSONObject o = (JSONObject) JSONObject.toJSON(paramMap);
        String md5String = MD5Utils.getMD5String(o.toJSONString());
        byte[] encode = Base64.encode(md5String);
        String encodeStr = new String(encode,Base64.DEFAULT_ENCODING);
        return token.equalsIgnoreCase(encodeStr);
    }

    public static boolean checkMd5Base64(Object object,String token) throws UnsupportedEncodingException {
        JSONObject o = (JSONObject) JSONObject.toJSON(object);
        String s = JSON.toJSONString(o,SerializerFeature.WriteDateUseDateFormat);
        System.out.println(s);
        String md5String = MD5Utils.getMD5String(s);
        byte[] encode = Base64.encode(md5String);
        String encodeStr = new String(encode,Base64.DEFAULT_ENCODING);
        return token.equalsIgnoreCase(encodeStr);
    }
}
