package com.example.self_endpoint.controller;

import com.example.self_endpoint.model.Person;
import com.example.self_endpoint.service.UserService;
import com.example.self_endpoint.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.self_endpoint.request_response.*;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/api/")
public class PersonController {

    private UserService userService;

    @Autowired
    public PersonController(UserService userService) {
        this.userService = userService;
    }

    UserServiceImpl userServiceimp = new UserServiceImpl();


    private Map<String, Person> map2 = userServiceimp.getPersonMap();


    @GetMapping("/all")
    public ResponseEntity<Map<String, Person>> getUsers() {
        System.out.println(userService.getPersonMap());
        return new ResponseEntity<>(userService.getPersonMap(), HttpStatus.OK);
    }


    @PostMapping(path = "/create",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            },
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public ResponseEntity<Person> creatUser(@RequestBody Person person) {
        Person user = userService.createPerson(person);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(path = "/get/{userId}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            })
    public ResponseEntity<?> getUserById(@PathVariable String userId) {
        Person person = userService.getUserById(userId);
        if (person != null) {
            return new ResponseEntity<>(person, HttpStatus.OK);
        }
        return new ResponseEntity<>(" No user with the Id " + userId + " exist!! ", HttpStatus.NO_CONTENT);
    }


    @PutMapping(path = "/editUser/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @RequestBody Person person) {

        Person user = userService.updateUser(person, userId);

        if (user == null)
            return new ResponseEntity<>("User does not exist!", HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable String id) {
        userService.getPersonMap().remove(id);

        return ResponseEntity.noContent().build();
    }
}
