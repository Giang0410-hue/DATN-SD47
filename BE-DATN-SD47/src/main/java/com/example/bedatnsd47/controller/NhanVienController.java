package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.entity.DiaChi;
import com.example.bedatnsd47.entity.TaiKhoan;
import com.example.bedatnsd47.entity.VaiTro;
import com.example.bedatnsd47.service.DiaChiService;
import com.example.bedatnsd47.service.KhachHangService;
import com.example.bedatnsd47.service.NhanVienService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/admin/nhan-vien")
public class NhanVienController {
    @Autowired
    NhanVienService taiKhoanService;


    @Autowired
    DiaChiService diaChiService;

    String random2 = ranDom1();

    TaiKhoan userInfo = new TaiKhoan();

    @Autowired
    private PasswordEncoder passwordEncoder;

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

        TaiKhoan taiKhoan = taiKhoanService.getById(id);
        model.addAttribute("nhanVien", taiKhoan);
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
            vaiTro.setId(Long.valueOf(1));
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
                      RedirectAttributes redirectAttributes,
                      HttpServletRequest request,
                      @RequestParam("email") String email
    ) {
        userInfo = taiKhoan;
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
        }
        else if (!taiKhoanService.checkTenTaiKhoanTrung(taiKhoan.getTenTaiKhoan())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Tên tài khoản phẩm đã tồn tại");
            model.addAttribute("checkEmailTrung", "Email phẩm đã tồn tại");
            model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
            return "/admin-template/nhan_vien/nhan-vien";
        }
        else if (!taiKhoanService.checkEmail(taiKhoan.getEmail())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkEmailTrung", "Email phẩm đã tồn tại");
            model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
            return "/admin-template/nhan_vien/nhan-vien";
        }
        else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            String url = request.getRequestURL().toString();
            System.out.println(url);
            url = url.replace(request.getServletPath(), "");
            taiKhoanService.sendEmail(userInfo, url, random2);
            System.out.println(userInfo);
            userInfo.setNgayTao(currentDate);
            userInfo.setNgaySua(currentDate);
            userInfo.setMatKhau(passwordEncoder.encode(random2));
            VaiTro vaiTro = new VaiTro();
            vaiTro.setId(Long.valueOf(1));
            userInfo.setVaiTro(vaiTro);
            userInfo.setTrangThai(1);
            userInfo.setVaiTro(vaiTro);
            taiKhoanService.update(userInfo);
            return "redirect:/admin/nhan-vien";
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
