package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.config.PrincipalCustom;
import com.example.bedatnsd47.config.UserInfoUserDetails;
import com.example.bedatnsd47.entity.KichCo;
import com.example.bedatnsd47.service.KichCoService;
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
@RequestMapping("/admin/kich-co")
public class KichCoController {

    @Autowired
    private KichCoService kichCoService;

    private PrincipalCustom principalCustom = new PrincipalCustom();

    @GetMapping()
    public String hienThi(
            Model model
    ) {
        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
        if (name != null) {
            model.addAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
        } else {
            return "redirect:/login";
        }
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("kichCo", new KichCo());
        return "/admin-template/kich_co/kich-co";
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
        model.addAttribute("listKichCo", kichCoService.getAllDangHoatDong());
        model.addAttribute("kichCo", new KichCo());
        return "/admin-template/kich_co/kich-co";
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
        model.addAttribute("listKichCo", kichCoService.getAllNgungHoatDong());
        model.addAttribute("kichCo", new KichCo());
        return "/admin-template/kich_co/kich-co";
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
        KichCo kichCo = kichCoService.getById(id);
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("kichCo", kichCo);
        return "/admin-template/kich_co/sua-kich-co";
    }

    @PostMapping("/update")
    public String update(@Valid
                         @ModelAttribute("kichCo") KichCo kichCo,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listKichCo", kichCoService.findAll());
            return "/admin-template/kich_co/sua-kich-co";
        } else if (!kichCoService.checkTenTrungSua(kichCo.getId(), kichCo.getTen())) {
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Kích cỡ đã tồn tại");
            model.addAttribute("listKichCo", kichCoService.findAll());
            return "/admin-template/kich_co/sua-kich-co";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            KichCo kt = kichCoService.getById(kichCo.getId());
            kichCo.setNgayTao(kt.getNgayTao());
            kichCo.setNgaySua(new Date());
            kichCoService.update(kichCo);
            return "redirect:/admin/kich-co";
        }
    }

    @PostMapping("/add")
    public String add(@Valid
                      @ModelAttribute("kichCo") KichCo kichCo,
                      BindingResult result,
                      Model model,
                      RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listKichCo", kichCoService.findAll());

            return "/admin-template/kich_co/kich-co";
        } else if (!kichCoService.checkTenTrung(kichCo.getTen())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Kích cỡ đã tồn tại");
            model.addAttribute("listKichCo", kichCoService.findAll());
            return "/admin-template/kich_co/kich-co";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            kichCo.setNgayTao(new Date());
            kichCo.setNgaySua(new Date());
            kichCo.setTrangThai(0);
            kichCoService.save(kichCo);
            return "redirect:/admin/kich-co";
        }
    }

}
