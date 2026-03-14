package com.patterns.dp_springboot.factory.exception;

public class UnsupportedChannelException extends RuntimeException {

    public UnsupportedChannelException(String channel) {
        super("No notification sender registered for channel: " + channel);
    }
}
