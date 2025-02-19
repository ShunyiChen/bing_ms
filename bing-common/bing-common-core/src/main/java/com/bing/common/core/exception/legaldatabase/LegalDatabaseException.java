package com.bing.common.core.exception.legaldatabase;

public class LegalDatabaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public LegalDatabaseException(String message) {
        super(message);
        // 清除堆栈跟踪信息
        this.setStackTrace(new StackTraceElement[0]);
    }
}