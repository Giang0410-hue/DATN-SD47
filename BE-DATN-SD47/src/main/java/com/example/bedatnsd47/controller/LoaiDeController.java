package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.entity.LoaiDe;
import com.example.bedatnsd47.entity.ThuongHieu;
import com.example.bedatnsd47.service.LoaiDeService;
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
@RequestMapping("/ap1/v1/loai-de")
public class LoaiDeController {
    @Autowired
    LoaiDeService loaiDeService;
    @GetMapping("/hien-thi")
    public String listThuongHieu(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 5; // Số lượng phần tử trên mỗi trang
        Page<LoaiDe> thuongHieuPage = loaiDeService.findAll(page, pageSize);
        model.addAttribute("thuongHieuPage", thuongHieuPage);
        model.addAttribute("thuongHieu", new LoaiDe());
        return "admin-template/loai_de/loai-de";
    }

    @GetMapping("/getById/{id}")
    public String getId(@PathVariable("id") Long id, Model model) {
        LoaiDe thuongHieu = loaiDeService.findById(id).orElse(null);
        System.out.println(thuongHieu);
        model.addAttribute("thuongHieu", thuongHieu);
        return "admin-template/loai_de/sua-loai-de";
    }


    @PostMapping("/tim")
    public String timKiemThuongHieu(@RequestParam("keyword") String keyword, @RequestParam("trangThai") Integer trangThai, Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 5; // Số lượng phần tử trên mỗi trang
        System.out.println(keyword);
        Page<LoaiDe> thuongHieuPage = loaiDeService.findAll(page, pageSize);
        Page<LoaiDe> ketQua = loaiDeService.findByTenContaining(keyword, trangThai, page, pageSize);

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
    public String timKiemThuongHieu(@RequestParam("ten") String ten, Model model, @RequestParam(defaultValue = "0") int page) {
        // Tìm "thuongHieu" dựa trên tên
        LoaiDe thuongHieu = loaiDeService.findByTen(ten);

        if (thuongHieu == null) {
            LoaiDe thuongHieu1 = new LoaiDe();
            loaiDeService.saveOrUpdate(thuongHieu1, ten);
        } else {
            model.addAttribute("message", Validation.MESS_TRUNG_TEN);
        }
        return "redirect:/ap1/v1/loai-de/hien-thi";
    }


    @PostMapping("/update")
    public String update(@RequestParam("ten") String ten, @RequestParam("trangThai") Integer trangThai, Model model, @RequestParam(defaultValue = "0") int page,
                         @RequestParam("id") Long id, @RequestParam("ngayTao")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date ngayTao) {
        // Tìm "thuongHieu" dựa trên tên

        LoaiDe thuongHieu = loaiDeService.findById(id).orElse(null);

        try {
            if (thuongHieu != null) {
                LoaiDe thuongHieu1 = new LoaiDe();
                loaiDeService.update(thuongHieu1, id, trangThai, ten,ngayTao);
                return "redirect:/ap1/v1/loai-de/hien-thi";
            } else {
                model.addAttribute("message", Validation.MESS_TRUNG_TEN);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/ap1/v1/loai-de/hien-thi";
    }
}
