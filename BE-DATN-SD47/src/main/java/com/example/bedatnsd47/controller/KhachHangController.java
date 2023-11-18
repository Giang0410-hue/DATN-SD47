package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.entity.DiaChi;
import com.example.bedatnsd47.entity.TaiKhoan;
import com.example.bedatnsd47.entity.ThuongHieu;
import com.example.bedatnsd47.entity.VaiTro;
import com.example.bedatnsd47.service.DiaChiService;
import com.example.bedatnsd47.service.KhachHangService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/admin/khach-hang")
public class KhachHangController {
    @Autowired
    KhachHangService taiKhoanService;


    @Autowired
    DiaChiService diaChiService;

    private Date currentDate = new Date();

    String random3 = ranDom1();

    TaiKhoan userInfo = new TaiKhoan();

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping()
    public String hienThi(Model model) {
        model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
        model.addAttribute("khachHang", new TaiKhoan());
        return "/admin-template/khach_hang/khach-hang";
    }

    @GetMapping("/dang-hoat-dong")
    public String hienThiDangHoatDong(Model model) {
        model.addAttribute("listTaiKhoan", taiKhoanService.getAllDangHoatDong());
        model.addAttribute("khachHang", new TaiKhoan());
        return "/admin-template/khach_hang/khach-hang";
    }

    @GetMapping("/ngung-hoat-dong")
    public String hienThiNgungHoatDong(Model model) {
        model.addAttribute("listTaiKhoan", taiKhoanService.getAllNgungHoatDong());
        model.addAttribute("khachHang", new TaiKhoan());
        return "/admin-template/khach_hang/khach-hang";
    }

    @GetMapping("/view-update-khach-hang/{id}")
    public String viewUpdate(
            Model model,
            @PathVariable("id") Long id
    ) {
        List<DiaChi> listDiaChi = diaChiService.getAllByTaiKhoan(id);
        TaiKhoan taiKhoan = taiKhoanService.getById(id);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("idKhachHangUpdate", id);

//        check button add
        model.addAttribute("checkAdd", "add");

        model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
        model.addAttribute("khachHang", taiKhoan);
        model.addAttribute("diaChi", new DiaChi());
        return "/admin-template/khach_hang/sua-khach-hang";

    }

    @GetMapping("/view-update-dia-chi/{idKhachHang}/{idDiaChi}")
    public String viewUpdateAndDiaChi(
            Model model,
            @PathVariable("idKhachHang") Long idKhachHang,
            @PathVariable("idDiaChi") Long idDiaCHi
    ) {
        DiaChi diaChi = diaChiService.getById(idDiaCHi);
        List<DiaChi> listDiaChi = diaChiService.getAllByTaiKhoan(idKhachHang);
        TaiKhoan taiKhoan = taiKhoanService.getById(idKhachHang);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("listTaiKhoan", taiKhoanService.getAll());

//        check button update
        model.addAttribute("checkUpdate", "update");

//        id khách hàng và id địa chỉ cần update
        model.addAttribute("idKhachHangUpdate", idKhachHang);
        model.addAttribute("idDiaChiUpdate", idDiaCHi);

        model.addAttribute("khachHang", taiKhoan);
        model.addAttribute("diaChi", diaChi);
        return "/admin-template/khach_hang/sua-khach-hang";
    }

