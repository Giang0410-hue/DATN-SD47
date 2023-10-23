package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.entity.SanPham;
import com.example.bedatnsd47.service.SanPhamSerivce;
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

import java.time.LocalDate;
import java.util.Date;

@Controller
@RequestMapping("/admin/san-pham")
public class SanPhamController {

    @Autowired
    private SanPhamSerivce sanPhamSerivce;

    @Autowired
    private ThuongHieuService thuongHieuService;

    private Integer pageNo = 0;

    private Integer trangThai = 1;

    private Date currentDate = new Date();


    @GetMapping()
    public String hienThi(
            Model model
    ) {
        model.addAttribute("listSanPham", sanPhamSerivce.getPage(pageNo).stream().toList());
        model.addAttribute("index", pageNo + 1);
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("sanPham", new SanPham());
        return "/admin-template/san_pham/san-pham";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(
            Model model,
            @PathVariable("id") Long id
    ) {
//        SanPham sanPham = sanPhamSerivce.getById(id);
//        model.addAttribute("detailSanPham",sanPham);
        return "/admin-template/san_pham/sua-san-pham";
    }

    @PostMapping("/add")
    public String add(@Valid
                      @ModelAttribute("sanPham") SanPham sanPham,
                      BindingResult result,
                      Model model
    ) {
        if (result.hasErrors()) {
            model.addAttribute("hasErrors", 1);
            model.addAttribute("listSanPham", sanPhamSerivce.getPage(pageNo).stream().toList());
            model.addAttribute("index", pageNo + 1);
            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            return "/admin-template/san_pham/san-pham";
        }

        sanPham.setMa("SP" + sanPhamSerivce.genMaTuDong());
        sanPham.setNgayTao(currentDate);
        sanPham.setTrangThai(1);
        sanPhamSerivce.add(sanPham);
        return "redirect:/admin/san-pham";
    }

}
