package com.rograndec.feijiayun.chain.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by ST on 2017/8/24.
 */
@ConfigurationProperties(prefix="chain_yml") //application.yml中的myProps下的属性
public class YmlConfiguration {
    private String webUploadPath;

    public String getWebUploadPath() {
        return webUploadPath;
    }

    public void setWebUploadPath(String webUploadPath) {
        this.webUploadPath = webUploadPath;
    }
}