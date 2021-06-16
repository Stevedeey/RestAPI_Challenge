package com.example.self_endpoint.utility;

import java.util.UUID;

public class GenerateID {
    public String generateUserId(){
        return UUID.randomUUID().toString();
    }
}
