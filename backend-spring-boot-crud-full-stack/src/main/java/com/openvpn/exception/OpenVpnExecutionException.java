package com.openvpn.exception;

public class OpenVpnExecutionException extends Exception {
    public OpenVpnExecutionException() {
    }

    public OpenVpnExecutionException(String message) {
        super(message);
    }

    public OpenVpnExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public OpenVpnExecutionException(Throwable cause) {
        super(cause);
    }

    public OpenVpnExecutionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
