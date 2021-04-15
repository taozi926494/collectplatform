package com.collectplatform.project.exception;

/**
 * @Author fuqiang
 * @Date 2021/4/14
 */
public class FileException extends RuntimeException{
    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
}
