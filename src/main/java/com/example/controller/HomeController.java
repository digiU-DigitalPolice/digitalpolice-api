package com.example.controller;

import com.example.entity.Crime;
import com.example.entity.Filter;
import com.example.entity.User;
import com.example.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    private MapService mapService;

    @Autowired
    public HomeController(MapService mapService) {
        this.mapService = mapService;
    }

    @RequestMapping("/")
    public User hello(){
        return new User("username");
    }

    @RequestMapping("/map")
    public List<Crime> map(Filter filter){
        return new ArrayList<>();
    }
}
