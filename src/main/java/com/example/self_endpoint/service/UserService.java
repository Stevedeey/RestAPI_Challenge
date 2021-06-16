package com.example.self_endpoint.service;

import com.example.self_endpoint.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.self_endpoint.request_response.*;

import java.util.Map;


public interface UserService {
    Person createPerson(Person person);
    Person getUserById(String userId);
    Map<String, Person> getPersonMap();
    Person updateUser(Person person, String userId);
}
