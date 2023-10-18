package com.example.bedatnsd47.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HoaDonCtrl {
    @GetMapping("/index1")
    public String index(){
        System.out.println("23");
        return "index.html";
    }
}
