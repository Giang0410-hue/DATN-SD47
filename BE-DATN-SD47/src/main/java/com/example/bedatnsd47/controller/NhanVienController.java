package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.entity.DiaChi;
import com.example.bedatnsd47.entity.TaiKhoan;
import com.example.bedatnsd47.entity.VaiTro;
import com.example.bedatnsd47.service.DiaChiService;
import com.example.bedatnsd47.service.KhachHangService;
import com.example.bedatnsd47.service.NhanVienService;
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
import java.util.List;

@Controller
@RequestMapping("/admin/nhan-vien")
public class NhanVienController {
    @Autowired
    NhanVienService taiKhoanService;


    @Autowired
    DiaChiService diaChiService;

    private Date currentDate = new Date();

    @GetMapping()
    public String hienThi(Model model) {
        model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
        model.addAttribute("nhanVien", new TaiKhoan());
        return "/admin-template/nhan_vien/nhan-vien";
    }

    @GetMapping("/dang-hoat-dong")
    public String hienThiDangHoatDong(Model model) {
        model.addAttribute("listTaiKhoan", taiKhoanService.getAllDangHoatDong());
        model.addAttribute("nhanVien", new TaiKhoan());
        return "/admin-template/nhan_vien/nhan-vien";
    }

    @GetMapping("/ngung-hoat-dong")
    public String hienThiNgungHoatDong(Model model) {
        model.addAttribute("listTaiKhoan", taiKhoanService.getAllNgungHoatDong());
        model.addAttribute("nhanVien", new TaiKhoan());
        return "/admin-template/nhan_vien/nhan-vien";
    }

    //
    @GetMapping("/view-update-nhan-vien/{id}")
    public String viewUpdate(
            Model model,
            @PathVariable("id") Long id
    ) {
//        List<DiaChi> listDiaChi = diaChiService.getAllByTaiKhoan(id);
        TaiKhoan taiKhoan = taiKhoanService.getById(id);
//        model.addAttribute("listDiaChi", listDiaChi);
//        model.addAttribute("idKhachHangUpdate", id);

//        check button add
//        model.addAttribute("checkAdd", "add");

//        model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
        model.addAttribute("nhanVien", taiKhoan);
//        model.addAttribute("diaChi", new DiaChi());
        return "/admin-template/nhan_vien/sua-nhan-vien";

    }

    @PostMapping("/update")
    public String update(
            @Valid
            @ModelAttribute("nhanVien") TaiKhoan taiKhoan,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        TaiKhoan taiKhoanEntity = new TaiKhoan();
        taiKhoanEntity.setNgaySinh(taiKhoan.getNgaySinh());
        if (result.hasErrors()) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
            return "/admin-template/nhan_vien/sua-nhan-vien";
        } else if (!taiKhoanEntity.isValidNgaySinh()) {
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkNgaySinh", "ngaySinh");
            return "/admin-template/nhan_vien/sua-nhan-vien";
        } else if (!taiKhoanService.checkTenTkTrungSua(taiKhoan.getId(), taiKhoan.getTenTaiKhoan())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Tên tài khoản phẩm đã tồn tại");
            model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
            return "/admin-template/nhan_vien/sua-nhan-vien";
        } else if (!taiKhoanService.checkEmailSua(taiKhoan.getId(), taiKhoan.getEmail())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
            return "/admin-template/nhan_vien/sua-nhan-vien";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            taiKhoan.setNgaySua(currentDate);
            VaiTro vaiTro = new VaiTro();
            vaiTro.setId(Long.valueOf(2));
            taiKhoan.setVaiTro(vaiTro);
            taiKhoan.setTrangThai(taiKhoan.getTrangThai());
            taiKhoanService.update(taiKhoan);
            return "redirect:/admin/nhan-vien";
        }
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("nhanVien") TaiKhoan taiKhoan,
                      BindingResult result,
                      Model model,
                      RedirectAttributes redirectAttributes
    ) {
        TaiKhoan taiKhoanEntity = new TaiKhoan();
        taiKhoanEntity.setNgaySinh(taiKhoan.getNgaySinh());
        if (result.hasErrors()) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
            return "/admin-template/nhan_vien/nhan-vien";
        } else if (!taiKhoanEntity.isValidNgaySinh()) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkNgaySinh", "ngaySinh");
            model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
            return "/admin-template/nhan_vien/nhan-vien";
        } else if (!taiKhoanService.checkTenTaiKhoanTrung(taiKhoan.getTenTaiKhoan())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Tên tài khoản phẩm đã tồn tại");
            model.addAttribute("checkEmailTrung", "Email phẩm đã tồn tại");
            model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
            return "/admin-template/nhan_vien/nhan-vien";
        } else if (!taiKhoanService.checkEmail(taiKhoan.getEmail())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkEmailTrung", "Email phẩm đã tồn tại");
            model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
            return "/admin-template/nhan_vien/nhan-vien";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            taiKhoan.setNgayTao(currentDate);
            taiKhoan.setNgaySua(currentDate);
            VaiTro vaiTro = new VaiTro();
            vaiTro.setId(Long.valueOf(2));
            taiKhoan.setVaiTro(vaiTro);
            taiKhoan.setTrangThai(0);
            taiKhoanService.update(taiKhoan);
            return "redirect:/admin/nhan-vien";
        }
    }
}
