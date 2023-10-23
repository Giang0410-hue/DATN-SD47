package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.entity.ThuongHieu;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;


@Controller
//@RestController
@RequestMapping("/ap1/v1/thuong-hieu")
public class ThuongHieuController {
    @Autowired
    private ThuongHieuService thuongHieuService;

    @GetMapping("/hien-thi")
    public String listThuongHieu(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 5; // Số lượng phần tử trên mỗi trang
        Page<ThuongHieu> thuongHieuPage = thuongHieuService.findAll(page, pageSize);
        model.addAttribute("thuongHieuPage", thuongHieuPage);
        model.addAttribute("thuongHieu", new ThuongHieu());
        return "admin-template/thuong_hieu/thuong-hieu";
    }

    @GetMapping("/getById/{id}")
    public String getId(@PathVariable("id") Long id, Model model) {
        ThuongHieu thuongHieu = thuongHieuService.findById(id).orElse(null);
        System.out.println(thuongHieu);
        model.addAttribute("thuongHieu", thuongHieu);
        return "admin-template/thuong_hieu/sua-thuong-hieu";
    }


    @PostMapping("/tim")
    public String timKiemThuongHieu(@RequestParam("keyword") String keyword, @RequestParam("trangThai") Integer trangThai, Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 5; // Số lượng phần tử trên mỗi trang
        System.out.println(keyword);
        Page<ThuongHieu> thuongHieuPage = thuongHieuService.findAll(page, pageSize);
        Page<ThuongHieu> ketQua = thuongHieuService.findByTenContaining(keyword, trangThai, page, pageSize);

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
        ThuongHieu thuongHieu = thuongHieuService.findByTen(ten);

        if (thuongHieu == null) {
            ThuongHieu thuongHieu1 = new ThuongHieu();
            thuongHieuService.saveOrUpdate(thuongHieu1, ten);
        } else {
            model.addAttribute("message", Validation.MESS_TRUNG_TEN);
        }
        return "redirect:/ap1/v1/thuong-hieu/hien-thi";
    }


    @PostMapping("/update")
    public String update(@RequestParam("ten") String ten, @RequestParam("trangThai") Integer trangThai, Model model, @RequestParam(defaultValue = "0") int page,
                         @RequestParam("id") Long id, @RequestParam("ngayTao")  @DateTimeFormat(pattern = "yyyy-MM-dd")Date ngayTao) {
        // Tìm "thuongHieu" dựa trên tên

        ThuongHieu thuongHieu = thuongHieuService.findById(id).orElse(null);

        try {
            if (thuongHieu != null) {
                ThuongHieu thuongHieu1 = new ThuongHieu();
                thuongHieuService.update(thuongHieu1, id, trangThai, ten,ngayTao);
                return "redirect:/ap1/v1/thuong-hieu/hien-thi";
            } else {
                model.addAttribute("message", Validation.MESS_TRUNG_TEN);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/ap1/v1/thuong-hieu/hien-thi";
    }


}
