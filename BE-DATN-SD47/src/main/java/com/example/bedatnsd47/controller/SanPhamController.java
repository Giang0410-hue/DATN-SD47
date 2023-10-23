package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.service.SanPhamSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/san-pham/")
public class SanPhamController {

    @Autowired
    private SanPhamSerivce sanPhamSerivce;

    @GetMapping("hien-thi")
    public String hienThi(Model model){
        model.addAttribute("listSanPham",sanPhamSerivce.getAll());
        return "/admin-template/san_pham/san-pham";
    }

    @GetMapping("update")
    public String update(Model model){
        model.addAttribute("listSanPham",sanPhamSerivce.getAll());
        return "/admin-template/san_pham/sua-san-pham";
    }

}
