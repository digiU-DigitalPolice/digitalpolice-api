package com.example.controller;

import com.example.entity.Crime;
import com.example.entity.Filter;
import com.example.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    @RequestMapping("/")
    public User hello(){
        return new User("username");
    }

    @RequestMapping("/map")
    public List<Crime> map(Filter filter){
        return new ArrayList<>();
    }
}
