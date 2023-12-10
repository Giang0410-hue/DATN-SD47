package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.config.ExportPdf;
import com.example.bedatnsd47.entity.ChiTietSanPham;
import com.example.bedatnsd47.entity.DiaChi;
import com.example.bedatnsd47.entity.GioHang;
import com.example.bedatnsd47.entity.HoaDon;
import com.example.bedatnsd47.entity.HoaDonChiTiet;
import com.example.bedatnsd47.entity.LichSuHoaDon;
import com.example.bedatnsd47.entity.TaiKhoan;
import com.example.bedatnsd47.entity.VaiTro;
import com.example.bedatnsd47.entity.Voucher;
import com.example.bedatnsd47.service.ChiTietSanPhamSerivce;
import com.example.bedatnsd47.service.DiaChiService;
import com.example.bedatnsd47.service.GioHangService;
import com.example.bedatnsd47.service.HoaDonChiTietService;
import com.example.bedatnsd47.service.HoaDonService;
import com.example.bedatnsd47.service.KhachHangService;
import com.example.bedatnsd47.service.LichSuHoaDonService;
import com.example.bedatnsd47.service.NhanVienService;
import com.example.bedatnsd47.service.TaiKhoanService;
import com.example.bedatnsd47.service.VoucherService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.aspectj.apache.bcel.classfile.Module.Export;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ban-hang-tai-quay")
// @HÁ
public class BanHangController {
    @Autowired
    GioHangService gioHangService;

    @Autowired
    LichSuHoaDonService lichSuHoaDonService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    HoaDonService hoaDonService;

    @Autowired
    HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    ChiTietSanPhamSerivce chiTietSanPhamSerivce;

    @Autowired
    KhachHangService khachHangService;

    @Autowired
    NhanVienService nhanVienService;

    @Autowired
    TaiKhoanService taiKhoanService;

    @Autowired
    DiaChiService diaChiService;

    @Autowired
    VoucherService voucherService;

    @Autowired
    HttpServletRequest request;

    @GetMapping("/hoa-don")
    public String home() {
        request.setAttribute("lstHoaDon", hoaDonService.find5ByTrangThai(-1));
        return "/admin-template/ban-hang-admin-v2";
    }

    void addKhachLe() {
        if (khachHangService.findKhachLe() == null) {
            khachHangService.addKhachLe();
        }
    }

    List<HoaDonChiTiet> lstHoaDonCtDoiTra = new ArrayList<>();
    Long idhdc;
    Long idTk = (long) 7;

    @GetMapping("/doi-tra")
    public String doiTra() {
        hoaDonService.deleteHoaDonHoanTra();
        return "/admin-template/doi-tra";
    }

    @GetMapping("/doi-tra/{maHoaDon}")
    public String detailHoaDonDoiTra(@PathVariable String maHoaDon, RedirectAttributes redirectAttributes) {
        HoaDon hd = hoaDonService.findByMa(maHoaDon);
        try {
            idhdc = hd.getId();
        } catch (Exception e) {
            thongBao(redirectAttributes, "Không có kết quả phù hợp", 0);

            return "redirect:/ban-hang-tai-quay/doi-tra";
        }
        if (hd.getTrangThai() != 3) {
            thongBao(redirectAttributes, "Không có kết quả phù hợp", 0);
            return "redirect:/ban-hang-tai-quay/doi-tra";
        }
        // hoaDonService.saveOrUpdate(hdHangTra);
        if (hoaDonService.findByMa(maHoaDon + "-DOITRA") == null) {
            HoaDon hdHangTra = new HoaDon();
            hdHangTra.setMaHoaDon(hd.getMaHoaDon() + "-DOITRA");
            hdHangTra.setTrangThai(8);
            hoaDonService.saveOrUpdate(hdHangTra);
            request.setAttribute("check", 0);
        } else {
            request.setAttribute("check", 1);
            request.setAttribute("hdHangTra", hoaDonService.findByMa(maHoaDon + "-DOITRA"));
        }

        request.setAttribute("lstHoaDon", hoaDonService.find5ByTrangThai(-1));
        request.setAttribute("lstHdct", hoaDonChiTietService.findAll());
        request.setAttribute("lstCtsp", chiTietSanPhamSerivce.fillAllDangHoatDongLonHon0());
        request.setAttribute("lstTaiKhoan", khachHangService.getAll());
        request.setAttribute("lstTaiKhoanDc",
                khachHangService.getById(hoaDonService.findById(hd.getId()).getTaiKhoan().getId()));
        request.setAttribute("listVoucher", voucherService.fillAllDangDienRa());

        request.setAttribute("lstLshd", lichSuHoaDonService.findByIdhd(idhdc));
        request.setAttribute("listLichSuHoaDon", lichSuHoaDonService.findByIdhdNgaySuaAsc(idhdc));

        checkVoucher();
        request.setAttribute("hoaDon", hd);
        request.setAttribute("byHoaDon", hd);
        thongBao(redirectAttributes, "Thành công", 1);
        return "/admin-template/detail-hoa-don-tra";
    }

