package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.entity.ChiTietSanPham;
import com.example.bedatnsd47.entity.HoaDon;
import com.example.bedatnsd47.entity.HoaDonChiTiet;
import com.example.bedatnsd47.entity.TaiKhoan;
import com.example.bedatnsd47.service.ChiTietSanPhamSerivce;
import com.example.bedatnsd47.service.DiaChiService;
import com.example.bedatnsd47.service.HoaDonChiTietService;
import com.example.bedatnsd47.service.HoaDonService;
import com.example.bedatnsd47.service.KhachHangService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @Autowired
    HttpServletRequest request;

    @GetMapping("/hoa-don")
    public String home() {
        request.setAttribute("lstHoaDon", hoaDonService.find5ByTrangThai(-1));
        return "/admin-template/ban-hang-admin-v2";
    }

    Date timeNow = new Date();
    Long idhdc;

    @GetMapping("/hoa-don/{id}")
    public String hoaDon(@PathVariable Long id) {
        request.setAttribute("lstHoaDon", hoaDonService.find5ByTrangThai(-1));
        request.setAttribute("lstHdct", hoaDonChiTietService.findAll());
        request.setAttribute("lstCtsp", chiTietSanPhamSerivce.fillAllDangHoatDongLonHon0());
        request.setAttribute("lstTaiKhoan", khachHangService.getAll());
        request.setAttribute("lstTaiKhoanDc",
                khachHangService.getById(hoaDonService.findById(id).getTaiKhoan().getId()));
        idhdc = id;
        HoaDon hd = hoaDonService.findById(id);
        request.setAttribute("hoaDon", hd);
        return "/admin-template/hoa-don-chi-tiet";
    }

    @PostMapping("/hoa-don/add")
    public String taoHoaDon() {
        if (hoaDonService.countHoaDonTreo() < 5) {
            HoaDon hd = new HoaDon();
            hd.setTaiKhoan(khachHangService.getById((long) 1));
            hd.setTrangThai(-1); // view 5 hoa don
            hd.setNgaySua(timeNow);
            hd.setTaiKhoan(khachHangService.findKhachLe());
            hoaDonService.saveOrUpdate(hd);
            hd.setMaHoaDon("HD" + hd.getId());
            hoaDonService.saveOrUpdate(hd);
            idhdc = hd.getId();
            return "redirect:/ban-hang-tai-quay/hoa-don/" + idhdc;
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
        if(soLuongEdit==0){
            hoaDonChiTietService.deleteById(idHdct);
        }else{
            hdct.setSoLuong(soLuongEdit);
        hoaDonChiTietService.saveOrUpdate(hdct);
        }
        
        return "redirect:/ban-hang-tai-quay/hoa-don/" + idhdc;
    }

    public void addKhachLeDb() {
        if (khachHangService.findKhachLe() == null) {
            TaiKhoan kl = new TaiKhoan();
            kl.setHoVaTen("Khách lẻ");
            khachHangService.add(kl);
        }
    }

    @PostMapping("/hoa-don/add-khach-hang")
    public String addKhachHang(@RequestParam Long idTaiKhoan) {
        HoaDon hd = hoaDonService.findById(idhdc);
        if (idTaiKhoan == -1) {
            hd.setTaiKhoan(khachHangService.findKhachLe());

        } else {
            hd.setTaiKhoan(khachHangService.getById(idTaiKhoan));
        }

        hoaDonService.saveOrUpdate(hd);
        return "redirect:/ban-hang-tai-quay/hoa-don/" + idhdc;
    }

    @PostMapping("/hoa-don/thanh-toan")
    public String thanhToanV2(@RequestParam(defaultValue = "off") String treo,
            @RequestParam(defaultValue = "off") String giaoHang) {
        HoaDon hd = hoaDonService.findById(idhdc);
        switch (hd.getTrangThai()) {
            case -1:
                if (treo.equals("on")) {
                    hd.setTrangThai(4);
                } else if (giaoHang.equals("on")) {
                    hd.setTrangThai(0);
                } else {
                    hd.setTrangThai(3);
                    updateSl(hd);
                }
                break;
            case 0:
                hd.setTrangThai(1);
                updateSl(hd);
                break;
            case 1:
                hd.setTrangThai(2);
                break;
            case 2:
                hd.setTrangThai(3);
                break;
            case 4:
                hd.setTrangThai(3);
                updateSl(hd);
                break;
            default:
                break;

        }
        hoaDonService.saveOrUpdate(hd);
        return "redirect:/ban-hang-tai-quay/hoa-don/quan-ly";
    }

    private void updateSl(HoaDon hd) {
        List<HoaDonChiTiet> lstHdct = hoaDonService.findById(idhdc).getLstHoaDonChiTiet();
        for (HoaDonChiTiet hdct : lstHdct) {
            Long idid = hdct.getChiTietSanPham().getId();
            ChiTietSanPham ctsp = chiTietSanPhamSerivce.getById(idid);
            ctsp.setSoLuong(ctsp.getSoLuong() - hdct.getSoLuong());
            chiTietSanPhamSerivce.update(ctsp);
        }
    }

    @PostMapping("/hoa-don/thanh-toan2")
    public String thanhToan(@RequestParam(defaultValue = "khongthay") String checkGiaoHang,
            @RequestParam(defaultValue = "khongthaytreo") String treo) {
        HoaDon hd = hoaDonService.findById(idhdc);
        if (treo.equals("on")) {
            hd.setTrangThai(4);
            hoaDonService.saveOrUpdate(hd);
            return "redirect:/ban-hang-tai-quay/hoa-don/quan-ly";
        }
        if (hd.getTrangThai() == -1 && !checkGiaoHang.equals("on")) {

            hd.setTongTien(hd.tongTienHoaDon());
            List<HoaDonChiTiet> lstHdct = hoaDonService.findById(idhdc).getLstHoaDonChiTiet();
            for (HoaDonChiTiet hdct : lstHdct) {
                Long idid = hdct.getChiTietSanPham().getId();
                ChiTietSanPham ctsp = chiTietSanPhamSerivce.getById(idid);
                ctsp.setSoLuong(ctsp.getSoLuong() - hdct.getSoLuong());
                chiTietSanPhamSerivce.update(ctsp);
            }

            hd.setTrangThai(3);
        } else if (hd.getTrangThai() == -1 && checkGiaoHang.equals("on")) {

            hd.setTongTien(hd.tongTienHoaDon());
            List<HoaDonChiTiet> lstHdct = hoaDonService.findById(idhdc).getLstHoaDonChiTiet();
            for (HoaDonChiTiet hdct : lstHdct) {
                Long idid = hdct.getChiTietSanPham().getId();
                ChiTietSanPham ctsp = chiTietSanPhamSerivce.getById(idid);
                ctsp.setSoLuong(ctsp.getSoLuong() - hdct.getSoLuong());
                chiTietSanPhamSerivce.update(ctsp);
            }

            hd.setTrangThai(0);
        } else if (hd.getTrangThai() <= 3) {
            hd.setTrangThai(hd.getTrangThai() + 1);
        } else if (hd.getTrangThai() == 4) {
            hd.setTrangThai(3);
        }

        hoaDonService.saveOrUpdate(hd);
        return "redirect:/ban-hang-tai-quay/hoa-don/quan-ly";
    }

    @GetMapping("/hoa-don/quan-ly")
    public String quanLyHoaDon() {
        request.setAttribute("lstHdChoXacNhan", hoaDonService.find5ByTrangThai(0));
        request.setAttribute("lstHdChoGiao", hoaDonService.find5ByTrangThai(1));
        request.setAttribute("lstHdDangGiao", hoaDonService.find5ByTrangThai(2));
        request.setAttribute("lstHdHoanThanh", hoaDonService.find5ByTrangThai(3));
        request.setAttribute("lstHdChoThanhToan", hoaDonService.find5ByTrangThai(4));
        request.setAttribute("lstHdHuy", hoaDonService.find5ByTrangThai(5));
        return "/admin-template/hoa-don";
    }
}
