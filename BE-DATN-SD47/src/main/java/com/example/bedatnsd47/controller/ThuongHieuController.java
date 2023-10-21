package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.entity.ThuongHieu;
import com.example.bedatnsd47.service.ThuongHieuService;
import com.example.bedatnsd47.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
        return "admin-template/thuong-hieu";
    }

    @GetMapping("/getById/{id}")
    public  String getId(@PathVariable("id") Long id,Model model){
        ThuongHieu thuongHieu = thuongHieuService.findById(id).orElse(null);
        System.out.println(thuongHieu);
        model.addAttribute("thuongHieuDetail",thuongHieu);
        return "redirect:/ap1/v1/thuong-hieu/hien-thi";
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
            return "admin-template/thuong-hieu";
        }


    }

    @PostMapping("/add")
    public String timKiemThuongHieu(@RequestParam("ten") String ten, Model model, @RequestParam(defaultValue = "0") int page) {
        // Tìm "thuongHieu" dựa trên tên
        ThuongHieu thuongHieu = thuongHieuService.findByTen(ten);

        if (thuongHieu == null) {
            ThuongHieu thuongHieu1 = new ThuongHieu();
            thuongHieuService.saveOrUpdate(thuongHieu1, ten);
        }else{
            model.addAttribute("message",Validation.MESS_TRUNG_TEN);
        }
        return "redirect:/ap1/v1/thuong-hieu/hien-thi";
    }




}
