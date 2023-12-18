package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.config.ExportFileCTSP;
import com.example.bedatnsd47.config.ImportFileExcelCTSP;
import com.example.bedatnsd47.config.PrincipalCustom;
import com.example.bedatnsd47.config.UserInfoUserDetails;
import com.example.bedatnsd47.entity.ChiTietSanPham;
import com.example.bedatnsd47.entity.SanPham;
import com.example.bedatnsd47.repository.ChiTietSanPhamRepository;
import com.example.bedatnsd47.repository.KichCoRepository;
import com.example.bedatnsd47.repository.LoaiDeRepository;
import com.example.bedatnsd47.repository.MauSacRepository;
import com.example.bedatnsd47.repository.SanPhamRepository;
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

import java.io.IOException;
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

    @Autowired
    private ChiTietSanPhamRepository chiTietSanPhamRepository;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private MauSacRepository mauSacRepository;

    @Autowired
    private KichCoRepository kichThuocRepository;

    @Autowired
    private LoaiDeRepository deGiayRepository;

    private PrincipalCustom principalCustom = new PrincipalCustom();

    private Model getString(Model model) {
        model.addAttribute("listSanPham", sanPhamSerivce.getAllDangHoatDong());
        model.addAttribute("listThuongHieu", thuongHieuService.getAllDangHoatDong());
        model.addAttribute("listKichCo", kichCoService.getAllDangHoatDong());
        model.addAttribute("listMauSac", mauSacService.getAllDangHoatDong());
        model.addAttribute("listLoaiDe", loaiDeService.getAllDangHoatDong());
        return model;
    }

    @GetMapping()
    public String hienThi(
            Model model) {
        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
        if (name != null) {
            model.addAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
        } else {
            return "redirect:/login";
        }
        model.addAttribute("listChiTietSP", chiTietSanPhamSerivce.getAllCtspOneSanPham());
        getString(model);
        model.addAttribute("sanPham", new SanPham());
        return "/admin-template/san_pham_chi_tiet/san-pham-chi-tiet";
    }

    @GetMapping("/ngung-hoat-dong")
    public String hienThiNgungHoatDong(
            Model model) {
        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
        if (name != null) {
            model.addAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
        } else {
            return "redirect:/login";
        }
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
        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
        if (name != null) {
            model.addAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
        } else {
            return "redirect:/login";
        }
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
        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
        if (name != null) {
            model.addAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
        } else {
            return "redirect:/login";
        }
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
            sanPham.setNgayTao(new Date());
            sanPham.setNgaySua(new Date());
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

    @PostMapping("import-excel")
    public String importExcel(
            @RequestParam("file") MultipartFile file,
            RedirectAttributes attributes,
            Model model

    ) throws IOException {
        UserInfoUserDetails name = principalCustom.getCurrentUserNameAdmin();
        if (name != null) {
            model.addAttribute("tenNhanVien", principalCustom.getCurrentUserNameAdmin().getHoVaTen());
        } else {
            return "redirect:/login";
        }
        if (!file.isEmpty()) {
//            String directory = "D:\\DATN-SD47\\BE-DATN-SD47";
//            String fileName = file.getOriginalFilename();
//            String filePath = directory + "\\" + fileName;
            ImportFileExcelCTSP importFileExcelCTSP = new ImportFileExcelCTSP();
            try {
                importFileExcelCTSP.ImportFile(file, sanPhamRepository, mauSacRepository, kichThuocRepository,
                        deGiayRepository, chiTietSanPhamRepository, chiTietSanPhamSerivce);
                if (importFileExcelCTSP.checkLoi() > 0) {
                    attributes.addFlashAttribute("checkTab", "true");
                    attributes.addFlashAttribute("checkThongBao", "thanhCong");
                    attributes.addFlashAttribute("thongBaoLoiImport", "Đã thêm sản phẩm thành công nhưng có một số sản phẩm lỗi.");
                    return "redirect:/admin/san-pham-chi-tiet";
                }
            } catch (Exception e) {
                attributes.addFlashAttribute("checkTab", "true");
                attributes.addFlashAttribute("checkThongBao", "thaiBai");
                attributes.addFlashAttribute("thongBaoLoiImport", "Sai định dạng file");
                return "redirect:/admin/san-pham-chi-tiet";
            }
            attributes.addFlashAttribute("checkThongBao", "thanhCong");
            return "redirect:/admin/san-pham-chi-tiet";
        }
        attributes.addFlashAttribute("checkTab", "true");
        attributes.addFlashAttribute("thongBaoLoiImport", "Bạn chưa chọn file excel nào");
        attributes.addFlashAttribute("checkThongBao", "thaiBai");
        return "redirect:/admin/san-pham-chi-tiet";
    }

    @GetMapping("export-excel")
    public String exportExcel(
            RedirectAttributes attributes
    ) throws IOException {
        ExportFileCTSP exportFileCTSP = new ExportFileCTSP();
        exportFileCTSP.ExportFileExcel(chiTietSanPhamSerivce);
        attributes.addFlashAttribute("checkTab", "true");
        attributes.addFlashAttribute("checkThongBao", "thanhCong");
        return "redirect:/admin/san-pham-chi-tiet";
    }

    @GetMapping("file-mau-excel")
    public String fileMauExcel(
            RedirectAttributes attributes
    ) throws IOException {
        ExportFileCTSP exportFileCTSP = new ExportFileCTSP();
        exportFileCTSP.ExportFileExcelMau(chiTietSanPhamSerivce);
        attributes.addFlashAttribute("checkTab", "true");
        attributes.addFlashAttribute("checkThongBao", "thanhCong");
        return "redirect:/admin/san-pham-chi-tiet";
    }

}
