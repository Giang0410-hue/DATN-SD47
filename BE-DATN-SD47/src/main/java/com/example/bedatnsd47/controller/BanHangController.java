package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.entity.ChiTietSanPham;
import com.example.bedatnsd47.entity.HoaDon;
import com.example.bedatnsd47.entity.HoaDonChiTiet;
import com.example.bedatnsd47.service.ChiTietSanPhamSerivce;
import com.example.bedatnsd47.service.HoaDonChiTietService;
import com.example.bedatnsd47.service.HoaDonService;
import com.example.bedatnsd47.service.TaiKhoanService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@RequestMapping("/ban-hang-tai-quay")
@Controller
public class BanHangController {

    @Autowired
    HoaDonService hoaDonService;

    @Autowired
    HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    ChiTietSanPhamSerivce chiTietSanPhamSerivce;

    @Autowired
    TaiKhoanService taiKhoanService;

    @GetMapping("/hoa-don")
    public String home(Model model) {
        model.addAttribute("lstHoaDon", hoaDonService.findAll());
        return "/admin-template/ban-hang-admin-v2";
    }
    
   
    
    Long idhdc;

    @GetMapping("/hoa-don/{id}")
    public String hoaDon(@PathVariable Long id, Model model) {
        model.addAttribute("lstHoaDon", hoaDonService.findAll());
        model.addAttribute("lstHdct", hoaDonChiTietService.findAll());
        model.addAttribute("lstCtsp", chiTietSanPhamSerivce.getAll());
        model.addAttribute("lstTaiKhoan", taiKhoanService.getAll());
        idhdc = id;
        HoaDon hd = hoaDonService.findById(id);
        model.addAttribute("hoaDon", hd);
        return "/admin-template/hoa-don-chi-tiet";
    }

    @PostMapping("/hoa-don/add")
    public String taoHoaDon() {
        HoaDon hd = new HoaDon();
        Integer size = hoaDonService.findAll().size() + 1;
        hd.setMaHoaDon("HD" + size);
        hd.setTaiKhoan(taiKhoanService.getById((long) 1));
        hoaDonService.saveOrUpdate(hd);
        return "redirect:/ban-hang-tai-quay/hoa-don";
    }

    @GetMapping("/hoa-don/delete/{id}")
    public String delete(@PathVariable Long id) {
        hoaDonService.deleteById(id);

        return "redirect:/hoa-don/"+idhdc;
    }

    @PostMapping("/hoa-don-chi-tiet/add")
    public String addHdct(@RequestParam Long idHoaDon, @RequestParam Long idCtsp) {
        Boolean cr = true;
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        HoaDon hoaDon = hoaDonService.findById(idHoaDon);
        ChiTietSanPham ctsp = chiTietSanPhamSerivce.getById(idCtsp);
        for (HoaDonChiTiet obj : hoaDonChiTietService.findAll()) {
            if (obj.getHoaDon() == hoaDon) {
                if (obj.getChiTietSanPham() == ctsp) {
                    hdct = obj;
                    cr = false;
                    break;
                }
            }
        }

        if (cr) {
            hdct = new HoaDonChiTiet();
            hdct.setHoaDon(hoaDon);
            hdct.setChiTietSanPham(ctsp);
            hdct.setSoLuong(1);
            hdct.setDonGia(ctsp.getGiaHienHanh());
        } else {
            hdct.setSoLuong(hdct.getSoLuong() + 1);
        }
        hoaDonChiTietService.saveOrUpdate(hdct);
        System.out.println(idCtsp + "idctsp");
        System.out.println(idHoaDon + "idctsp");

        return "redirect:/ban-hang-tai-quay/hoa-don/" + idhdc;
    }

    @GetMapping("/hoa-don-chi-tiet/delete/{id}")
    public String deleteHdct(@PathVariable Long id) {
        hoaDonChiTietService.deleteById(id);
        return "redirect:/ban-hang-tai-quay/hoa-don/" + idhdc;
    }

    @PostMapping("/hoa-don-chi-tiet/update")
    public String updateSoLuong(@RequestParam Integer soLuongEdit, @RequestParam Long idHdct){
        HoaDonChiTiet hdct = hoaDonChiTietService.findById(idHdct);
        System.out.println("000"+idHdct);
        hdct.setSoLuong(soLuongEdit);
        hoaDonChiTietService.saveOrUpdate(hdct);
        return "redirect:/ban-hang-tai-quay/hoa-don/" + idhdc;
    }

    @PostMapping("/hoa-don/add-khach-hang")
    public String addKhachHang(@RequestParam Long idTaiKhoan){
        HoaDon hd = hoaDonService.findById(idhdc);
        hd.setTaiKhoan(taiKhoanService.getById(idTaiKhoan));
        hoaDonService.saveOrUpdate(hd);
        System.out.println(idTaiKhoan+"idtk");
        return "redirect:/ban-hang-tai-quay/hoa-don/" + idhdc;
    }

}
