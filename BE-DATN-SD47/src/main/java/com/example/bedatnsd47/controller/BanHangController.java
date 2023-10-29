package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.entity.ChiTietSanPham;
import com.example.bedatnsd47.entity.HoaDon;
import com.example.bedatnsd47.entity.HoaDonChiTiet;
import com.example.bedatnsd47.service.ChiTietSanPhamSerivce;
import com.example.bedatnsd47.service.HoaDonChiTietService;
import com.example.bedatnsd47.service.HoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ban-hang-tai-quay")
public class BanHangController {

    @Autowired
    HoaDonService hoaDonService;

    @Autowired
    HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    ChiTietSanPhamSerivce chiTietSanPhamSerivce;

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("lstHoaDon", hoaDonService.findAll());
        model.addAttribute("lstHdct", hoaDonChiTietService.findByIdHoaDon(36l));
        model.addAttribute("lstCtsp", chiTietSanPhamSerivce.getAll());
        return "/admin-template/ban-hang-admin";
    }

    @PostMapping("/create")
    public String taoHoaDon() {
        HoaDon hd = new HoaDon();
        Integer size = hoaDonService.findAll().size() + 1;
        hd.setMaHoaDon("HD" + size);
        hoaDonService.saveOrUpdate(hd);
        return "redirect:home";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        hoaDonService.deleteById(id);
        return "redirect:/ban-hang-tai-quay/home";
    }

    @PostMapping("/hdct/add")
    public String addHdct(@RequestParam Long idHoaDon, @RequestParam Long idCtsp) {
        Boolean cr =true;
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        HoaDon hoaDon = hoaDonService.findById(idHoaDon);
        ChiTietSanPham ctsp = chiTietSanPhamSerivce.getById(idCtsp);
        for (HoaDonChiTiet obj : hoaDonChiTietService.findAll()) {
            if (obj.getChiTietSanPham().getId() == idCtsp) {
                hdct = obj;
                hdct.setSoLuong(hdct.getSoLuong() + 1);
                cr=false;
                break;
            }
        }

        if (cr) {
            hdct = new HoaDonChiTiet();
            hdct.setHoaDon(hoaDon);
            hdct.setChiTietSanPham(ctsp);
            hdct.setSoLuong(1);
            hdct.setDonGia(ctsp.getGiaHienHanh());
        }
        hoaDonChiTietService.saveOrUpdate(hdct);
        return "redirect:/ban-hang-tai-quay/home";
    }

    @GetMapping("/hdct/delete/{id}")
    public String deleteHdct(@PathVariable Long id) {
        hoaDonChiTietService.deleteById(id);
        return "redirect:/ban-hang-tai-quay/home";
    }

}
