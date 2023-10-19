package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.entity.Voucher;
import com.example.bedatnsd47.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/voucher")

public class VoucherController {
    @Autowired
    VoucherService voucherService;

    @GetMapping("/list")
    public String index(Model model){
        model.addAttribute("lst",voucherService.findAll());
        return "/voucher.html";
    }

    @PostMapping("/add")
    public String add(@RequestBody @ModelAttribute("aVoucher") Voucher voucher){
        voucherService.saveOrUpdate(voucher);
        return "redirect:/list";
    }
}