    @GetMapping("/hoa-don/{id}")
    public String hoaDon(@PathVariable Long id, Model model) {
        chiTietSanPhamSerivce.checkSoLuongBang0();
        // request.setAttribute("hoaDonTra");
        model.addAttribute("khachHang", new TaiKhoan());
        request.setAttribute("lstHoaDon", hoaDonService.find5ByTrangThai(-1));
        request.setAttribute("lstHdct", hoaDonChiTietService.findAll());
        request.setAttribute("lstCtsp", chiTietSanPhamSerivce.fillAllDangHoatDongLonHon0());
        request.setAttribute("lstTaiKhoan", khachHangService.getAll());
        request.setAttribute("lstTaiKhoanDc",
                khachHangService.getById(hoaDonService.findById(id).getTaiKhoan().getId()));
        request.setAttribute("listVoucher", voucherService.fillAllDangDienRa());
        idhdc = id;
        request.setAttribute("lstLshd", lichSuHoaDonService.findByIdhd(idhdc));
        HoaDon hd = hoaDonService.findById(id);
        request.setAttribute("hoaDon", hd);
        checkVoucher();
        return "/admin-template/hoa-don-chi-tiet";
    }

    @PostMapping("/hoa-don/add")
    public String taoHoaDon(RedirectAttributes redirectAttributes) {
        addKhachLe();
        if (hoaDonService.countHoaDonTreo() < 5) {
            HoaDon hd = new HoaDon();
            // hd.setTaiKhoan(khachHangService.getById((long) 1));
            hd.setTrangThai(-1); // view 5 hoa don
            hd.setNgaySua(new Date());
            hd.setNgayTao(new Date());
            hd.setTaiKhoan(khachHangService.findKhachLe());
            hd.setPhiShip((long) 0);
            hd.setLoaiHoaDon(2);
            hd.setTongTien((long) 0);
            hd.setTongTienKhiGiam((long) 0);
            hoaDonService.saveOrUpdate(hd);
            hd.setMaHoaDon("HD" + hd.getId());
            hoaDonService.saveOrUpdate(hd);
            // HoaDon hddt = new HoaDon();
            // hddt.setTrangThai(8);
            // hddt.setMaHoaDon("HD" + hd.getId()+"-DOITRA");
            // hoaDonService.saveOrUpdate(hddt);
            idhdc = hd.getId();
            addLichSuHoaDon(idhdc, "", 0);
            thongBao(redirectAttributes, "Thành công", 1);
            return "redirect:/ban-hang-tai-quay/hoa-don/" + idhdc;
        }
        return "redirect:/ban-hang-tai-quay/hoa-don";
    }

    void thongBao(RedirectAttributes redirectAttributes, String thongBao, int trangThai) {
        if (trangThai == 0) {
            redirectAttributes.addFlashAttribute("checkThongBao", "thatBai");
            redirectAttributes.addFlashAttribute("thongBao", thongBao);
        } else if (trangThai == 1) {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            redirectAttributes.addFlashAttribute("thongBao", thongBao);
        } else {

            redirectAttributes.addFlashAttribute("checkThongBao", "canhBao");
            redirectAttributes.addFlashAttribute("thongBao", thongBao);
        }

    }

    @PostMapping("/hoa-don/delete/{id}")
    public String delete(RedirectAttributes redirectAttributes, @PathVariable Long id, @RequestParam String ghiChu) {
        HoaDon hd = hoaDonService.findById(id);
        hd.setTrangThai(5);
        hoaDonService.saveOrUpdate(hd);
        addLichSuHoaDon(idhdc, ghiChu, 5);
        thongBao(redirectAttributes, "Thành công", 1);
        if (hd.getTrangThai() == -1) {
            return "redirect:/ban-hang-tai-quay/hoa-don/" + idhdc;
        } else {
            return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + idhdc;
        }
    }

    Boolean checkVoucher() {
        HoaDon hd = hoaDonService.findById(idhdc);
        // hd.setNgaySua(new Date());
        Voucher voucher = hd.getVoucher();
        BigDecimal getTongTien = new BigDecimal(hd.tongTienHoaDon());
        if (voucher != null) {
            if (voucher.getGiaTriDonToiThieu().compareTo(getTongTien) == 1) {
                hd.setTongTien(hd.tongTienHoaDon());
                hd.setTongTienKhiGiam(hd.tongTienHoaDon());
                hd.setVoucher(null);
                hoaDonService.saveOrUpdate(hd);
                System.out.println("So sánh " + voucher.getGiaTriDonToiThieu() + ' ' + getTongTien);

            } else {
                hd.setTongTienKhiGiam(hd.tongTienHoaDon() + hd.getPhiShip() - hd.getGiamGia());
                hd.setTongTien(hd.tongTienHoaDon() + hd.getPhiShip());
                hoaDonService.saveOrUpdate(hd);
            }
        }
        return null;
    }

