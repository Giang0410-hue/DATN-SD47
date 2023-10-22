package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.entity.HoaDon;
import com.example.bedatnsd47.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ban-hang-tai-quay")
public class BanHangController {

    @Autowired
    HoaDonService hoaDonService;

    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("lstHoaDon",hoaDonService.findAll());
        return "/admin-template/ban-hang-admin.html";
    }

    @PostMapping("/create")
    public String taoHoaDon(){
        HoaDon hd = new HoaDon();
        hoaDonService.saveOrUpdate(hd);
        return "redirect:home";
    }

}
