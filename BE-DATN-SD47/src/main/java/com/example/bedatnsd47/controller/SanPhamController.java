package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.config.PrincipalCustom;
import com.example.bedatnsd47.config.UserInfoUserDetails;
import com.example.bedatnsd47.entity.HinhAnhSanPham;
import com.example.bedatnsd47.entity.SanPham;
import com.example.bedatnsd47.repository.HinhAnhSanPhamRepository;
import com.example.bedatnsd47.service.HinhAnhSanPhamSerivce;
import com.example.bedatnsd47.service.SanPhamSerivce;
import com.example.bedatnsd47.service.ThuongHieuService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/san-pham")
public class SanPhamController {

    @Autowired
    private SanPhamSerivce sanPhamSerivce;

    @Autowired
    private ThuongHieuService thuongHieuService;

    @Autowired
    private HinhAnhSanPhamSerivce hinhAnhSanPhamSerivce;

    private PrincipalCustom principalCustom = new PrincipalCustom();

    @GetMapping()
    public String hienThi(
            Model model
    ) {
        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
        if (name != null) {
            model.addAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
        } else {
            return "redirect:/login";
        }
        model.addAttribute("listSanPham", sanPhamSerivce.getAll());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("sanPham", new SanPham());
        return "/admin-template/san_pham/san-pham";
    }

    @GetMapping("/dang-hoat-dong")
    public String hienThiAll(
            Model model
    ) {
        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
        if (name != null) {
            model.addAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
        } else {
            return "redirect:/login";
        }
        model.addAttribute("listSanPham", sanPhamSerivce.getAllDangHoatDong());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("sanPham", new SanPham());
        return "/admin-template/san_pham/san-pham";
    }

    @GetMapping("/ngung-hoat-dong")
    public String hienThiNgungHoatDong(
            Model model
    ) {
        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
        if (name != null) {
            model.addAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
        } else {
            return "redirect:/login";
        }
        model.addAttribute("listSanPham", sanPhamSerivce.getAllNgungHoatDong());
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("sanPham", new SanPham());
        return "/admin-template/san_pham/san-pham";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(
            Model model,
            @PathVariable("id") Long id
    ) {
        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
        if (name != null) {
            model.addAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
        } else {
            return "redirect:/login";
        }
        SanPham sanPham = sanPhamSerivce.getById(id);
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("listHinhAnh", hinhAnhSanPhamSerivce.listHinhAnh(id));
        model.addAttribute("sanPham", sanPham);
        return "/admin-template/san_pham/sua-san-pham";
    }

    @PostMapping("/update")
    public String update(@Valid
                         @ModelAttribute("sanPham") SanPham sanPham,
                         BindingResult result,
                         Model model,
                         @RequestParam("fileImage") List<MultipartFile> multipartFiles,
                         RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            return "/admin-template/san_pham/sua-san-pham";
        } else if (!sanPhamSerivce.checkTenTrungSua(sanPham.getMa(), sanPham.getTen())) {
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Tên sản phẩm đã tồn tại");
            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            return "/admin-template/san_pham/sua-san-pham";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            SanPham sp = sanPhamSerivce.getById(sanPham.getId());
            sanPham.setNgayTao(sp.getNgayTao());
            sanPham.setNgaySua(new Date());
            for (MultipartFile file : multipartFiles) {
                if (file != null && !file.isEmpty()) {
                    hinhAnhSanPhamSerivce.deleteByID(sanPham.getId());
                }
            }
            hinhAnhSanPhamSerivce.saveWhenEditingImage(multipartFiles, sanPham.getId());
            sanPhamSerivce.update(sanPham);
            return "redirect:/admin/san-pham";
        }
    }

    @PostMapping("/add")
    public String add(@Valid
                      @ModelAttribute("sanPham") SanPham sanPham,
                      BindingResult result,
                      Model model,
                      @RequestParam("fileImage") List<MultipartFile> multipartFiles,
                      RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listSanPham", sanPhamSerivce.getAll());
            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            return "/admin-template/san_pham/san-pham";
        } else if (!sanPhamSerivce.checkTenTrung(sanPham.getTen())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Tên sản phẩm đã tồn tại");
            model.addAttribute("listSanPham", sanPhamSerivce.getAll());
            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            return "/admin-template/san_pham/san-pham";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            sanPham.setMa("SP" + sanPhamSerivce.genMaTuDong());
            sanPham.setNgayTao(new Date());
            sanPham.setNgaySua(new Date());
            sanPham.setTrangThai(0);
            sanPhamSerivce.add(sanPham);

            hinhAnhSanPhamSerivce.saveImage(multipartFiles,sanPham);
            return "redirect:/admin/san-pham";
        }
    }
}
