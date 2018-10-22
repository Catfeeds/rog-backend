package com.rograndec.feijiayun.chain.utils;


import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 * Created by ST on 2017/8/23.
 */
public class FileUtils {

    public static String getFileName(Part part){
        if(part == null) return null;

        String fileName = part.getHeader("content-disposition");
        if(StringUtils.isBlank(fileName)){
            return null;
        }
        return StringUtils.substringBetween(fileName, "filename=\"", "\"");
    }


    public static String processFileName(HttpServletRequest request, String fileNames) {
        String codedFileName = null;
        try {
            String agent = request.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie

                String name = java.net.URLEncoder.encode(fileNames, "UTF8");

                codedFileName = name;
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,chrome等
                codedFileName = new String(fileNames.getBytes("UTF-8"), "iso-8859-1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return codedFileName;
    }
}