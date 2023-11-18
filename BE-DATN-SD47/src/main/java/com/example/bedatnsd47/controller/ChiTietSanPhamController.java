package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.entity.ChiTietSanPham;
import com.example.bedatnsd47.entity.SanPham;
import com.example.bedatnsd47.service.ChiTietSanPhamSerivce;
import com.example.bedatnsd47.service.HinhAnhSanPhamSerivce;
import com.example.bedatnsd47.service.KichCoService;
import com.example.bedatnsd47.service.LoaiDeService;
import com.example.bedatnsd47.service.MauSacService;
import com.example.bedatnsd47.service.SanPhamSerivce;
import com.example.bedatnsd47.service.ThuongHieuService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/san-pham-chi-tiet")
public class ChiTietSanPhamController {

    @Autowired
    private ChiTietSanPhamSerivce chiTietSanPhamSerivce;

    @Autowired
    private SanPhamSerivce sanPhamSerivce;

    @Autowired
    private HinhAnhSanPhamSerivce hinhAnhSanPhamSerivce;

    @Autowired
    private ThuongHieuService thuongHieuService;

    @Autowired
    private KichCoService kichCoService;

    @Autowired
    private MauSacService mauSacService;

    @Autowired
    private LoaiDeService loaiDeService;

    private Date currentDate = new Date();

    private Model getString(Model model) {
        model.addAttribute("listSanPham", sanPhamSerivce.getAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("listLoaiDe", loaiDeService.findAll());
        return model;
    }

    @GetMapping()
    public String hienThi(
            Model model) {
        model.addAttribute("listChiTietSP", chiTietSanPhamSerivce.getAllCtspOneSanPham());
        getString(model);
        model.addAttribute("sanPham", new SanPham());
        return "/admin-template/san_pham_chi_tiet/san-pham-chi-tiet";
    }

    @GetMapping("/ngung-hoat-dong")
    public String hienThiNgungHoatDong(
            Model model) {
        model.addAttribute("listChiTietSP", chiTietSanPhamSerivce.getAllNgungHoatDong());
        getString(model);
        model.addAttribute("sanPham", new SanPham());
        return "/admin-template/san_pham_chi_tiet/san-pham-chi-tiet";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(
            @PathVariable("id") Long id,
            Model model
    ) {
        SanPham sanPham = sanPhamSerivce.getById(id);
        List<ChiTietSanPham> listChiTietSP = chiTietSanPhamSerivce.getAllCtspByIdSanPham(id);
        model.addAttribute("sanPhamDetail", sanPham);
        model.addAttribute("listChiTietSP", listChiTietSP);
        getString(model);
        return "/admin-template/san_pham_chi_tiet/sua-san-pham-chi-tiet";
    }

    @PostMapping("/add-san-pham")
    public String addSanPham(@Valid
                             @ModelAttribute("sanPham") SanPham sanPham,
                             BindingResult result,
                             Model model,
                             @RequestParam("fileImage") List<MultipartFile> multipartFiles,
                             RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            model.addAttribute("checkTab", "true");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkModal", "true");
            model.addAttribute("listChiTietSP", chiTietSanPhamSerivce.getAllCtspOneSanPham());
            getString(model);

            return "/admin-template/san_pham_chi_tiet/san-pham-chi-tiet";
        } else if (!sanPhamSerivce.checkTenTrung(sanPham.getTen())) {
            model.addAttribute("checkTab", "true");
            model.addAttribute("checkModal", "true");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Tên sản phẩm đã tồn tại");
            model.addAttribute("listChiTietSP", chiTietSanPhamSerivce.getAllCtspOneSanPham());
            getString(model);
            return "/admin-template/san_pham_chi_tiet/san-pham-chi-tiet";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            redirectAttributes.addFlashAttribute("checkTab", "true");
            sanPham.setMa("SP" + sanPhamSerivce.genMaTuDong());
            sanPham.setNgayTao(currentDate);
            sanPham.setNgaySua(currentDate);
            sanPham.setTrangThai(0);
            sanPhamSerivce.add(sanPham);

            hinhAnhSanPhamSerivce.saveImage(multipartFiles, sanPham);
            return "redirect:/admin/san-pham-chi-tiet";
        }
    }

    @PostMapping("/update")
    public String update(
            @RequestParam("listIdChiTietSp") List<String> listIdChiTietSp,
            @RequestParam("listSanPham") List<String> listSanPham,
            @RequestParam("listKichCo") List<String> listKichCo,
            @RequestParam("listMauSac") List<String> listMauSac,
            @RequestParam("listLoaiDe") List<String> listLoaiDe,
            @RequestParam("listTrangThai") List<String> listTrangThai,
            @RequestParam("listSoLuong") List<String> listSoLuong,
            @RequestParam("listDonGia") List<String> listDonGia,
            RedirectAttributes attributes

    ) {
        attributes.addFlashAttribute("checkThongBao", "thanhCong");
        chiTietSanPhamSerivce.updateAllCtsp(listIdChiTietSp, listSanPham, listKichCo, listMauSac,
                listLoaiDe, listTrangThai, listSoLuong, listDonGia);
        return "redirect:/admin/san-pham-chi-tiet";
    }

    @PostMapping("/add")
    public String add(
            @RequestParam("listSanPham") List<String> listSanPham,
            @RequestParam("listKichCo") List<String> listKichCo,
            @RequestParam("listMauSac") List<String> listMauSac,
            @RequestParam("listLoaiDe") List<String> listLoaiDe,
            @RequestParam("listSoLuong") List<String> listSoLuong,
            @RequestParam("listDonGia") List<String> listDonGia,
            RedirectAttributes attributes
    ) {
        attributes.addFlashAttribute("checkThongBao", "thanhCong");
        chiTietSanPhamSerivce.add(listSanPham, listKichCo, listMauSac, listLoaiDe, listSoLuong, listDonGia);
        return "redirect:/admin/san-pham-chi-tiet";
    }

}
