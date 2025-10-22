package com.springdemo.API1.Services;

import com.springdemo.API1.Domain.User;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

    public String helloWorld(String name){
        return "Hello world " + name;
    }

    public String helloWorldPost(User body){
        return "Hello world " + body.getName();
    }

    public String goodbyeWorld(String name){
        return "Goodbye world " + name;
    }
}
