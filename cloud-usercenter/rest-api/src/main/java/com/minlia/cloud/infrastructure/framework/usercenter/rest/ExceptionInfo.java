package com.minlia.cloud.infrastructure.framework.usercenter.rest;


public class ExceptionInfo {

    private final String message;
    private final int status;
    private final String path;
    private long timestamp;

    public ExceptionInfo(String message, int status, String path) {
        this.message = message;
        this.status = status;
        this.path = path;
        this.timestamp = System.currentTimeMillis();
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getPath() {
        return path;
    }
}
