package com.example.user.demoredis;

public interface MessagePublisher {

    void publish(final String message);
}