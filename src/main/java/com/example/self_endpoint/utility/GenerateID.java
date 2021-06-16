package com.example.self_endpoint.utility;

import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class GenerateID {
    public String generateUserId(){
        return UUID.randomUUID().toString();
    }
}
