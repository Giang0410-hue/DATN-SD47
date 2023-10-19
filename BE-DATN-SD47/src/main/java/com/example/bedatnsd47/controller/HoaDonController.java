package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/hoa-don")
public class HoaDonController {

    @Autowired
    HoaDonService hoaDonService;

    @RequestMapping("/hoa-don/index")
    public String index(){
        System.out.println("check");
        return "/test/abc";
    }
//    @PostMapping("/add")
//    public String add(){
//        return "list.html";
//    }
}
