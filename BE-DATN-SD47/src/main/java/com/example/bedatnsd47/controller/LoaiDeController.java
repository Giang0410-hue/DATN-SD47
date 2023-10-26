package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.entity.LoaiDe;
import com.example.bedatnsd47.entity.ThuongHieu;
import com.example.bedatnsd47.service.LoaiDeService;
import com.example.bedatnsd47.service.ThuongHieuService;
import com.example.bedatnsd47.validation.Validation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
@RequestMapping("/admin/loai-de")
public class LoaiDeController {
    @Autowired
    LoaiDeService loaiDeService;

    private Integer pageNo = 0;

    private Date currentDate = new Date();

    @GetMapping("")
    public String hienThi(
            Model model
    ) {
        model.addAttribute("listLoaiDe", loaiDeService.getPage(pageNo).stream().toList());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("loaiDe", new LoaiDe());
        return "/admin-template/loai_de/loai-de";
    }

    @GetMapping("/pre")
    public String hienThiPre(
    ) {
        pageNo--;
        pageNo = loaiDeService.checkPageNo(pageNo);
        return "redirect:/admin/loai-de";
    }

    @GetMapping("/next")
    public String hienThiNext(
    ) {
        pageNo++;
        pageNo = loaiDeService.checkPageNo(pageNo);
        return "redirect:/admin/loai-de";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(
            Model model,
            @PathVariable("id") Long id
    ) {
        LoaiDe loaiDe = loaiDeService.getById(id);
        model.addAttribute("listThuongHieu", loaiDeService.findAll());
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
//            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            return "/admin-template/loai_de/sua-loai-de";
        } else if (!loaiDeService.checkTenTrungSua(loaiDe.getId(), loaiDe.getTen())) {
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Tên sản phẩm đã tồn tại");
//            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            return "/admin-template/loai_de/sua-loai-de";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            LoaiDe ld = loaiDeService.getById(loaiDe.getId());
            loaiDe.setNgayTao(ld.getNgayTao());
            loaiDe.setNgaySua(currentDate);
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
            model.addAttribute("listThuongHieu", loaiDeService.getPage(pageNo).stream().toList());
            model.addAttribute("index", pageNo + 1);
            model.addAttribute("currentPage", pageNo);
//            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            return "/admin-template/loai_de/loai-de";
        } else if (!loaiDeService.checkTenTrung(loaiDe.getTen())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Tên sản phẩm đã tồn tại");
            model.addAttribute("listThuongHieu", loaiDeService.getPage(pageNo).stream().toList());
            model.addAttribute("index", pageNo + 1);
            model.addAttribute("currentPage", pageNo);
            return "/admin-template/loai_de/loai-de";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
//            sanPham.setMa("SP" + thuongHieuService.genMaTuDong());
            loaiDe.setNgayTao(currentDate);
            loaiDe.setTrangThai(0);
            loaiDeService.update(loaiDe);
            return "redirect:/admin/loai-de";
        }
    }






}
