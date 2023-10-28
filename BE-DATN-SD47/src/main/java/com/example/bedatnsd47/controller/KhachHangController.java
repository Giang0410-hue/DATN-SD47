package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.entity.TaiKhoan;
import com.example.bedatnsd47.entity.VaiTro;
import com.example.bedatnsd47.service.KhachHangService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
@RequestMapping("/admin/khach-hang")
public class KhachHangController {
    @Autowired
    KhachHangService taiKhoanService;

    private Integer pageNo = 0;

    private Integer trangThai = 1;

    private Date currentDate = new Date();

//    private Long vaiTro_id = 1;

    @GetMapping("/pre")
    public String hienThiPre() {
        pageNo--;
        pageNo = taiKhoanService.checkPageNo(pageNo);
        return "redirect:/admin/thuong-hieu";
    }

    @GetMapping("/next")
    public String hienThiNext() {
        pageNo++;
        pageNo = taiKhoanService.checkPageNo(pageNo);
        return "redirect:/admin/thuong-hieu";
    }

    @GetMapping()
    public String hienThi(Model model) {
        model.addAttribute("listTaiKhoan", taiKhoanService.getPage(pageNo).stream().toList());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("khachHang", new TaiKhoan());
        return "/admin-template/khach_hang/khach-hang";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("khachHang") TaiKhoan taiKhoan, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listKhachHang", taiKhoanService.getPage(pageNo).stream().toList());
            model.addAttribute("currentPage", pageNo);
//            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            return "/admin-template/khach_hang/khach-hang";
        } else if (!taiKhoanService.checkTenTaiKhoanTrung(taiKhoan.getTen_tai_khoan())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Tên tài khoản phẩm đã tồn tại");
            model.addAttribute("checkEmailTrung", "Email phẩm đã tồn tại");
            model.addAttribute("listKhachHang", taiKhoanService.getPage(pageNo).stream().toList());
            model.addAttribute("index", pageNo + 1);
            model.addAttribute("currentPage", pageNo);
            return "/admin-template/khach_hang/khach-hang";
        } else if (!taiKhoanService.checkEmail(taiKhoan.getEmail())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkEmailTrung", "Email phẩm đã tồn tại");
            model.addAttribute("listKhachHang", taiKhoanService.getPage(pageNo).stream().toList());
            model.addAttribute("index", pageNo + 1);
            model.addAttribute("currentPage", pageNo);
            return "/admin-template/khach_hang/khach-hang";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            taiKhoan.setNgayTao(currentDate);
            VaiTro vaiTro = new VaiTro();
            vaiTro.setId(Long.valueOf(1));
            taiKhoan.setVaiTro(vaiTro);
            taiKhoan.setTrangThai(0);
            taiKhoanService.update(taiKhoan);
            return "redirect:/admin/khach-hang";
        }
    }


    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") Long id) {
        TaiKhoan taiKhoan = taiKhoanService.getById(id);
        model.addAttribute("listKhachHang", taiKhoanService.getAll());
        model.addAttribute("khachHang", taiKhoan);
        return "/admin-template/khach_hang/sua-khach-hang";
    }


    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("khachHang") TaiKhoan taiKhoan
            , BindingResult result, Model model, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listKhachHang", taiKhoanService.getPage(pageNo).stream().toList());
            model.addAttribute("currentPage", pageNo);
//            model.addAttribute("listThuongHieu", thuongHieuService.findAll());
            return "/admin-template/khach_hang/sua-khach-hang";
        } else if (!taiKhoanService.checkTenTkTrungSua(taiKhoan.getId(), taiKhoan.getTen_tai_khoan())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Tên tài khoản phẩm đã tồn tại");
            model.addAttribute("checkEmailTrung", "Email phẩm đã tồn tại");
            model.addAttribute("listKhachHang", taiKhoanService.getPage(pageNo).stream().toList());
            model.addAttribute("index", pageNo + 1);
            model.addAttribute("currentPage", pageNo);
            return "/admin-template/khach_hang/sua-khach-hang";
        } else if (!taiKhoanService.checkEmailSua(taiKhoan.getId(), taiKhoan.getEmail())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkEmailTrung", "Email phẩm đã tồn tại");
            model.addAttribute("listKhachHang", taiKhoanService.getPage(pageNo).stream().toList());
            model.addAttribute("index", pageNo + 1);
            model.addAttribute("currentPage", pageNo);
            return "/admin-template/khach_hang/khach-hang";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");

            taiKhoan.setNgayTao(taiKhoan.getNgayTao());
            taiKhoan.setNgayTao(currentDate);
            VaiTro vaiTro = new VaiTro();
            vaiTro.setId(Long.valueOf(1));
            taiKhoan.setVaiTro(vaiTro);
            System.out.println("Vai tro --------------------------" + vaiTro.getId());
            taiKhoan.setVaiTro(vaiTro);
            taiKhoan.setTrangThai(taiKhoan.getTrangThai());
            taiKhoanService.update(taiKhoan);
            return "redirect:/admin/khach-hang";
        }
    }
}
