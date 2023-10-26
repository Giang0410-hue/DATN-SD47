package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.entity.KichCo;
import com.example.bedatnsd47.entity.ThuongHieu;
import com.example.bedatnsd47.service.KichThuocService;
import com.example.bedatnsd47.service.ThuongHieuService;
import com.example.bedatnsd47.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/ap1/v1/kich-co")
public class KichThuocController {
    @Autowired
    private KichThuocService kichThuocService;

    @GetMapping("/hien-thi")
    public String listThuongHieu(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 5; // Số lượng phần tử trên mỗi trang
        Page<KichCo> thuongHieuPage = kichThuocService.findAll(page, pageSize);
        model.addAttribute("thuongHieuPage", thuongHieuPage);
        model.addAttribute("thuongHieu", new KichCo());
        return "admin-template/kich_co/kich-co";
    }

    @GetMapping("/getById/{id}")
    public String getId(@PathVariable("id") Long id, Model model) {
        KichCo thuongHieu = kichThuocService.findById(id).orElse(null);
        System.out.println(thuongHieu);
        model.addAttribute("thuongHieu", thuongHieu);
        return "admin-template/kich_co/sua-kich-co";
    }


    @PostMapping("/tim")
    public String timKiemThuongHieu(@RequestParam("keyword") String keyword, @RequestParam("trangThai") Integer trangThai, Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 5; // Số lượng phần tử trên mỗi trang
        System.out.println(keyword);
        Page<KichCo> thuongHieuPage = kichThuocService.findAll(page, pageSize);
        Page<KichCo> ketQua = kichThuocService.findByTenContaining(keyword, trangThai, page, pageSize);

        if (keyword == null || keyword.equals("")) {
            model.addAttribute("thuongHieuPage", thuongHieuPage);
            model.addAttribute("thuongHieu", new ThuongHieu());
            return "redirect:/ap1/v1/thuong-hieu/hien-thi";
        } else if (trangThai == 3) {
            model.addAttribute("thuongHieuPage", thuongHieuPage);
            model.addAttribute("thuongHieu", new ThuongHieu());
            return "redirect:/ap1/v1/thuong-hieu/hien-thi";
        } else {
            model.addAttribute("thuongHieuPage", ketQua);
            model.addAttribute("thuongHieu", new ThuongHieu());
            return "admin-template/thuong_hieu/thuong-hieu";
        }
    }

    @PostMapping("/add")
    public String timKiemThuongHieu(@RequestParam("ten") Float ten, Model model, @RequestParam(defaultValue = "0") int page) {
        // Tìm "thuongHieu" dựa trên tên
        KichCo thuongHieu = kichThuocService.findByTen(ten);

        if (thuongHieu == null) {
            KichCo thuongHieu1 = new KichCo();
            kichThuocService.saveOrUpdate(thuongHieu1, ten);
        } else {
            model.addAttribute("message", Validation.MESS_TRUNG_TEN);
        }
        return "redirect:/ap1/v1/kich-co/hien-thi";
    }


    @PostMapping("/update")
    public String update(@RequestParam("ten") Float ten, @RequestParam("trangThai") Integer trangThai, Model model, @RequestParam(defaultValue = "0") int page,
                         @RequestParam("id") Long id, @RequestParam("ngayTao") @DateTimeFormat(pattern = "yyyy-MM-dd") Date ngayTao) {
        // Tìm "thuongHieu" dựa trên tên

        KichCo thuongHieu = kichThuocService.findById(id).orElse(null);

        try {
            if (thuongHieu != null) {
                KichCo thuongHieu1 = new KichCo();
                kichThuocService.update(thuongHieu1, id, trangThai, ten, ngayTao);
                return "redirect:/ap1/v1/kich-co/hien-thi";
            } else {
                model.addAttribute("message", Validation.MESS_TRUNG_TEN);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/ap1/v1/kich-co/hien-thi";
    }
}
