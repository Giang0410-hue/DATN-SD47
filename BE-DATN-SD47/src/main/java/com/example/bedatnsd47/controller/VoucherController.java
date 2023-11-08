package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.entity.Voucher;
import com.example.bedatnsd47.service.VoucherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
@RequestMapping("/admin/voucher")

public class VoucherController {
    @Autowired
    VoucherService voucherService;


    private Date currentDate = new Date();

    @GetMapping()
    public String hienThi(
            Model model) {
        model.addAttribute("listVoucher", voucherService.findAll());
        model.addAttribute("voucher", new Voucher());
        return "/admin-template/voucher/voucher";
    }

    @GetMapping("/dang-dien-ra")
    public String hienThiDangHoatDong(
            Model model) {
        model.addAttribute("listVoucher", voucherService.fillAllDangDienRa());
        model.addAttribute("voucher", new Voucher());
        return "/admin-template/voucher/voucher";
    }

    @GetMapping("/da-ket-thuc")
    public String hienThiNgungHoatDong(
            Model model) {
        model.addAttribute("listVoucher", voucherService.fillAllDaKetThuc());
        model.addAttribute("voucher", new Voucher());
        return "/admin-template/voucher/voucher";
    }
    @GetMapping("/sap-dien-ra")
    public String hienThiSapDienRa(
            Model model) {
        model.addAttribute("listVoucher", voucherService.fillAllSapDienRa());
        model.addAttribute("voucher", new Voucher());
        return "/admin-template/voucher/voucher";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(
            Model model,
            @PathVariable("id") Long id) {
        Voucher voucher = voucherService.getById(id);
        model.addAttribute("voucher", voucher);
        return "/admin-template/voucher/sua-voucher";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("voucher") Voucher voucher,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("checkThongBao", "thaiBai");
            return "/admin-template/voucher/sua-voucher";
        }
        else if (voucher.getNgayBatDau().after(voucher.getNgayKetThuc())) {
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkNgayKetThuc", "ngayKetThuc");
            model.addAttribute("listVoucher", voucherService.findAll());
            return "/admin-template/voucher/sua-voucher";
        }
        else if (!voucherService.checkTenTrungSua(voucher.getMaVoucher(), voucher.getTenVoucher())) {
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Voucher đã tồn tại");
            return "/admin-template/voucher/sua-voucher";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            Voucher ms = voucherService.getById(voucher.getId());

            voucher.setNgaySua(currentDate);
            voucherService.update(voucher);
            return "redirect:/admin/voucher";
        }
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("voucher") Voucher voucher,
                      BindingResult result,
                      Model model,
                      RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listVoucher", voucherService.findAll());
            return "/admin-template/voucher/voucher";
        } else if (voucher.getNgayBatDau().after(voucher.getNgayKetThuc())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkNgayKetThuc", "ngayKetThuc");
            model.addAttribute("listVoucher", voucherService.findAll());
            return "/admin-template/voucher/voucher";
        } else if (!voucherService.checkMaTrung(voucher.getMaVoucher())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkMaTrung", "Mã voucher đã tồn tại");
            model.addAttribute("listVoucher", voucherService.findAll());
            return "/admin-template/voucher/voucher";
        } else if (!voucherService.checkTenTrung(voucher.getTenVoucher())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Tên voucher đã tồn tại");
            model.addAttribute("listVoucher", voucherService.findAll());
            return "/admin-template/voucher/voucher";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            voucher.setNgayTao(currentDate);
            voucher.setTrangThai(2);
            voucherService.update(voucher);
            return "redirect:/admin/voucher";
        }
    }
}
