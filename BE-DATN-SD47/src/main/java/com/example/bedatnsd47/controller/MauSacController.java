package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.entity.MauSac;
import com.example.bedatnsd47.entity.SanPham;
import com.example.bedatnsd47.entity.ThuongHieu;
import com.example.bedatnsd47.service.MauSacService;
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
@RequestMapping("/admin/mau-sac")
public class MauSacController {
    @Autowired
    MauSacService mauSacService;

    private Integer pageNo = 0;

    private Integer trangThai = 1;

    private Date currentDate = new Date();


    @GetMapping()
    public String hienThi(
            Model model
    ) {
        model.addAttribute("listMauSac", mauSacService.getPage(pageNo).stream().toList());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("mauSac", new MauSac());
        return "/admin-template/mau_sac/mau-sac";
    }

    @GetMapping("/pre")
    public String hienThiPre(
    ) {
        pageNo--;
        pageNo = mauSacService.checkPageNo(pageNo);
        return "redirect:/admin/mau-sac";
    }

    @GetMapping("/next")
    public String hienThiNext(
    ) {
        pageNo++;
        pageNo = mauSacService.checkPageNo(pageNo);
        return "redirect:/admin/mau-sac";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(
            Model model,
            @PathVariable("id") Long id
    ) {
        MauSac mauSac = mauSacService.getById(id);
//        model.addAttribute("listThuongHieu", mauSacService.findAll());
        model.addAttribute("mauSac", mauSac);
        return "/admin-template/mau_sac/sua-mau-sac";
    }

    @PostMapping("/update")
    public String update(@Valid
                         @ModelAttribute("mauSac") MauSac mauSac,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("currentPage", pageNo);
            return "/admin-template/mau_sac/sua-mau-sac";
        } else if (!mauSacService.checkTenTrungSua(mauSac.getMaMau(), mauSac.getTen())) {
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("checkTenTrung", "Tên sản phẩm đã tồn tại");
            return "/admin-template/mau_sac/sua-mau-sac";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            MauSac ms = mauSacService.getById(mauSac.getId());
            mauSac.setNgayTao(ms.getNgayTao());
            mauSac.setNgaySua(currentDate);
            mauSacService.update(mauSac);
            return "redirect:/admin/mau-sac";
        }
    }

    @PostMapping("/add")
    public String add(@Valid
                      @ModelAttribute("mauSac") MauSac mauSac,
                      BindingResult result,
                      Model model,
                      RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listMauSac", mauSacService.getPage(pageNo).stream().toList());
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("index", pageNo + 1);
            return "/admin-template/mau_sac/mau-sac";
        } else if (!mauSacService.checkTenTrung(mauSac.getTen())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("checkTenTrung", "Tên sản phẩm đã tồn tại");
            model.addAttribute("listMauSac", mauSacService.getPage(pageNo).stream().toList());
            model.addAttribute("index", pageNo + 1);
            return "/admin-template/mau_sac/mau-sac";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            mauSac.setMaMau("MS" + mauSacService.genMaTuDong());
            mauSac.setNgayTao(currentDate);
            mauSac.setTrangThai(0);
            mauSacService.update(mauSac);
            return "redirect:/admin/mau-sac";
        }
    }
}
