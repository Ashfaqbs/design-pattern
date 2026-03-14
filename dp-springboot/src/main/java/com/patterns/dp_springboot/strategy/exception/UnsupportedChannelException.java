package com.patterns.dp_springboot.strategy.exception;

public class UnsupportedChannelException extends RuntimeException {

    public UnsupportedChannelException(String channel) {
        super("No sender registered for channel: " + channel);
    }
}