    @PostMapping("/dia-chi")
    public String updateDiaChi(
            @Valid
            @ModelAttribute("diaChi") DiaChi diaChi,
            BindingResult result,
            Model model,
//            lấy id của khách hàng
            @RequestParam("idTaiKhoanUpdateRequestParam") Long idTaiKhoan,
            RedirectAttributes redirectAttributes
    ) {
        if (diaChi.getId() == null) {
            if (result.hasErrors()) {
                model.addAttribute("checkModal", "modal");
                model.addAttribute("checkThongBao", "thaiBai");
                model.addAttribute("listTaiKhoan", taiKhoanService.getAll());

                List<DiaChi> listDiaChi = diaChiService.getAllByTaiKhoan(idTaiKhoan);
                TaiKhoan taiKhoan = taiKhoanService.getById(idTaiKhoan);
                model.addAttribute("listDiaChi", listDiaChi);
                model.addAttribute("idKhachHangUpdate", idTaiKhoan);

//        check button add
                model.addAttribute("checkAdd", "add");
                model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
                model.addAttribute("khachHang", taiKhoan);
                return "/admin-template/khach_hang/sua-khach-hang";
            } else if (!diaChiService.checkTenTrung(diaChi.getDiaChiCuThe(), idTaiKhoan)) {
                model.addAttribute("checkModal", "modal");
                model.addAttribute("checkThongBao", "thaiBai");
                model.addAttribute("checkTenTrungDiaChi", "Tên địa chỉ cũ thể đã tồn tại");
                List<DiaChi> listDiaChi = diaChiService.getAllByTaiKhoan(idTaiKhoan);
                TaiKhoan taiKhoan = taiKhoanService.getById(idTaiKhoan);
                model.addAttribute("listDiaChi", listDiaChi);
                model.addAttribute("idKhachHangUpdate", idTaiKhoan);
//        check button add
                model.addAttribute("checkAdd", "add");
                model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
                model.addAttribute("khachHang", taiKhoan);
                return "/admin-template/khach_hang/sua-khach-hang";
            } else {
                TaiKhoan taiKhoan = taiKhoanService.getById(idTaiKhoan);
                diaChi.setTaiKhoan(taiKhoan);
                diaChi.setTrangThai(0);
                diaChi.setNgayTao(currentDate);
                diaChi.setNgaySua(currentDate);
                diaChiService.save(diaChi);
                return "redirect:/admin/khach-hang/view-update-khach-hang/" + idTaiKhoan;
            }
        } else {
            if (result.hasErrors()) {
                model.addAttribute("checkModal", "modal");
                model.addAttribute("checkThongBao", "thaiBai");
                model.addAttribute("listTaiKhoan", taiKhoanService.getAll());

                List<DiaChi> listDiaChi = diaChiService.getAllByTaiKhoan(idTaiKhoan);
                TaiKhoan taiKhoan = taiKhoanService.getById(idTaiKhoan);
                model.addAttribute("listDiaChi", listDiaChi);
                model.addAttribute("idKhachHangUpdate", idTaiKhoan);

//        check button update
                model.addAttribute("checkUpdate", "update");
                model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
                model.addAttribute("khachHang", taiKhoan);
                return "/admin-template/khach_hang/sua-khach-hang";
            } else if (!diaChiService.checkTenTrungSua(diaChi.getId(), diaChi.getDiaChiCuThe(), idTaiKhoan)) {
                model.addAttribute("checkModal", "modal");
                model.addAttribute("checkThongBao", "thaiBai");
                model.addAttribute("checkTenTrungDiaChi", "Tên địa chỉ cũ thể đã tồn tại");
                List<DiaChi> listDiaChi = diaChiService.getAllByTaiKhoan(idTaiKhoan);
                TaiKhoan taiKhoan = taiKhoanService.getById(idTaiKhoan);
                model.addAttribute("listDiaChi", listDiaChi);
                model.addAttribute("idKhachHangUpdate", idTaiKhoan);
//        check button update
                model.addAttribute("checkUpdate", "update");
                model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
                model.addAttribute("khachHang", taiKhoan);
                return "/admin-template/khach_hang/sua-khach-hang";
            } else {
                redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
                TaiKhoan taiKhoan = taiKhoanService.getById(idTaiKhoan);
                diaChi.setTaiKhoan(taiKhoan);
                diaChi.setTrangThai(0);
                diaChi.setNgayTao(currentDate);
                diaChi.setNgaySua(currentDate);
                diaChiService.update(diaChi);
                return "redirect:/admin/khach-hang/view-update-khach-hang/" + idTaiKhoan;
            }
        }
    }


    @GetMapping("/dia-chi/delete/{idKhachHang}/{idDiaChi}")
    public String deleteDiaCHi(
            Model model,
            @PathVariable("idKhachHang") Long idKhachHang,
            @PathVariable("idDiaChi") Long idDiaCHi
    ) {
        model.addAttribute("checkModal", "modal");
        model.addAttribute("checkThongBao", "thaiBai");
        List<DiaChi> listDiaChi = diaChiService.getAllByTaiKhoan(idKhachHang);
        TaiKhoan taiKhoan = taiKhoanService.getById(idKhachHang);
        model.addAttribute("listDiaChi", listDiaChi);
        model.addAttribute("idKhachHangUpdate", idKhachHang);
        model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
        model.addAttribute("khachHang", taiKhoan);
        diaChiService.deleteById(idDiaCHi);
        return "redirect:/admin/khach-hang/view-update-khach-hang/" + idKhachHang;
    }


