//package com.example.bedatnsd47.controller;
//
//import com.example.bedatnsd47.entity.DiaChi;
//import com.example.bedatnsd47.entity.TaiKhoan;
//import jakarta.validation.Valid;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import java.util.List;
//
//public class Test {
//    @PostMapping("/dia-chi")
//    public String updateDiaChi(
//            @Valid
//            @ModelAttribute("diaChi") DiaChi diaChi,
//            BindingResult result,
//            Model model,
////            lấy id của khách hàng
//            @RequestParam("idTaiKhoanUpdateRequestParam") Long idTaiKhoan,
//            RedirectAttributes redirectAttributes
//    ) {
//        if (diaChi.getId() == null) {
//            if (result.hasErrors()) {
//                model.addAttribute("checkModal", "modal");
//                model.addAttribute("checkThongBao", "thaiBai");
//                model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
//
//                List<DiaChi> listDiaChi = diaChiService.getAllByTaiKhoan(idTaiKhoan);
//                TaiKhoan taiKhoan = taiKhoanService.getById(idTaiKhoan);
//                model.addAttribute("listDiaChi", listDiaChi);
//                model.addAttribute("idKhachHangUpdate", idTaiKhoan);
//
////        check button add
//                model.addAttribute("checkAdd", "add");
//                model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
//                model.addAttribute("khachHang", taiKhoan);
//                return "/admin-template/khach_hang/sua-khach-hang";
//            } else if (!diaChiService.checkTenTrung(diaChi.getDiaChiCuThe())) {
//                model.addAttribute("checkModal", "modal");
//                model.addAttribute("checkThongBao", "thaiBai");
//                model.addAttribute("checkTenTrungDiaChi", "Tên địa chỉ cũ thể đã tồn tại");
//                List<DiaChi> listDiaChi = diaChiService.getAllByTaiKhoan(idTaiKhoan);
//                TaiKhoan taiKhoan = taiKhoanService.getById(idTaiKhoan);
//                model.addAttribute("listDiaChi", listDiaChi);
//                model.addAttribute("idKhachHangUpdate", idTaiKhoan);
////        check button add
//                model.addAttribute("checkAdd", "add");
//                model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
//                model.addAttribute("khachHang", taiKhoan);
//                return "/admin-template/khach_hang/sua-khach-hang";
//            } else {
//                if (result.hasErrors()) {
//                    model.addAttribute("checkModal", "modal");
//                    model.addAttribute("checkThongBao", "thaiBai");
//                    model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
//
//                    List<DiaChi> listDiaChi = diaChiService.getAllByTaiKhoan(idTaiKhoan);
//                    TaiKhoan taiKhoan = taiKhoanService.getById(idTaiKhoan);
//                    model.addAttribute("listDiaChi", listDiaChi);
//                    model.addAttribute("idKhachHangUpdate", idTaiKhoan);
//
////        check button add
//                    model.addAttribute("checkAdd", "add");
//                    model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
//                    model.addAttribute("khachHang", taiKhoan);
//                    return "/admin-template/khach_hang/sua-khach-hang";
//                } else if (!diaChiService.checkTenTrungSua(diaChi.getId(), diaChi.getDiaChiCuThe())) {
//                    model.addAttribute("checkModal", "modal");
//                    model.addAttribute("checkThongBao", "thaiBai");
//                    model.addAttribute("checkTenTrungDiaChi", "Tên địa chỉ cũ thể đã tồn tại");
//                    List<DiaChi> listDiaChi = diaChiService.getAllByTaiKhoan(idTaiKhoan);
//                    TaiKhoan taiKhoan = taiKhoanService.getById(idTaiKhoan);
//                    model.addAttribute("listDiaChi", listDiaChi);
//                    model.addAttribute("idKhachHangUpdate", idTaiKhoan);
////        check button add
//                    model.addAttribute("checkAdd", "add");
//                    model.addAttribute("listTaiKhoan", taiKhoanService.getAll());
//                    model.addAttribute("khachHang", taiKhoan);
//                    return "/admin-template/khach_hang/sua-khach-hang";
//                } else {
//                    TaiKhoan taiKhoan = taiKhoanService.getById(idTaiKhoan);
//                    diaChi.setTaiKhoan(taiKhoan);
//                    diaChi.setTrangThai(0);
//                    diaChi.setNgayTao(currentDate);
//                    diaChi.setNgaySua(currentDate);
//                    diaChiService.save(diaChi);
//                    return "redirect:/admin/khach-hang/view-update-khach-hang/" + idTaiKhoan;
//                }
//
//            }
//        } else {
//            TaiKhoan taiKhoan = taiKhoanService.getById(idTaiKhoan);
//            diaChi.setTaiKhoan(taiKhoan);
//            diaChi.setTrangThai(0);
//            diaChi.setNgayTao(currentDate);
//            diaChi.setNgaySua(currentDate);
//            diaChiService.update(diaChi);
//            return "redirect:/admin/khach-hang/view-update-khach-hang/" + idTaiKhoan;
//        }
//    }
// chinh
//    @PostMapping("/dia-chi")
//    public String updateDiaChi(
//            @Valid
//            @ModelAttribute("diaChi") DiaChi diaChi,
//            BindingResult result,
//            Model model,
////            lấy id của khách hàng
//            @RequestParam("idTaiKhoanUpdateRequestParam") Long idTaiKhoan,
//            RedirectAttributes redirectAttributes
//    ) {
//        if (diaChi.getId() == null) {
//
//            TaiKhoan taiKhoan = taiKhoanService.getById(idTaiKhoan);
//            diaChi.setTaiKhoan(taiKhoan);
//            diaChi.setTrangThai(0);
//            diaChi.setNgayTao(currentDate);
//            diaChi.setNgaySua(currentDate);
//            diaChiService.save(diaChi);
//            return "redirect:/admin/khach-hang/view-update-khach-hang/" + idTaiKhoan;
//
//        } else {
//            TaiKhoan taiKhoan = taiKhoanService.getById(idTaiKhoan);
//            diaChi.setTaiKhoan(taiKhoan);
//            diaChi.setTrangThai(0);
//            diaChi.setNgayTao(currentDate);
//            diaChi.setNgaySua(currentDate);
//            diaChiService.update(diaChi);
//            return "redirect:/admin/khach-hang/view-update-khach-hang/" + idTaiKhoan;
//        }
//    }
//
//}
