package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.entity.SanPham;
import com.example.bedatnsd47.service.SanPhamSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/san-pham")
public class SanPhamController {

    @Autowired
    private SanPhamSerivce sanPhamSerivce;

    private Integer pageNo = 0;

    @GetMapping()
    public String hienThi(Model model){
        model.addAttribute("listSanPham",sanPhamSerivce.getPage(pageNo).stream().toList());
        return "/admin-template/san_pham/san-pham";
    }

    @GetMapping("/view-update/{id}")
    public String update(
            Model model,
            @PathVariable("id")Long id
    ){
//        SanPham sanPham = sanPhamSerivce.getById(id);
//        model.addAttribute("detailSanPham",sanPham);
        return "/admin-template/san_pham/sua-san-pham";
    }

}
