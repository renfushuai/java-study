package com.rfs.exceptions;

public class MQSendFailException extends RuntimeException {
    private String topic;
    private String keys;
    private String content;

    public MQSendFailException(String topic, String keys, String content) {
        this.topic = topic;
        this.keys = keys;
        this.content = content;
    }

    public MQSendFailException(String message, String topic, String keys, String content) {
        super(message);
        this.topic = topic;
        this.keys = keys;
        this.content = content;
    }

    public MQSendFailException(String message, Throwable cause, String topic, String keys, String content) {
        super(message, cause);
        this.topic = topic;
        this.keys = keys;
        this.content = content;
    }

    public String getTopic() {
        return this.topic;
    }

    public String getKeys() {
        return this.keys;
    }

    public String getContent() {
        return this.content;
    }
}
