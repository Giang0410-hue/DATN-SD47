package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.entity.MauSac;
import com.example.bedatnsd47.entity.ThuongHieu;
import com.example.bedatnsd47.service.MauSacService;
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
@RequestMapping("/ap1/v1/mau-sac")
public class MauSacController {
    @Autowired
    MauSacService mauSacService;

    @GetMapping("/hien-thi")
    public String listMauSac(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 5; // Số lượng phần tử trên mỗi trang
        Page<MauSac> thuongHieuPage = mauSacService.findAll(page, pageSize);
        model.addAttribute("thuongHieuPage", thuongHieuPage);
        model.addAttribute("mauSac", new MauSac());
        return "admin-template/mau_sac/mau-sac";
    }

    @GetMapping("/getById/{id}")
    public  String getId(@PathVariable("id") Long id, Model model){
        MauSac mauSac = mauSacService.findById(id).orElse(null);
        System.out.println(mauSac);
        model.addAttribute("mauSac",mauSac);
        return "admin-template/mau_sac/sua-mau-sac";
    }

    @PostMapping("/add")
    public String add(@RequestParam("ten") String ten, Model model, @RequestParam(defaultValue = "0") int page) {
        // Tìm "thuongHieu" dựa trên tên
        MauSac mauSac = mauSacService.findByTen(ten);

        if (mauSac == null) {
            MauSac mauSac1 = new MauSac();
            mauSacService.saveOrUpdate(mauSac1, ten);
        }else{
            model.addAttribute("message", Validation.MESS_TRUNG_TEN);
        }
        return "redirect:/ap1/v1/mau-sac/hien-thi";
    }

    @PostMapping("/update")
    public String update(@RequestParam("ten") String ten, @RequestParam("trangThai") Integer trangThai, Model model, @RequestParam(defaultValue = "0") int page,
                         @RequestParam("id") Long id, @RequestParam("ngayTao")  @DateTimeFormat(pattern = "yyyy-MM-dd") Date ngayTao) {
        MauSac mauSac = mauSacService.findById(id).orElse(null);

        try {
            if (mauSac != null) {
                MauSac thuongHieu1 = new MauSac();

                mauSacService.update(thuongHieu1, id, trangThai, ten,ngayTao);
                return "redirect:/ap1/v1/mau-sac/hien-thi";
            } else {
                model.addAttribute("message", Validation.MESS_TRUNG_TEN);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/ap1/v1/mau-sac/hien-thi";
    }
}
