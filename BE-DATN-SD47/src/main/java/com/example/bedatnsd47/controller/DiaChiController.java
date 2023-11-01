package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.entity.DiaChi;
import com.example.bedatnsd47.entity.TaiKhoan;
import com.example.bedatnsd47.service.DiaChiService;
import com.example.bedatnsd47.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/admin/dia-chi")
public class DiaChiController {
    @Autowired
    KhachHangService taiKhoanService;

    @Autowired
    DiaChiService diaChiService;

    private Integer pageNo = 0;

    private Integer trangThai = 1;

    private Date currentDate = new Date();


    @GetMapping("/view-update/{id}/{idDiaChi}")
    public String viewUpdateAndDiaChi(Model model, @PathVariable("id") Long id, @PathVariable("idDiaChi") Long idDiaCHi) {
        DiaChi diaChi = diaChiService.getById(id);
        List<DiaChi> listDiaChi = diaChiService.getAllByTaiKhoan(id);
        TaiKhoan taiKhoan = taiKhoanService.getById(id);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("listKhachHang", taiKhoanService.getAll());
        model.addAttribute("khachHang", taiKhoan);
        model.addAttribute("diaChi", diaChi);
        return "/admin-template/khach_hang/sua-khach-hang";
    }
}
