package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.entity.ChiTietSanPham;
import com.example.bedatnsd47.service.ChiTietSanPhamSerivce;
import com.example.bedatnsd47.service.KichCoService;
import com.example.bedatnsd47.service.LoaiDeService;
import com.example.bedatnsd47.service.MauSacService;
import com.example.bedatnsd47.service.SanPhamSerivce;
import com.example.bedatnsd47.service.ThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/san-pham-chi-tiet")
public class ChiTietSanPhamController {

    @Autowired
    private ChiTietSanPhamSerivce chiTietSanPhamSerivce;

    @Autowired
    private SanPhamSerivce sanPhamSerivce;

    @Autowired
    private ThuongHieuService thuongHieuService;

    @Autowired
    private KichCoService kichCoService;

    @Autowired
    private MauSacService mauSacService;

    @Autowired
    private LoaiDeService loaiDeService;

    @GetMapping()
    public String hienThi(
            Model model) {
        model.addAttribute("listChiTietSP", chiTietSanPhamSerivce.getAll());
        model.addAttribute("listSanPham", sanPhamSerivce.getAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listLoaiDe", loaiDeService.findAll());
        return "/admin-template/san_pham_chi_tiet/san-pham-chi-tiet";
    }

    @GetMapping("/dang-hoat-dong")
    public String hienThiAll(
            Model model) {
        model.addAttribute("listChiTietSP", chiTietSanPhamSerivce.getAllDangHoatDong());
        model.addAttribute("listSanPham", sanPhamSerivce.getAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listLoaiDe", loaiDeService.findAll());
        return "/admin-template/san_pham_chi_tiet/san-pham-chi-tiet";
    }

    @GetMapping("/ngung-hoat-dong")
    public String hienThiNgungHoatDong(
            Model model) {
        model.addAttribute("listChiTietSP", chiTietSanPhamSerivce.getAllNgungHoatDong());
        model.addAttribute("listSanPham", sanPhamSerivce.getAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listLoaiDe", loaiDeService.findAll());
        return "/admin-template/san_pham_chi_tiet/san-pham-chi-tiet";
    }

    @PostMapping("/add")
    public String add(
//            @RequestParam(value = "multipleKichCo") String[] multipleKichCo,
//            @RequestParam(value = "multipleMauSac,") String[] multipleMauSac,
            @RequestParam("listSanPham") List<String> listSanPham,
            @RequestParam("listKichCo") List<String> listKichCo,
            @RequestParam("listMauSac") List<String> listMauSac,
            @RequestParam("listLoaiDe") List<String> listLoaiDe,
            @RequestParam("listSoLuong") List<String> listSoLuong,
            @RequestParam("listDonGia") List<String> listDonGia,
            Model model

    ) {
//        if (multipleKichCo != null && multipleKichCo.length > 0){
//            for (String kichCo : multipleKichCo) {
//                System.out.println("Giá trị đã chọn: " + kichCo);
//            }
//            model.addAttribute("checkTab","true");
//            model.addAttribute("checkThongBao", "thaiBai");
//            model.addAttribute("listKichCo", kichCoService.findAll());
//            model.addAttribute("listMauSac", mauSacService.findAll());
//            model.addAttribute("listLoaiDe", loaiDeService.findAll());
//            model.addAttribute("listSanPham", sanPhamSerivce.getAll());
//            return "/admin-template/san_pham_chi_tiet/san-pham-chi-tiet";
//        } else {
//            // Không có giá trị hoặc giá trị là null
//            // Thực hiện xử lý khi không có giá trị hoặc giá trị là null
//            model.addAttribute("checkTab","true");
//            model.addAttribute("checkThongBao", "thaiBai");
//            System.out.println("Không có giá trị hoặc giá trị là null");
//        }
        if (listDonGia.isEmpty()||listDonGia.size()==0|listDonGia==null||listSoLuong.isEmpty()||listSoLuong.size()==0|listSoLuong==null) {
            model.addAttribute("checkTab","true");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listKichCo", kichCoService.findAll());
            model.addAttribute("listMauSac", mauSacService.findAll());
            model.addAttribute("listLoaiDe", loaiDeService.findAll());
            model.addAttribute("listSanPham", sanPhamSerivce.getAll());

            return "/admin-template/san_pham_chi_tiet/san-pham-chi-tiet";

        }
        chiTietSanPhamSerivce.add(listSanPham, listKichCo, listMauSac, listLoaiDe, listSoLuong, listDonGia);
        return "redirect:/admin/san-pham-chi-tiet";
    }

}
