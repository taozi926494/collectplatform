package com.collectplatform.project.exception;

/**
 * @Author fuqiang
 * @Date 2021/4/14
 */
public class ScriptFileException extends RuntimeException{
    public ScriptFileException(String message) {
        super(message);
    }

    public ScriptFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
