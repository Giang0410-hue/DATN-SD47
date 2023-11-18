package com.example.bedatnsd47.tesst;


import com.example.bedatnsd47.entity.TaiKhoan;
import com.example.bedatnsd47.entity.VaiTro;
import com.example.bedatnsd47.repository.KhachHangRepository;
import com.example.bedatnsd47.repository.TaiKhoanRepository;
import com.example.bedatnsd47.service.TaiKhoanService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

@Controller
public class FormLoginContrller {
    @Autowired
    TaiKhoanService service;


    @Autowired
    TaiKhoanRepository taiKhoanRepository;

    @Autowired
    KhachHangRepository khachHangRepository;

    String emailKiemTra;

    @Autowired
    PasswordEncoder passwordEncoder;

    TaiKhoan userInfo = new TaiKhoan();

    String random2 = ranDom();


    String emailDangKi;

    String tenTaiKhoanDangKi;

    @GetMapping("/login")
    public String formLogin() {
//        principal.getName();
        return "dang-nhap";
    }

    @GetMapping("/register")
    public String dangKis() {
//        principal.getName();
        return "dang-ky";
    }

    @GetMapping("/check-ran-dom")
    public String checkRanDOm() {
        return "admin-template/xac-minh";
    }

    @PostMapping("/check-random")
    public String checkRanDOm1(@RequestParam("ranDom") String ranDom1, Model model) {

        if (ranDom1.equalsIgnoreCase(random2)) {
            service.addUser(userInfo);
            return "redirect:/login";
        } else {
            System.out.println("mã không đúng");
            model.addAttribute("ma", "mã không đúng");
            return "admin-template/xac-minh";
        }

    }


    @PostMapping("/saveTaiKhoan1")
    public String addNewUser1(@RequestParam("email") String email, @RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, Model model, HttpServletRequest request) {
        emailDangKi = email;
        tenTaiKhoanDangKi = username;
        TaiKhoan taiKhoanDk = taiKhoanRepository.findByTenTaiKhoan(tenTaiKhoanDangKi).orElse(null);
        TaiKhoan emailDk = khachHangRepository.findByEmail(emailDangKi).orElse(null);
        if (taiKhoanDk != null ) {
            System.out.println("Da ton tai");
            return "dang-ky";
        } else if (emailDk != null) {
            System.out.println("Da ton tai");
            return "dang-ky";
        } else {
            String url = request.getRequestURL().toString();
            System.out.println(url);
            url = url.replace(request.getServletPath(), "");
            System.out.println(url);
            // //http://localhost:8080/verify?code=3453sdfsdcsadcsc
            userInfo.setTenTaiKhoan(tenTaiKhoanDangKi);
            userInfo.setEmail(emailDangKi);
            VaiTro vaiTro = new VaiTro();
            vaiTro.setId(2L);
            try {
                // Create a SimpleDateFormat object with the desired date pattern
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

                // Parse the string and set the ngaySinh attribute
                userInfo.setNgaySinh(dateFormat.parse("01-01-1907"));
            } catch (ParseException e) {
                // Handle the exception if the date string is not in the expected format
                e.printStackTrace(); // Or log the error
            }
            userInfo.setHoVaTen(username);
            userInfo.setSoDienThoai("0000000000");
            userInfo.setGioiTinh(-1);
            userInfo.setVaiTro(vaiTro);
            userInfo.setMatKhau(password);
            service.sendEmail1(userInfo, url, random2);
            return "redirect:/check-ran-dom";
        }
    }

    @GetMapping("/verify")
    public String verifyAccount(Model m) {
        return "xac-nhan-quen-mat-khau";
    }

    @PostMapping("/reset-mat-khau")
    public String resetMatKhau(
            @RequestParam("matKhauCu") String matKhauCu,
            @RequestParam("matKhauMoi") String matKhauMoi,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        TaiKhoan taiKhoan = taiKhoanRepository.findByEmail(emailKiemTra);
        if (taiKhoan != null) {
            if (matKhauCu.equals(matKhauMoi)) {
                taiKhoan.setMatKhau(matKhauMoi);
                service.addUser(taiKhoan);
                System.out.println("Thay đổi mk thành công ");
                redirectAttributes.addFlashAttribute("thongBao", "Thay đổi mật khẩu thành công.");
                redirectAttributes.addFlashAttribute("tenTaiKhoan", taiKhoan.getTenTaiKhoan());
                redirectAttributes.addFlashAttribute("matKhauMoi", matKhauMoi);
                return "redirect:/login";
            } else {
                System.out.println("Mật khẩu không trung khớp");
                model.addAttribute("thongBao", "Mật khẩu không trùng khớp .");
                return "xac-nhan-quen-mat-khau";
            }
        }
        return "redirect:/login";

    }


    @GetMapping("/quen-mat-khau")
    public String quenMatKhau() {
        return "quen-mat-khau";
    }

    @PostMapping("/quen-mat-khau")
    public String quenMatKau(
            @RequestParam("email") String email, Model model,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request) {
        String xacNhan = "";
        emailKiemTra = email;
        String url = request.getRequestURL().toString();
        TaiKhoan taiKhoan = taiKhoanRepository.findByEmail(emailKiemTra);
        url = url.replace(request.getServletPath(), "");
        if (taiKhoan != null) {
            service.sendEmail(taiKhoanRepository.findByEmail(email), url);
            System.out.println("thanh cong");
            model.addAttribute("email", email);
            return "xac-minh-email-tc";
        } else {
            model.addAttribute("message", "Email này chưa ứng với tài khoản nào " + xacNhan);
            System.out.println("Email nay không tồn tại");
            return "quen-mat-khau";
        }

    }

    public String ranDom() {
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
