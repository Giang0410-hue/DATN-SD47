package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.entity.ThuongHieu;
import com.example.bedatnsd47.service.ThuongHieuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;


@Controller
@RequestMapping("/admin/thuong-hieu")
public class ThuongHieuController {

    @Autowired
    private ThuongHieuService thuongHieuService;

    private Date currentDate = new Date();

    @GetMapping("")
    public String hienThi(
            Model model
    ) {
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("thuongHieu", new ThuongHieu());
        return "/admin-template/thuong_hieu/thuong-hieu";
    }

    @GetMapping("/dang-hoat-dong")
    public String hienThiDangHoatDong(
            Model model
    ) {
        model.addAttribute("listThuongHieu", thuongHieuService.getAllDangHoatDong());
        model.addAttribute("thuongHieu", new ThuongHieu());
        return "/admin-template/thuong_hieu/thuong-hieu";
    }

    @GetMapping("/ngung-hoat-dong")
    public String hienThiNgungHoatDong(
            Model model
    ) {
        model.addAttribute("listThuongHieu", thuongHieuService.getAllNgungHoatDong());
        model.addAttribute("thuongHieu", new ThuongHieu());
        return "/admin-template/thuong_hieu/thuong-hieu";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(
            Model model,
            @PathVariable("id") Long id
    ) {
        ThuongHieu thuongHieu = thuongHieuService.getById(id);
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("thuongHieu", thuongHieu);
        return "/admin-template/thuong_hieu/sua-thuong-hieu";
    }

    @PostMapping("/update")
    public String update(@Valid
                         @ModelAttribute("thuongHieu") ThuongHieu thuongHieu,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            model.addAttribute("checkThongBao", "thaiBai");
            return "/admin-template/thuong_hieu/sua-thuong-hieu";
        } else if (!thuongHieuService.checkTenTrungSua(thuongHieu.getId(), thuongHieu.getTen())) {
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Thương hiệu đã tồn tại");
            return "/admin-template/thuong_hieu/sua-thuong-hieu";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            ThuongHieu th = thuongHieuService.getById(thuongHieu.getId());
            thuongHieu.setNgayTao(th.getNgayTao());
            thuongHieu.setNgaySua(currentDate);
            thuongHieuService.update(thuongHieu);
            return "redirect:/admin/thuong-hieu";
        }
    }

    @PostMapping("/add")
    public String add(@Valid
                      @ModelAttribute("thuongHieu") ThuongHieu thuongHieu,
                      BindingResult result,
                      Model model,
                      RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            return "/admin-template/thuong_hieu/thuong-hieu";
        } else if (!thuongHieuService.checkTenTrung(thuongHieu.getTen())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Thương hiệu đã tồn tại");
            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            return "/admin-template/thuong_hieu/thuong-hieu";
        }
        else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            thuongHieu.setNgayTao(currentDate);
            thuongHieu.setNgaySua(currentDate);
            thuongHieu.setTrangThai(0);
            thuongHieuService.save(thuongHieu);
            return "redirect:/admin/thuong-hieu";
        }
    }


}
