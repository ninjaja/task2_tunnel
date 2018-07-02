package com.matrizaev.task_tunnel.exception;

public class TunnelEnteringException extends Exception {

    public TunnelEnteringException() {
        super();
    }

    public TunnelEnteringException(String message) {
        super(message);
    }

    public TunnelEnteringException(String message, Throwable cause) {
        super(message, cause);
    }

    public TunnelEnteringException(Throwable cause) {
        super(cause);
    }
}
