package com.example.self_endpoint.service;

import com.example.self_endpoint.model.Person;
import com.example.self_endpoint.utility.GenerateID;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

     Map<String, Person> personMap;

    public  Map<String, Person> getPersonMap() {
        return personMap;
    }

    //constructor-based injection
    GenerateID generateIDutils;

    public UserServiceImpl() {
    }

    public UserServiceImpl(GenerateID generateIDutils) {
        this.generateIDutils = generateIDutils;
    }

    @Override
    public Person createPerson(Person person) {
        String userId = generateIDutils.generateUserId();
        Person user = new Person();
        user.setFullname(person.getFullname());
        user.setAge(person.getAge());
        user.setEmail(person.getEmail());
        user.setPassword(person.getPassword());

        //set the userId with the generated id

        user.setUserId(userId);

        if(personMap==null) personMap = new HashMap<>();

        personMap.put(userId, user);

        System.out.println(personMap);

        return user;
    }

    public Person getUserById(String userId){
        Person person;
        if(!personMap.containsKey(userId)){
            return  null;
        }
        person = personMap.get(userId);
        return  person;
    }

    public Person updateUser(Person person, String userId){
        Person user = personMap.get(userId);
        if (user != null) {

            user.setFullname(person.getFullname());
            user.setEmail(person.getEmail());
            user.setPassword(person.getPassword());
            user.setAge(person.getAge());
            personMap.put(userId, user);

            return user;
        }
        return null;
    }
}
