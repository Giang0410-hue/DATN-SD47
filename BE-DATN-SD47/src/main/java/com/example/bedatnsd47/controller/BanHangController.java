package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.entity.ChiTietSanPham;
import com.example.bedatnsd47.entity.HoaDon;
import com.example.bedatnsd47.entity.HoaDonChiTiet;
import com.example.bedatnsd47.service.ChiTietSanPhamSerivce;
import com.example.bedatnsd47.service.DiaChiService;
import com.example.bedatnsd47.service.HoaDonChiTietService;
import com.example.bedatnsd47.service.HoaDonService;
import com.example.bedatnsd47.service.KhachHangService;

import java.util.Date;
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
    KhachHangService khachHangService;

    @Autowired
    DiaChiService diaChiService;

    @GetMapping("/hoa-don")
    public String home(Model model) {
        model.addAttribute("lstHoaDon", hoaDonService.find5ByTrangThai(-1));
        return "/admin-template/ban-hang-admin-v2";
    }

    Date timeNow = new Date();
    Long idhdc;

    @GetMapping("/hoa-don/{id}")
    public String hoaDon(@PathVariable Long id, Model model) {
        model.addAttribute("lstHoaDon", hoaDonService.find5ByTrangThai(-1));
        model.addAttribute("lstHdct", hoaDonChiTietService.findAll());
        model.addAttribute("lstCtsp", chiTietSanPhamSerivce.getAll());
        model.addAttribute("lstTaiKhoan", khachHangService.getAll());
        model.addAttribute("lstTaiKhoanDc", khachHangService.getById(hoaDonService.findById(id).getTaiKhoan().getId()));
        idhdc = id;
        HoaDon hd = hoaDonService.findById(id);
        model.addAttribute("hoaDon", hd);
        return "/admin-template/hoa-don-chi-tiet";
    }

    @PostMapping("/hoa-don/add")
    public String taoHoaDon() {
        if (hoaDonService.countHoaDonTreo() < 5) {
            HoaDon hd = new HoaDon();
            hd.setTaiKhoan(khachHangService.getById((long) 1));
            hd.setTrangThai(-1);
            hd.setNgaySua(timeNow);
            hoaDonService.saveOrUpdate(hd);
            hd.setMaHoaDon("HD" + hd.getId());
            hoaDonService.saveOrUpdate(hd);
        }
        return "redirect:/ban-hang-tai-quay/hoa-don";
    }

    @GetMapping("/hoa-don/delete/{id}")
    public String delete(@PathVariable Long id) {
        hoaDonService.deleteById(id);

        return "redirect:/ban-hang-tai-quay/hoa-don";
    }

    @PostMapping("/hoa-don-chi-tiet/add")
    public String addHdct(@RequestParam Long idHoaDon, @RequestParam Long idCtsp) {

        Boolean cr = true;
        HoaDonChiTiet hdct = new HoaDonChiTiet();
        HoaDon hoaDon = hoaDonService.findById(idHoaDon);
        hoaDon.setNgaySua(timeNow);
        hoaDonService.saveOrUpdate(hoaDon);
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
    public String updateSoLuong(@RequestParam Integer soLuongEdit, @RequestParam Long idHdct) {
        HoaDonChiTiet hdct = hoaDonChiTietService.findById(idHdct);
        hdct.setSoLuong(soLuongEdit);
        hoaDonChiTietService.saveOrUpdate(hdct);
        return "redirect:/ban-hang-tai-quay/hoa-don/" + idhdc;
    }

    @PostMapping("/hoa-don/add-khach-hang")
    public String addKhachHang(@RequestParam Long idTaiKhoan) {
        HoaDon hd = hoaDonService.findById(idhdc);
        hd.setTaiKhoan(khachHangService.getById(idTaiKhoan));
        hoaDonService.saveOrUpdate(hd);
        return "redirect:/ban-hang-tai-quay/hoa-don/" + idhdc;
    }

    @PostMapping("/hoa-don/thanh-toan")
    public String thanhToan() {
        HoaDon hd = hoaDonService.findById(idhdc);
        if(hd.getTrangThai()==-1){
            hd.setTrangThai(4);
        }else if(hd.getTrangThai()<=4){
            hd.setTrangThai(hd.getTrangThai()+1);
        }
        hd.setTongTien(hd.tongTienHoaDon());
        List<HoaDonChiTiet> lstHdct = hoaDonService.findById(idhdc).getLstHoaDonChiTiet();
        for (HoaDonChiTiet hdct : lstHdct) {
            Long idid = hdct.getChiTietSanPham().getId();
            ChiTietSanPham ctsp = chiTietSanPhamSerivce.getById(idid);
            ctsp.setSoLuong(ctsp.getSoLuong() - hdct.getSoLuong());
            chiTietSanPhamSerivce.update(ctsp);
        }

        hoaDonService.saveOrUpdate(hd);
        return "redirect:/ban-hang-tai-quay/hoa-don";
    }

    @GetMapping("/hoa-don/quan-ly")
    public String quanLyHoaDon(Model model) {
        model.addAttribute("lstHdChoXacNhan", hoaDonService.find5ByTrangThai(0));
        model.addAttribute("lstHdChoThanhToan", hoaDonService.find5ByTrangThai(1));
        model.addAttribute("lstHdChoGiao", hoaDonService.find5ByTrangThai(2));
        model.addAttribute("lstHdDangGiao", hoaDonService.find5ByTrangThai(3));
        model.addAttribute("lstHdHoanThanh", hoaDonService.find5ByTrangThai(4));
        model.addAttribute("lstHdHuy", hoaDonService.find5ByTrangThai(5));
        
        return "/admin-template/hoa-don";
    }
}
