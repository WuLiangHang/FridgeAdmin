package com.lysj.fridge.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by maohang on 2017/11/30.
 */
@ConfigurationProperties("storage")
public class StorageProperties {
    /**
     * Folder location for storing files
     */
    private String location = "upload-dir";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
