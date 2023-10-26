package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.entity.KichCo;
import com.example.bedatnsd47.entity.ThuongHieu;
import com.example.bedatnsd47.service.KichThuocService;
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
@RequestMapping("/admin/kich-co")
public class KichThuocController {
    @Autowired
    private KichThuocService kichThuocService;

    private Integer pageNo = 0;

    private Date currentDate = new Date();

    @GetMapping("")
    public String hienThi(
            Model model
    ) {
        model.addAttribute("listKichCo", kichThuocService.getPage(pageNo).stream().toList());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("kichCo", new KichCo());
        return "/admin-template/kich_co/kich-co";
    }

    @GetMapping("/pre")
    public String hienThiPre(
    ) {
        pageNo--;
        pageNo = kichThuocService.checkPageNo(pageNo);
        return "redirect:/admin/kich-co";
    }

    @GetMapping("/next")
    public String hienThiNext(
    ) {
        pageNo++;
        pageNo = kichThuocService.checkPageNo(pageNo);
        return "redirect:/admin/kich-co";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(
            Model model,
            @PathVariable("id") Long id
    ) {
        KichCo kichCo = kichThuocService.getById(id);
        model.addAttribute("listKichCo", kichThuocService.findAll());
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
            model.addAttribute("listKichCo", kichThuocService.findAll());
            return "/admin-template/kich_co/sua-kich-co";
        } else if (!kichThuocService.checkTenTrungSua(kichCo.getId(), kichCo.getTen())) {
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Tên sản phẩm đã tồn tại");
            model.addAttribute("listKichCo", kichThuocService.findAll());
            return "/admin-template/kich_co/sua-kich-co";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            KichCo kt = kichThuocService.getById(kichCo.getId());
            kichCo.setNgayTao(kt.getNgayTao());
            kichCo.setNgaySua(currentDate);
            kichThuocService.update(kichCo);
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
            model.addAttribute("listKichCo", kichThuocService.getPage(pageNo).stream().toList());
            model.addAttribute("index", pageNo + 1);
            model.addAttribute("currentPage", pageNo);
//            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            return "/admin-template/kich_co/kich-co";
        } else if (!kichThuocService.checkTenTrung(kichCo.getTen())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("checkTenTrung", "Tên sản phẩm đã tồn tại");
            model.addAttribute("listKichCo", kichThuocService.getPage(pageNo).stream().toList());
            model.addAttribute("index", pageNo + 1);
            return "/admin-template/kich_co/kich-co";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
//            sanPham.setMa("SP" + thuongHieuService.genMaTuDong());
            kichCo.setNgayTao(currentDate);
            kichCo.setTrangThai(0);
            kichThuocService.update(kichCo);
            return "redirect:/admin/kich-co";
        }
    }

}
