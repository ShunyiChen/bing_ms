package com.bing.common.core.exception.filingsystem;

public class FilingSystemException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public FilingSystemException(String message) {
        super(message);
        // 清除堆栈跟踪信息
        this.setStackTrace(new StackTraceElement[0]);
    }
}
