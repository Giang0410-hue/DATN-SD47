package com.example.bedatnsd47.controller;

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

    private Integer pageNo = 0;

    private Date currentDate = new Date();


    @GetMapping()
    public String hienThi(
            Model model
    ) {
        model.addAttribute("listSanPham", sanPhamSerivce.getPage(pageNo).stream().toList());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("listThuongHieu", thuongHieuService.findAll());
        model.addAttribute("sanPham", new SanPham());
        return "/admin-template/san_pham/san-pham";
    }

    @GetMapping("/pre")
    public String hienThiPre(
    ) {
        pageNo--;
        pageNo = sanPhamSerivce.checkPageNo(pageNo);
        return "redirect:/admin/san-pham";
    }

    @GetMapping("/next")
    public String hienThiNext(
    ) {
        pageNo++;
        pageNo = sanPhamSerivce.checkPageNo(pageNo);
        return "redirect:/admin/san-pham";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(
            Model model,
            @PathVariable("id") Long id
    ) {
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
            sanPham.setNgaySua(currentDate);
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
            model.addAttribute("listSanPham", sanPhamSerivce.getPage(pageNo).stream().toList());
            model.addAttribute("index", pageNo + 1);
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            return "/admin-template/san_pham/san-pham";
        } else if (!sanPhamSerivce.checkTenTrung(sanPham.getTen())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Tên sản phẩm đã tồn tại");
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("listSanPham", sanPhamSerivce.getPage(pageNo).stream().toList());
            model.addAttribute("index", pageNo + 1);
            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            return "/admin-template/san_pham/san-pham";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            sanPham.setMa("SP" + sanPhamSerivce.genMaTuDong());
            sanPham.setNgayTao(currentDate);
            sanPham.setTrangThai(0);
            hinhAnhSanPhamSerivce.saveImage(multipartFiles, sanPham);
            sanPhamSerivce.add(sanPham);
            return "redirect:/admin/san-pham";
        }
    }
}
