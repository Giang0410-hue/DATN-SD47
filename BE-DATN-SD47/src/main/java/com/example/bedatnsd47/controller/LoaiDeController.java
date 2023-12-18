package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.config.PrincipalCustom;
import com.example.bedatnsd47.config.UserInfoUserDetails;
import com.example.bedatnsd47.entity.LoaiDe;
import com.example.bedatnsd47.service.LoaiDeService;
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
@RequestMapping("/admin/loai-de")
public class LoaiDeController {

    @Autowired
    LoaiDeService loaiDeService;

    private PrincipalCustom principalCustom = new PrincipalCustom();

    @GetMapping("")
    public String hienThi(
            Model model
    ) {
        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
        if (name != null) {
            model.addAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
        } else {
            return "redirect:/login";
        }
        model.addAttribute("listLoaiDe", loaiDeService.findAll());
        model.addAttribute("loaiDe", new LoaiDe());
        return "/admin-template/loai_de/loai-de";
    }

    @GetMapping("/dang-hoat-dong")
    public String hienThiDangHoatDong(
            Model model
    ) {
        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
        if (name != null) {
            model.addAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
        } else {
            return "redirect:/login";
        }
        model.addAttribute("listLoaiDe", loaiDeService.getAllDangHoatDong());
        model.addAttribute("loaiDe", new LoaiDe());
        return "/admin-template/loai_de/loai-de";
    }

    @GetMapping("/ngung-hoat-dong")
    public String hienThiNgungHoatDong(
            Model model
    ) {
        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
        if (name != null) {
            model.addAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
        } else {
            return "redirect:/login";
        }
        model.addAttribute("listLoaiDe", loaiDeService.getAllNgungHoatDong());
        model.addAttribute("loaiDe", new LoaiDe());
        return "/admin-template/loai_de/loai-de";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(
            Model model,
            @PathVariable("id") Long id
    ) {
        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
        if (name != null) {
            model.addAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
        } else {
            return "redirect:/login";
        }
        LoaiDe loaiDe = loaiDeService.getById(id);
        model.addAttribute("loaiDe", loaiDe);
        return "/admin-template/loai_de/sua-loai-de";
    }

    @PostMapping("/update")
    public String update(@Valid
                         @ModelAttribute("loaiDe") LoaiDe loaiDe,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirectAttributes
    ) {

        if (result.hasErrors()) {
            model.addAttribute("checkThongBao", "thaiBai");
            return "/admin-template/loai_de/sua-loai-de";
        } else if (!loaiDeService.checkTenTrungSua(loaiDe.getId(), loaiDe.getTen())) {
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Loại đế đã tồn tại");
            return "/admin-template/loai_de/sua-loai-de";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            LoaiDe ld = loaiDeService.getById(loaiDe.getId());
            loaiDe.setNgayTao(ld.getNgayTao());
            loaiDe.setNgaySua(new Date());
            loaiDeService.update(loaiDe);
            return "redirect:/admin/loai-de";
        }

    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("loaiDe") LoaiDe loaiDe,
                      BindingResult result,
                      Model model,
                      RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listLoaiDe", loaiDeService.findAll());
            return "/admin-template/loai_de/loai-de";
        } else if (!loaiDeService.checkTenTrung(loaiDe.getTen())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Loại đế đã tồn tại");
            model.addAttribute("listLoaiDe", loaiDeService.findAll());
            return "/admin-template/loai_de/loai-de";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            loaiDe.setNgayTao(new Date());
            loaiDe.setNgaySua(new Date());
            loaiDe.setTrangThai(0);
            loaiDeService.save(loaiDe);
            return "redirect:/admin/loai-de";
        }

    }
}
