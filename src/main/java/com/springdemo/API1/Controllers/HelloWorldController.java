package com.springdemo.API1.Controllers;

import com.springdemo.API1.Domain.User;
import com.springdemo.API1.Services.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello-world")
public class HelloWorldController {

    @Autowired
    private HelloWorldService helloWorldService;

    @GetMapping
    public String helloWorld(){
        return helloWorldService.helloWorld("Mariana");
    }

    @PostMapping("")
    public String helloWorldPost(@RequestBody User body){
        return helloWorldService.helloWorldPost(body);
    }

    @GetMapping("/goodbye")
    public String goodbye(){
        return helloWorldService.goodbyeWorld("Mariane");
    }
}
