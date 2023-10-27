package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.service.ChiTietSanPhamSerivce;
import com.example.bedatnsd47.service.KichThuocService;
import com.example.bedatnsd47.service.LoaiDeService;
import com.example.bedatnsd47.service.MauSacService;
import com.example.bedatnsd47.service.SanPhamSerivce;
import com.example.bedatnsd47.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/san-pham-chi-tiet")
public class ChiTietSanPhamController {

    @Autowired
    private ChiTietSanPhamSerivce chiTietSanPhamSerivce;

    @Autowired
    private SanPhamSerivce sanPhamSerivce;

    @Autowired
    private ThuongHieuService thuongHieuService;

    @Autowired
    private KichThuocService kichThuocService;

    @Autowired
    private MauSacService mauSacService;

    @Autowired
    private LoaiDeService loaiDeService;


    private Integer pageNo = 0;

    @GetMapping()
    public String hienThi(
        Model model
    ){
        model.addAttribute("listChiTietSP",chiTietSanPhamSerivce.getPage(pageNo).stream().toList());
        model.addAttribute("listSanPham",sanPhamSerivce.getAll());
        model.addAttribute("listThuongHieu",thuongHieuService.findAll());
        model.addAttribute("listKichCo",kichThuocService.findAll());
        model.addAttribute("listMauSac",mauSacService.findAll());
        model.addAttribute("listLoaiDe",loaiDeService.findAll());
        model.addAttribute("currentPage",pageNo);
        return "/admin-template/san_pham_chi_tiet/san-pham-chi-tiet";
    }

}