    @PostMapping("/update")
    public String update(
            @Valid
            @ModelAttribute("khachHang") TaiKhoan taiKhoan,
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
            List<DiaChi> listDiaChi = diaChiService.getAllByTaiKhoan(taiKhoan.getId());
            model.addAttribute("listDiaChi", listDiaChi);
            model.addAttribute("diaChi", new DiaChi());
            return "/admin-template/khach_hang/sua-khach-hang";
        } else if (!taiKhoanEntity.isValidNgaySinh()) {
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkNgaySinh", "ngaySinh");
            List<DiaChi> listDiaChi = diaChiService.getAllByTaiKhoan(taiKhoan.getId());
            model.addAttribute("listDiaChi", listDiaChi);
            model.addAttribute("diaChi", new DiaChi());

            return "/admin-template/khach_hang/sua-khach-hang";
        } else if (!taiKhoanService.checkTenTkTrungSua(taiKhoan.getId(), taiKhoan.getTenTaiKhoan())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Tên tài khoản phẩm đã tồn tại");
            model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
            List<DiaChi> listDiaChi = diaChiService.getAllByTaiKhoan(taiKhoan.getId());
            model.addAttribute("listDiaChi", listDiaChi);
            model.addAttribute("diaChi", new DiaChi());
            return "/admin-template/khach_hang/sua-khach-hang";
        } else if (!taiKhoanService.checkEmailSua(taiKhoan.getId(), taiKhoan.getEmail())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
            List<DiaChi> listDiaChi = diaChiService.getAllByTaiKhoan(taiKhoan.getId());
            model.addAttribute("listDiaChi", listDiaChi);
            model.addAttribute("diaChi", new DiaChi());
            return "/admin-template/khach_hang/khach-hang";
        } else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            taiKhoan.setNgayTao(taiKhoan.getNgayTao());
            taiKhoan.setNgaySua(currentDate);
            VaiTro vaiTro = new VaiTro();
            vaiTro.setId(Long.valueOf(3));
            taiKhoan.setVaiTro(vaiTro);
            taiKhoan.setTrangThai(taiKhoan.getTrangThai());
            taiKhoanService.update(taiKhoan);
            return "redirect:/admin/khach-hang";
        }
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("khachHang") TaiKhoan taiKhoan,
                      BindingResult result, Model model,
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
            return "/admin-template/khach_hang/khach-hang";
        } else if (!taiKhoanEntity.isValidNgaySinh()) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkNgaySinh", "ngaySinh");
            model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
            return "/admin-template/khach_hang/khach-hang";
        } else if (!taiKhoanService.checkTenTaiKhoanTrung(taiKhoan.getTenTaiKhoan())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkTenTrung", "Tên tài khoản phẩm đã tồn tại");
            model.addAttribute("checkEmailTrung", "Email phẩm đã tồn tại");
            model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
            return "/admin-template/khach_hang/khach-hang";
        } else if (!taiKhoanService.checkEmail(taiKhoan.getEmail())) {
            model.addAttribute("checkModal", "modal");
            model.addAttribute("checkThongBao", "thaiBai");
            model.addAttribute("checkEmailTrung", "Email phẩm đã tồn tại");
            model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
            return "/admin-template/khach_hang/khach-hang";
        }
        else {
            redirectAttributes.addFlashAttribute("checkThongBao", "thanhCong");
            String url = request.getRequestURL().toString();
            System.out.println(url);
            url = url.replace(request.getServletPath(), "");
            taiKhoanService.sendEmail(userInfo, url, random3);
            System.out.println(userInfo);
            userInfo.setNgayTao(currentDate);
            userInfo.setNgaySua(currentDate);
            userInfo.setMatKhau(passwordEncoder.encode(random3));
            VaiTro vaiTro = new VaiTro();
            vaiTro.setId(Long.valueOf(2));
            userInfo.setVaiTro(vaiTro);
            userInfo.setTrangThai(1);
            userInfo.setVaiTro(vaiTro);
            taiKhoanService.update(userInfo);
            return "redirect:/admin/khach-hang";
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