    @PostMapping("/hoa-don-chi-tiet/add")
    public String addHdct(@RequestParam Long idHoaDon, @RequestParam Long idCtsp,
            RedirectAttributes redirectAttributes) {

        Boolean cr = true;
        HoaDonChiTiet hdct = new HoaDonChiTiet();

        HoaDon hoaDon = hoaDonService.findById(idHoaDon);
        hoaDon.setNgaySua(new Date());
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
            if (ctsp.getSoLuong() > hdct.getSoLuong())
                hdct.setSoLuong(hdct.getSoLuong() + 1);
        }
        hdct.setTrangThai(0);
        hoaDonChiTietService.saveOrUpdate(hdct);
        System.out.println(idCtsp + "idctsp");
        System.out.println(idHoaDon + "idctsp");
        thongBao(redirectAttributes, "Thành công", 1);
        redirectAttributes.addFlashAttribute("batModal", "ok");
        if (hoaDon.getTrangThai() == -1) {
            return "redirect:/ban-hang-tai-quay/hoa-don/" + idhdc;
        } else {
            return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + idhdc;
        }
    }

    @PostMapping("/hoa-don-chi-tiet/tra-hang")
    public String traHang(@RequestParam(defaultValue = "") Integer soLuongEdit,
            @RequestParam(defaultValue = "") Integer soLuongEditTra, @RequestParam Long idHdct) {
        HoaDonChiTiet hdct = hoaDonChiTietService.findById(idHdct);
        HoaDon hd = hdct.getHoaDon();
        HoaDon hdTraHang = hoaDonService.findByMa("HD" + idhdc + "-DOITRA");
        System.out.println("mama" + hdTraHang.getMaHoaDon());
        Boolean checkUpdate = false;
        HoaDonChiTiet hdctnew;
        hd.setNgaySua(new Date());

        for (HoaDonChiTiet hdctf : hdTraHang.getLstHoaDonChiTiet()) {
            System.out.println("datimthay");
            System.out.println(hdctf.toString());
            if (hdctf.getChiTietSanPham().getId() == hdct.getChiTietSanPham().getId()) {
                checkUpdate = true;
                hdctnew = hdctf;
                hdctnew.setSoLuong(soLuongEditTra);
                hdctnew.setTrangThai(2);
                if (soLuongEditTra <= 0) {
                    hoaDonChiTietService.deleteById(hdctnew.getId());
                } else {
                    hoaDonChiTietService.saveOrUpdate(hdctnew);
                }

                break;
            }
        }

        if (!checkUpdate) {
            if (soLuongEditTra != 0) {
                hdctnew = new HoaDonChiTiet();
                hdctnew.setSoLuong(soLuongEditTra);
                hdctnew.setChiTietSanPham(hdct.getChiTietSanPham());
                hdctnew.setHoaDon(hdTraHang);
                hdctnew.setTrangThai(2);
                hdctnew.setDonGia(hdct.getDonGia());
                hoaDonChiTietService.saveOrUpdate(hdctnew);
            }
        }

        return "redirect:/ban-hang-tai-quay/doi-tra/HD" + idhdc;

    }

    @GetMapping("/hoa-don-chi-tiet/delete/{id}")
    public String deleteHdct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        // HoaDon hd = hoaDonService.findById(id);
        HoaDon hd = hoaDonService.findById(idhdc);
        hd.setNgaySua(new Date());
        hoaDonService.saveOrUpdate(hd);
        hoaDonChiTietService.deleteById(id);
        thongBao(redirectAttributes, "Thành công", 1);
        if (hd.getTrangThai() == -1) {
            return "redirect:/ban-hang-tai-quay/hoa-don/" + idhdc;
        } else {
            return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + idhdc;
        }

    }

    @PostMapping("/hoa-don-chi-tiet/update")
    public String updateSoLuong(RedirectAttributes redirectAttributes,
            @RequestParam(defaultValue = "") Integer soLuongEdit,
            @RequestParam(defaultValue = "") Integer soLuongEditTra, @RequestParam Long idHdct) {
        HoaDonChiTiet hdct = hoaDonChiTietService.findById(idHdct);
        HoaDon hd = hdct.getHoaDon();
        hd.setNgaySua(new Date());
        HoaDonChiTiet hdctnew = new HoaDonChiTiet();

        hdctnew.setSoLuong(0);
        System.out.println(hd.getTrangThai() + "tthd");
        if (hd.getTrangThai() == 3) {

            for (HoaDonChiTiet hdctf : hd.getLstHoaDonChiTiet()) {
                if (hdctf.getChiTietSanPham() == hdct.getChiTietSanPham() && hdctf.getTrangThai() == 2) {
                    hdctnew = hdctf;
                    break;
                }
            }

            hdct.setSoLuong(hdct.getSoLuong() - soLuongEditTra);
            hdctnew.setHoaDon(hdct.getHoaDon());
            hdctnew.setChiTietSanPham(hdct.getChiTietSanPham());
            hdctnew.setSoLuong(hdctnew.getSoLuong() + soLuongEditTra);
            hdctnew.setTrangThai(2);
            hdctnew.setDonGia(hdct.getDonGia());
            hoaDonChiTietService.saveOrUpdate(hdctnew);
            hoaDonChiTietService.saveOrUpdate(hdct);

            if (hd.getTrangThai() == -1) {
                thongBao(redirectAttributes, "Thành công", 1);
                return "redirect:/ban-hang-tai-quay/hoa-don/" + idhdc;
            } else {
                thongBao(redirectAttributes, "Thành công", 1);
                return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + idhdc;
            }
        }
        if (soLuongEdit == 0) {
            hoaDonChiTietService.deleteById(idHdct);
        } else {
            hdct.setSoLuong(soLuongEdit);
            hdct.setTrangThai(0);
            hoaDonChiTietService.saveOrUpdate(hdct);
        }

        if (hd.getTrangThai() == -1) {
            thongBao(redirectAttributes, "Thành công", 1);
            return "redirect:/ban-hang-tai-quay/hoa-don/" + idhdc;
        } else if (hd.getTrangThai() == 3) {
            thongBao(redirectAttributes, "Thành công", 1);
            return "redirect:/ban-hang-tai-quay/doi-tra/" + idhdc;

        } else {
            thongBao(redirectAttributes, "Thành công", 1);
            return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + idhdc;
        }
    }

    @PostMapping("/hoa-don/add-khach-hang")
    public String addKhachHang(@RequestParam Long idTaiKhoan, RedirectAttributes redirectAttributes) {
        HoaDon hd = hoaDonService.findById(idhdc);
        hd.setNgaySua(new Date());
        if (idTaiKhoan == -1) {
            hd.setTaiKhoan(khachHangService.findKhachLe());
            hd.setDiaChiNguoiNhan(null);
            hd.setThanhPho(null);
            hd.setQuanHuyen(null);
            hd.setPhuongXa(null);
            hd.setNguoiNhan(null);
            hd.setSdtNguoiNhan(null);
        } else {
            TaiKhoan kh = khachHangService.getById(idTaiKhoan);
            hd.setTaiKhoan(kh);
            hd.setNguoiNhan(kh.getHoVaTen());
            hd.setSdtNguoiNhan(kh.getSoDienThoai());

        }

        hoaDonService.saveOrUpdate(hd);
        thongBao(redirectAttributes, "Thành công", 1);
        return "redirect:/ban-hang-tai-quay/hoa-don/" + idhdc;
    }

    @GetMapping("/hoa-don/detail/{id}")
    public String detailHoaDon(@PathVariable Long id) {
        lstHoaDonCtDoiTra = new ArrayList<HoaDonChiTiet>();
        request.setAttribute("lstHoaDon", hoaDonService.find5ByTrangThai(-1));
        request.setAttribute("lstHdct", hoaDonChiTietService.findAll());
        request.setAttribute("lstCtsp", chiTietSanPhamSerivce.fillAllDangHoatDongLonHon0());
        request.setAttribute("lstTaiKhoan", khachHangService.getAll());
        request.setAttribute("lstTaiKhoanDc",
                khachHangService.getById(hoaDonService.findById(id).getTaiKhoan().getId()));
        request.setAttribute("listVoucher", voucherService.fillAllDangDienRa());
        idhdc = id;
        request.setAttribute("lstLshd", lichSuHoaDonService.findByIdhd(idhdc));
        request.setAttribute("listLichSuHoaDon", lichSuHoaDonService.findByIdhdNgaySuaAsc(idhdc));
        HoaDon hd = hoaDonService.findById(id);
        checkVoucher();
        request.setAttribute("hoaDon", hd);
        request.setAttribute("byHoaDon", hd);

        return "/admin-template/detail-hoa-don";
    }

    @PostMapping("/hoa-don/rollback/{id}")
    public String rollback(@RequestParam String ghiChu, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        HoaDon hd = hoaDonService.findById(id);

        hd.setNgaySua(new Date());
        for (LichSuHoaDon lichSuHoaDon : lichSuHoaDonService.findByIdhd(id)) {
            if (lichSuHoaDon.getTrangThai() == hd.getTrangThai()) {
                lichSuHoaDon.setTrangThai(lichSuHoaDon.getTrangThai() + 20);
                lichSuHoaDonService.saveOrUpdate(lichSuHoaDon);
                addLichSuHoaDon(id, ghiChu, 8);
            }
        }
        if (hd.getTrangThai() == 1 && hd.getLoaiHoaDon() == 2) {
            hd.setTrangThai(hd.getTrangThai() - 2);
        } else {
            if (hd.getTrangThai() == 5) {
                Integer tt = lichSuHoaDonService.findByIdhdNgaySuaAsc(idhdc).size() - 1;
                hd.setTrangThai(tt);
            } else {
                System.out.println("sizee------" + lichSuHoaDonService.findByIdhdNgaySuaAsc(idhdc).size());
                if (lichSuHoaDonService.findByIdhdNgaySuaAsc(idhdc).size() == 1) {
                    hd.setTrangThai(-1);

                } else {
                    hd.setTrangThai(hd.getTrangThai() - 1);
                }

            }

        }

        hoaDonService.saveOrUpdate(hd);
        if (hd.getTrangThai() == -1) {
            thongBao(redirectAttributes, "Thành công", 1);
            return "redirect:/ban-hang-tai-quay/hoa-don/" + idhdc;
        } else {
            thongBao(redirectAttributes, "Thành công", 1);
            return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + idhdc;
        }

    }

    @PostMapping("/hoa-don/xac-nhan")
    public String xacNhan(
            @RequestParam Long idHoaDon,
            @RequestParam String ghiChu,
            @RequestParam(defaultValue = "") String detail,
            @RequestParam Long phiShip2,
            @RequestParam Long giamGia,
            @RequestParam String voucherID, RedirectAttributes redirectAttributes) {

        HoaDon hd = hoaDonService.findById(idHoaDon);
        if (voucherID != "") {
            hd.setVoucher(voucherService.findById(Long.parseLong(voucherID)));
        }
        if (hd.getTrangThai() == 1) {
            updateSl(hd);
        }
        hd.setTrangThai(hd.getTrangThai() + 1);
        hd.setNgaySua(new Date());
        addLichSuHoaDon(idHoaDon, ghiChu, hd.getTrangThai());
        hoaDonService.saveOrUpdate(hd);
        System.out.println(ghiChu + "ghiChu");
        if (detail.equals("ok")) {
            hd.setTongTien(hd.tongTienHoaDon() + phiShip2);
            hd.setTongTienKhiGiam(hd.tongTienHoaDon() + phiShip2 - giamGia);
            hoaDonService.saveOrUpdate(hd);
            thongBao(redirectAttributes, "Thành công", 1);
            return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + idhdc;
        } else {
            return "redirect:/ban-hang-tai-quay/hoa-don/quan-ly";
        }

    }

    @PostMapping("/hoa-don/add-dia-chi")
    public String addDiaChi(@RequestParam Long idDiaChi, RedirectAttributes redirectAttributes) {
        System.out.println(idDiaChi + "==========");
        HoaDon hd = hoaDonService.findById(idhdc);
        hd.setNgaySua(new Date());
        DiaChi dc = diaChiService.getById(idDiaChi);
        hd.setDiaChiNguoiNhan(dc.getDiaChiCuThe());
        hd.setQuanHuyen(dc.getQuanHuyen());
        hd.setPhuongXa(dc.getPhuongXa());
        hd.setThanhPho(dc.getThanhPho());
        hoaDonService.saveOrUpdate(hd);
        thongBao(redirectAttributes, "Thành công", 1);
        if (hd.getTrangThai() == -1) {
            return "redirect:/ban-hang-tai-quay/hoa-don/" + idhdc;
        } else {
            return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + idhdc;
        }
    }

    @PostMapping("/hoa-don/update")
    public String updateHoaDon(@RequestParam Long phiShip,
            @RequestParam String inputHoVaTen, @RequestParam String inputSoDienThoai,
            @RequestParam String inputDcct, @RequestParam String inputGhiChu,
            @RequestParam(defaultValue = "") String thanhPho,
            @RequestParam(defaultValue = "") String quanHuyen, @RequestParam(defaultValue = "") String phuongXa) {
        HoaDon hd = hoaDonService.findById(idhdc);
        hd.setNgaySua(new Date());
        hd.setPhiShip(phiShip);
        hd.setSdtNguoiNhan(inputSoDienThoai);
        hd.setNguoiNhan(inputHoVaTen);
        hd.setDiaChiNguoiNhan(inputDcct);
        hd.setGhiChu(inputGhiChu);
        hd.setThanhPho(thanhPho);
        hd.setQuanHuyen(quanHuyen);
        hd.setPhuongXa(phuongXa);
        hoaDonService.saveOrUpdate(hd);
        return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + idhdc;
    }

    @GetMapping("/hoa-don/bo-voucher/{id}")
    public String boChonVoucher(@PathVariable Long id) {
        HoaDon hd = hoaDonService.findById(id);
        hd.setVoucher(null);
        hd.setTongTienKhiGiam(hd.tongTienHoaDonDaNhan() + hd.getPhiShip());
        hoaDonService.saveOrUpdate(hd);
        return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + idhdc;
    }

    @PostMapping("/doi-tra/xac-nhan")
    public String xacNhan(@RequestParam String lyDo) {
        addLichSuHoaDon(idhdc, lyDo, 6);
        System.out.println(idhdc + "-----hdc");
        HoaDon hdc = hoaDonService.findById(idhdc);
        hdc.setTrangThai(6);
        HoaDon hdDoiTra = hoaDonService.findByMa(hdc.getMaHoaDon() + "-DOITRA");
        for (HoaDonChiTiet hdctChinh : hdc.getLstHoaDonChiTiet()) {
            for (HoaDonChiTiet hdctDoiTra : hdDoiTra.getLstHoaDonChiTiet()) {
                if (hdctChinh.getChiTietSanPham().getId() == hdctDoiTra.getChiTietSanPham().getId()) {
                    HoaDonChiTiet hdct = new HoaDonChiTiet();
                    hdct.setDonGia(hdctDoiTra.getDonGia());
                    hdct.setSoLuong(hdctDoiTra.getSoLuong());
                    hdct.setTrangThai(2);
                    hdct.setHoaDon(hdc);
                    hdct.setChiTietSanPham(hdctChinh.getChiTietSanPham());
                    hoaDonChiTietService.saveOrUpdate(hdct);// tạo hdct mới dựa trên hd đôi trả

                    hdctChinh.setSoLuong(hdctChinh.getSoLuong() - hdctDoiTra.getSoLuong());// Trừ số lượng hdct chính
                                                                                           // dựa trên đổi trả
                    if (hdctChinh.getSoLuong() == 0) {
                        hoaDonChiTietService.deleteById(hdctChinh.getId());// nếu hết( soluong=0) xóa sp hdct chính
                    } else {
                        hoaDonChiTietService.saveOrUpdate(hdctChinh); // nếu còn trừ số lượng
                    }

                    hoaDonChiTietService.deleteById(hdctDoiTra.getId());// xong thì xóa hdct ở hoaDon đổi trả

                }
            }
        }

        hoaDonService.deleteById(hdDoiTra.getId()); // Xóa luôn hóa đơn đổi trả tạm
        return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + idhdc;
    }

    @PostMapping("/hoa-don/chuyen-nhanh")
    public String chuyenNhanh(
            @RequestParam Long idHoaDon,
            @RequestParam String ghiChu

    ) {

        HoaDon hd = hoaDonService.findById(idHoaDon);

        hd.setTrangThai(hd.getTrangThai() + 1);
        hd.setNgaySua(new Date());
        addLichSuHoaDon(idHoaDon, ghiChu, hd.getTrangThai());
        hoaDonService.saveOrUpdate(hd);

        System.out.println(ghiChu + "ghiChu");

        return "redirect:/ban-hang-tai-quay/hoa-don/quan-ly";
    }

    @PostMapping("/hoa-don/thanh-toan")
    public String thanhToan(@RequestParam(defaultValue = "off") String treo,
            @RequestParam(defaultValue = "off") String giaoHang, @RequestParam Long phiShip,
            @RequestParam Long giamGia, @RequestParam String inputHoVaTen, @RequestParam String inputSoDienThoai,
            @RequestParam String inputDcct, @RequestParam String inputGhiChu,
            @RequestParam(defaultValue = "") String thanhPho,
            @RequestParam(defaultValue = "") String quanHuyen, @RequestParam(defaultValue = "") String phuongXa,
            @RequestParam String voucherID, @RequestParam String ghiChuThanhToan,
            RedirectAttributes redirectAttributes) {
        thongBao(redirectAttributes, "Thành công", 1);
        HoaDon hd = hoaDonService.findById(idhdc);
        chiTietSanPhamSerivce.checkSoLuongBang0();
        hd.setNgaySua(new Date());
        System.out.println("ttttttttt" + thanhPho + quanHuyen + phuongXa);
        if (voucherID != "") {
            hd.setVoucher(voucherService.findById(Long.parseLong(voucherID)));
        }
        switch (hd.getTrangThai()) {
            case -1:
                if (treo.equals("on")) {
                    hd.setTrangThai(4);

                } else if (giaoHang.equals("on")) {
                    // Giao hàng
                    addLichSuHoaDon(hd.getId(), ghiChuThanhToan, 1);
                    hd.setTrangThai(1);
                    hd.setPhiShip(phiShip);
                    hd.setSdtNguoiNhan(inputSoDienThoai);
                    hd.setNguoiNhan(inputHoVaTen);
                    hd.setDiaChiNguoiNhan(inputDcct);
                    hd.setGhiChu(inputGhiChu);
                    hd.setThanhPho(thanhPho);
                    hd.setQuanHuyen(quanHuyen);
                    hd.setPhuongXa(phuongXa);

                } else {
                    // Hoàn thành
                    addLichSuHoaDon(hd.getId(), ghiChuThanhToan, 3);
                    hd.setTrangThai(3);
                    hd.setNgayThanhToan(new Date());
                    hd.setNgaySua(new Date());
                    hd.setTongTien(hd.tongTienHoaDon());
                    hd.setTongTienKhiGiam(hd.tongTienHoaDon() - giamGia);

                    if (hd.getNguoiNhan() == null) {
                        hd.setNguoiNhan("Khách lẻ");
                    }
                    updateSl(hd);
                }
                break;
            case 0:
                // xác nhận đơn
                addLichSuHoaDon(hd.getId(), ghiChuThanhToan, 1);
                hd.setTrangThai(1);
                hd.setNgaySua(new Date());

                break;
            case 1:
                // Giao hàng
                addLichSuHoaDon(hd.getId(), ghiChuThanhToan, 2);
                hd.setTrangThai(2);
                hd.setNgaySua(new Date());

                break;
            case 2:
                // Giao hàng thành công
                addLichSuHoaDon(hd.getId(), ghiChuThanhToan, 3);
                hd.setTrangThai(3);
                hd.setNgaySua(new Date());
                hd.setNgayThanhToan(new Date());
                System.out.println("updateSoLuong");

                updateSl(hd);
                break;
            case 3:
                addLichSuHoaDon(hd.getId(), ghiChuThanhToan, 7);
                HoaDon hdDoiTra = new HoaDon();
                hdDoiTra.setNguoiNhan(hd.getNguoiNhan());
                hdDoiTra.setEmailNguoiNhan(hd.getEmailNguoiNhan());
                hdDoiTra.setNgayTao(new Date());
                hdDoiTra.setNgaySua(new Date());
                hdDoiTra.setTaiKhoan(hd.getTaiKhoan());
                hdDoiTra.setQuanHuyen(hd.getQuanHuyen());
                hdDoiTra.setThanhPho(hd.getThanhPho());
                hdDoiTra.setPhuongXa(hd.getPhuongXa());
                hdDoiTra.setLoaiHoaDon(2);
                hdDoiTra.setTrangThai(7);
                hdDoiTra.setTongTien((long) 0);
                hdDoiTra.setTongTienKhiGiam((long) 0);
                hdDoiTra.setPhiShip((long) 0);
                hoaDonService.saveOrUpdate(hdDoiTra);
                hdDoiTra.setMaHoaDon("HD-DOITRA" + hdDoiTra.getId());
                hoaDonService.saveOrUpdate(hdDoiTra);
                for (HoaDonChiTiet hdctf : hd.getLstHoaDonChiTiet()) {
                    if (hdctf.getTrangThai() == 2) {
                        HoaDonChiTiet hdctn = hdctf;
                        hdctn.setHoaDon(hdDoiTra);
                        hoaDonChiTietService.saveOrUpdate(hdctn);
                    }
                }

                hd.setNgaySua(new Date());
                break;
            case 4:
                addLichSuHoaDon(hd.getId(), ghiChuThanhToan, 3);
                hd.setTrangThai(3);
                hd.setNgaySua(new Date());
                hd.setNgayThanhToan(new Date());
                updateSl(hd);
                break;
            case 6:
                hd.setTrangThai(7);
            default:
                break;

        }
        hd.setTongTien(hd.tongTienHoaDon() + phiShip);
        hd.setTongTienKhiGiam(hd.tongTienHoaDon() + phiShip - giamGia);
        hoaDonService.saveOrUpdate(hd);

        return "redirect:/ban-hang-tai-quay/hoa-don/detail/" + idhdc;
    }

    private void updateSl(HoaDon hd) {
        List<HoaDonChiTiet> lstHdct = hoaDonService.findById(idhdc).getLstHoaDonChiTiet();
        for (HoaDonChiTiet hdct : lstHdct) {
            Long idid = hdct.getChiTietSanPham().getId();
            ChiTietSanPham ctsp = chiTietSanPhamSerivce.getById(idid);
            ctsp.setSoLuong(ctsp.getSoLuong() - hdct.getSoLuong());
            chiTietSanPhamSerivce.update(ctsp);
            if (ctsp.getSoLuong() == 0) {
                ctsp.setTrangThai(1);
                chiTietSanPhamSerivce.update(ctsp);
            }
        }
    }

    @GetMapping("/hoa-don/quan-ly")
    public String quanLyHoaDon() {
        request.setAttribute("lstHdctAll", hoaDonService.findAllOrderByNgaySua());
        request.setAttribute("lstHdChoXacNhan", hoaDonService.find5ByTrangThai(0));
        request.setAttribute("lstHdChoGiao", hoaDonService.find5ByTrangThai(1));
        request.setAttribute("lstHdDangGiao", hoaDonService.find5ByTrangThai(2));
        request.setAttribute("lstHdHoanThanh", hoaDonService.find5ByTrangThai(3));
        request.setAttribute("lstHdChoThanhToan", hoaDonService.find5ByTrangThai(4));
        request.setAttribute("lstHdHuy", hoaDonService.find5ByTrangThai(5));
        request.setAttribute("lstHdTra", hoaDonService.find5ByTrangThai(6));

        return "/admin-template/hoa-don";
    }

    @GetMapping("/hoa-don/exportPdf/{id}")
    public String exportPdf(@PathVariable Long id) throws Exception {
        ExportPdf ex = new ExportPdf();
        HoaDon hd = hoaDonService.findById(id);

        // ex.exportToPDF(hd.getLstHoaDonChiTiet(), hd, (long) 30000);

        System.out.println("catch in hoa don");

        return "/admin-template/in-hoa-don";
    }

    public void addLichSuHoaDon(Long idHoaDon, String ghiChu, Integer trangThai) {
        HoaDon hd = hoaDonService.findById(idHoaDon);
        LichSuHoaDon lshd = new LichSuHoaDon();
        lshd.setHoaDon(hd);
        lshd.setGhiChu(ghiChu);
        lshd.setTrangThai(trangThai);
        // lshd.setNgayTao(new Date());
        lshd.setNgaySua(new Date());
        TaiKhoan tk = nhanVienService.getById(idTk);
        System.out.println(tk + "====================");
        lshd.setNguoiSua(tk.getHoVaTen());
        lichSuHoaDonService.saveOrUpdate(lshd);
    }

    @PostMapping("/khach-hang/them-nhanh")
    public String add(@ModelAttribute("khachHang") TaiKhoan taiKhoan,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request,
            @RequestParam("email") String email) {
        String random3 = ranDom1();
        TaiKhoan userInfo = taiKhoan;
        TaiKhoan taiKhoanEntity = new TaiKhoan();
        taiKhoanEntity.setNgaySinh(taiKhoan.getNgaySinh());
        if (!taiKhoanEntity.isValidNgaySinh()) {
            redirectAttributes.addFlashAttribute("checkModal", "modal");
            redirectAttributes.addFlashAttribute("checkThongBao", "thaiBai");
            redirectAttributes.addFlashAttribute("checkNgaySinh", "ngaySinh");
            return "redirect:/ban-hang-tai-quay/hoa-don/" + idhdc;
        } else if (!khachHangService.checkTenTaiKhoanTrung(taiKhoan.getTenTaiKhoan())) {
            redirectAttributes.addFlashAttribute("checkModal", "modal");
            redirectAttributes.addFlashAttribute("checkThongBao", "thaiBai");
            redirectAttributes.addFlashAttribute("checkTenTrung", "Tên tài khoản đã tồn tại");
            redirectAttributes.addFlashAttribute("checkEmailTrung", "Email đã tồn tại");
            return "redirect:/ban-hang-tai-quay/hoa-don/" + idhdc;
        } else if (!khachHangService.checkEmail(taiKhoan.getEmail())) {
            redirectAttributes.addFlashAttribute("checkModal", "modal");
            redirectAttributes.addFlashAttribute("checkThongBao", "thaiBai");
            redirectAttributes.addFlashAttribute("checkEmailTrung", "Email đã tồn tại");
            return "redirect:/ban-hang-tai-quay/hoa-don/" + idhdc;
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            String url = request.getRequestURL().toString();
            System.out.println(url);
            url = url.replace(request.getServletPath(), "");
            khachHangService.sendEmail(userInfo, url, random3);
            System.out.println(userInfo);
            userInfo.setNgayTao(new Date());
            userInfo.setNgaySua(new Date());
            userInfo.setMatKhau(passwordEncoder.encode(random3));
            VaiTro vaiTro = new VaiTro();
            vaiTro.setId(Long.valueOf(2));
            userInfo.setVaiTro(vaiTro);
            userInfo.setTrangThai(0);
            userInfo.setVaiTro(vaiTro);
            khachHangService.update(userInfo);

            GioHang gioHang = new GioHang();
            gioHang.setMaGioHang("GH" + gioHangService.genMaTuDong());
            gioHang.setGhiChu("");
            gioHang.setNgaySua(new Date());
            gioHang.setNgayTao(new Date());
            gioHang.setTaiKhoan(TaiKhoan.builder().id(userInfo.getId()).build());
            gioHang.setTrangThai(0);
            gioHangService.save(gioHang);

            return "redirect:/ban-hang-tai-quay/hoa-don/" + idhdc;
        }
    }

    public String ranDom1() {
        // Khai báo một mảng chứa 6 số nguyên ngẫu nhiên
        String ran = "";
        int[] randomNumbers = new int[6];

        // Tạo một đối tượng Random
        Random random = new Random();

        // Đổ số nguyên ngẫu nhiên vào mảng
        for (int i = 0; i < 6; i++) {
            randomNumbers[i] = random.nextInt(100); // Giới hạn số ngẫu nhiên từ 0 đến 99
        }

        // In ra các số nguyên ngẫu nhiên trong mảng
        System.out.println("Dãy 6 số nguyên ngẫu nhiên:");
        for (int number : randomNumbers) {
            ran = ran + number;
            System.out.println(number);
        }
        return ran;
    }

}
