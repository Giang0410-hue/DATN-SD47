package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.entity.HoaDon;
import com.example.bedatnsd47.service.HoaDonChiTietService;
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

     @Autowired
     HoaDonChiTietService hoaDonChiTietService;

    


    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("lstHoaDon",hoaDonService.findAll());
        model.addAttribute("lstHdct",hoaDonChiTietService.findByIdHoaDon(36l));
        hoaDonService.findAll().forEach(o->{
            System.out.println(o.getLstHoaDonChiTiet().toString());;

        });
        return "/admin-template/ban-hang-admin.html";
    }

    @PostMapping("/create")
    public String taoHoaDon(){
        HoaDon hd = new HoaDon();
        Integer size = hoaDonService.findAll().size()+1;
        hd.setMaHoaDon("HD"+size);
        hoaDonService.saveOrUpdate(hd);
        return "redirect:home";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        hoaDonService.deleteById(id);
        return "redirect:home";
    }

}
