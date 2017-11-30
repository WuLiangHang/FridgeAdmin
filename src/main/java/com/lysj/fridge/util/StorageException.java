package com.lysj.fridge.util;

/**
 * Created by maohang on 2017/11/30.
 */
public class StorageException extends RuntimeException{
    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
