package com.example.bedatnsd47.controller.user;

import com.example.bedatnsd47.entity.ChiTietSanPham;
import com.example.bedatnsd47.entity.DiaChi;
import com.example.bedatnsd47.entity.GioHangChiTiet;
import com.example.bedatnsd47.entity.TaiKhoan;
import com.example.bedatnsd47.entity.VaiTro;
import com.example.bedatnsd47.entity.Voucher;
import com.example.bedatnsd47.service.ChiTietSanPhamSerivce;
import com.example.bedatnsd47.service.DiaChiService;
import com.example.bedatnsd47.service.GioHangChiTietService;
import com.example.bedatnsd47.service.GioHangService;
import com.example.bedatnsd47.service.HoaDonService;
import com.example.bedatnsd47.service.KhachHangService;
import com.example.bedatnsd47.service.KichCoService;
import com.example.bedatnsd47.service.LoaiDeService;
import com.example.bedatnsd47.service.MauSacService;
import com.example.bedatnsd47.service.TaiKhoanService;
import com.example.bedatnsd47.service.VoucherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
//@RequestMapping("/home")
public class HomeController {

    private Long idTaiKhoan  = Long.valueOf(8);

    @Autowired
    private ChiTietSanPhamSerivce chiTietSanPhamSerivce;

    @Autowired
    private KichCoService kichCoService;

    @Autowired
    private MauSacService mauSacService;

    @Autowired
    private LoaiDeService loaiDeService;

    @Autowired
    private GioHangService gioHangService;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private DiaChiService diaChiService;

    @Autowired
    private TaiKhoanService  taiKhoanService;

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private GioHangChiTietService gioHangChiTietService;

    @Autowired
    private HoaDonService hoaDonService;

    @GetMapping("home")
    public String home(
//            Principal principal
    ) {
//        TaiKhoan taiKhoan = taiKhoanService.getTaiKhoanByName(principal.getName());
//        idTaiKhoan = taiKhoan.getId();
        return "/customer-template/ban-hang-customer";

    }

    @GetMapping("/dang-nhap")
    public String dangNhap() {

        return "/customer-template/dang-nhap";
    }

    @GetMapping("/dang-ky")
    public String dangKy() {

        return "/customer-template/dang-ky";
    }

    @GetMapping("cart")
    public String cart(
            Model model
    ) {
        TaiKhoan khachHang = khachHangService.getById(idTaiKhoan);
        List<GioHangChiTiet> listGioHangChiTiet = gioHangChiTietService.findAllByIdGioHang(khachHang.getGioHang().getId());
        model.addAttribute("soLuongSPGioHangCT", gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
        model.addAttribute("listGioHangChiTiet", listGioHangChiTiet);
        return "/customer-template/cart";
    }

    @GetMapping("/cart/detele/{id}")
    public String deleteCart(
            @PathVariable("id") Long id
    ) {
        gioHangChiTietService.deleteById(id);
        return "redirect:/cart";
    }

    @GetMapping("/cart/update/{id}")
    public String updateCart(
            @PathVariable("id") Long id,
            @RequestParam("soLuong") String soLuong
    ) {
        GioHangChiTiet gioHangChiTiet = gioHangChiTietService.fillById(id);
        gioHangChiTiet.setSoLuong(Integer.valueOf(soLuong));
        gioHangChiTietService.update(gioHangChiTiet);
        return "redirect:/cart";
    }

    @PostMapping("/gio-hang-chi-tiet/add/{idChiTietSpAdd}/{soLuongAdd}")
    public String addGioHangChiTiet(
            @PathVariable String idChiTietSpAdd,
            @PathVariable String soLuongAdd
    ) {
        TaiKhoan khachHang = khachHangService.getById(idTaiKhoan);
        gioHangChiTietService.save(khachHang.getGioHang().getId(), Long.valueOf(idChiTietSpAdd), Integer.valueOf(soLuongAdd));
        return "redirect:/shop";
    }

    @PostMapping("/gio-hang-chi-tiet/add-fast/{idChiTietSpAdd}/{soLuongAdd}")
    public String addGioHangChiTietNhanh(
            @PathVariable String idChiTietSpAdd,
            @PathVariable String soLuongAdd
    ) {
        TaiKhoan khachHang = khachHangService.getById(idTaiKhoan);
        gioHangChiTietService.save(khachHang.getGioHang().getId(), Long.valueOf(idChiTietSpAdd), Integer.valueOf(soLuongAdd));
        return "redirect:/cart";
    }

    @PostMapping("/dia-chi/update")
    public String updateDiaChi(
            @RequestParam("idDiaChi") Long idDiaChi,
            @RequestParam("phuongXa") String phuongXa,
            @RequestParam("quanHuyen") String quanHuyen,
            @RequestParam("thanhPho") String thanhPho,
            @RequestParam("diaChiCuThe") String diaChiCuThe,
            @RequestParam("trangThai") Integer trangThai
    ) {
        if (trangThai == 0) {
            List<DiaChi> listDiaChi = diaChiService.getAllTrangThai(0);
            DiaChi diaChiNew = new DiaChi();
            for (DiaChi diaChiUpdate : listDiaChi) {
                diaChiNew.setId(diaChiUpdate.getId());
                diaChiNew.setPhuongXa(diaChiUpdate.getPhuongXa());
                diaChiNew.setQuanHuyen(diaChiUpdate.getQuanHuyen());
                diaChiNew.setThanhPho(diaChiUpdate.getThanhPho());
                diaChiNew.setDiaChiCuThe(diaChiUpdate.getDiaChiCuThe());
                diaChiNew.setTrangThai(1);
                diaChiNew.setNgayTao(diaChiUpdate.getNgayTao());
                diaChiNew.setNgaySua(diaChiUpdate.getNgaySua());
                diaChiNew.setTaiKhoan(diaChiUpdate.getTaiKhoan());
                diaChiService.update(diaChiNew);
            }
        }
        Date date = new Date();
        DiaChi diaChi = new DiaChi();
        diaChi.setId(idDiaChi);
        diaChi.setPhuongXa(phuongXa);
        diaChi.setQuanHuyen(quanHuyen);
        diaChi.setThanhPho(thanhPho);
        diaChi.setDiaChiCuThe(diaChiCuThe);
        diaChi.setTrangThai(trangThai);
        diaChi.setNgayTao(date);
        diaChi.setNgaySua(date);
        diaChi.setTaiKhoan(TaiKhoan.builder().id(idTaiKhoan).build());
        diaChiService.update(diaChi);


        return "redirect:/cart";
    }

    @PostMapping("/dia-chi/add")
    public String adđDiaChi(
            @RequestParam("phuongXaID") String phuongXa,
            @RequestParam("quanHuyenID") String quanHuyen,
            @RequestParam("thanhPhoID") String thanhPho,
            @RequestParam("diaChiCuThe") String diaChiCuThe
    ) {
        Date date = new Date();
        DiaChi diaChi = new DiaChi();
        diaChi.setPhuongXa(phuongXa);
        diaChi.setQuanHuyen(quanHuyen);
        diaChi.setThanhPho(thanhPho);
        diaChi.setDiaChiCuThe(diaChiCuThe);
        diaChi.setTrangThai(1);
        diaChi.setNgayTao(date);
        diaChi.setNgaySua(date);
        diaChi.setTaiKhoan(TaiKhoan.builder().id(idTaiKhoan).build());
        diaChiService.save(diaChi);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(
            @RequestParam String options,
            Model model
    ) {
        String[] optionArray = options.split(",");
        List<String> listIdString = Arrays.asList(optionArray);
        TaiKhoan khachHang = khachHangService.getById(idTaiKhoan);
        List<GioHangChiTiet> listGioHangChiTiet = gioHangChiTietService.findAllById(listIdString, khachHang.getGioHang().getId());
        model.addAttribute("listGioHangChiTiet", listGioHangChiTiet);
        List<DiaChi> diaChi = diaChiService.getAllByTaiKhoan(idTaiKhoan);
        model.addAttribute("listVoucher", voucherService.fillAllDangDienRa());
        model.addAttribute("khachHang", khachHang);
        model.addAttribute("soLuongSPGioHangCT", gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
        if (khachHang.getLstDiaChi() == null || khachHang.getLstDiaChi().size() == 0) {
            model.addAttribute("checkDiaChi", "DiaChiNull");
        } else {
            model.addAttribute("checkDiaChi", "DiaChi");
            model.addAttribute("listDiaChi", diaChi);
            if (diaChi.size() == 5) {
                model.addAttribute("checkButtonAdd", "true");
                model.addAttribute("soDiaChi", diaChi.size());
            } else {
                model.addAttribute("checkButtonAdd", "false");
                model.addAttribute("soDiaChi", diaChi.size());
            }
        }
        return "/customer-template/checkout";
    }

    @PostMapping("/checkout/add")
    public String addHoaDon(
            @RequestParam("idGioHangChiTiet") String idGioHangChiTiet,
            @RequestParam("tongTien") String tongTien,
            @RequestParam("tongTienAndSale") String tongTienAndSale,
            @RequestParam("hoVaTen") String hoVaTen,
            @RequestParam("soDienThoai") String soDienThoai,
            @RequestParam("tienShip") String tienShip,
            @RequestParam("email") String email,
            @RequestParam("voucher") String voucher,
            @RequestParam("diaChiCuThe") String diaChiCuThe,
            @RequestParam("ghiChu") String ghiChu,
            @RequestParam("phuongXaID") String phuongXaID,
            @RequestParam("quanHuyenID") String quanHuyenID,
            @RequestParam("thanhPhoID") String thanhPhoID,
            @RequestParam("trangThaiLuuDC") String trangThaiLuuDC
    ) {
        String[] optionArray = idGioHangChiTiet.split(",");
        List<String> listIdString = Arrays.asList(optionArray);
        TaiKhoan khachHang = khachHangService.getById(idTaiKhoan);
        Voucher voucherGet = voucherService.fillByMaVoucher();
        if (trangThaiLuuDC.equals("0")) {
            Date date = new Date();
            DiaChi diaChi = new DiaChi();
            diaChi.setPhuongXa(phuongXaID);
            diaChi.setQuanHuyen(quanHuyenID);
            diaChi.setThanhPho(thanhPhoID);
            diaChi.setDiaChiCuThe(diaChiCuThe);
            diaChi.setTrangThai(0);
            diaChi.setNgayTao(date);
            diaChi.setNgaySua(date);
            diaChi.setTaiKhoan(TaiKhoan.builder().id(idTaiKhoan).build());
            diaChiService.save(diaChi);
        }
        if (voucherGet.getMaVoucher().equalsIgnoreCase(voucher)) {
            gioHangChiTietService.addHoaDon(listIdString, Long.valueOf(tongTien), Long.valueOf(tongTienAndSale), hoVaTen,
                    soDienThoai, tienShip, email, String.valueOf(voucherGet.getId()), diaChiCuThe, ghiChu, khachHang, phuongXaID, quanHuyenID, thanhPhoID, khachHang.getGioHang().getId());
        } else {
            gioHangChiTietService.addHoaDon(listIdString, Long.valueOf(tongTien), Long.valueOf(tongTienAndSale), hoVaTen,
                    soDienThoai, tienShip, email, voucher, diaChiCuThe, ghiChu, khachHang, phuongXaID, quanHuyenID, thanhPhoID, khachHang.getGioHang().getId());
        }
        return "redirect:/thankyou";
    }

    @GetMapping("/shop")
    public String shop(
            Model model
    ) {
        model.addAttribute("listChiTietSP", chiTietSanPhamSerivce.getAllDangHoatDong());
        model.addAttribute("listMauSac", mauSacService.findAll());
        TaiKhoan khachHang = khachHangService.getById(idTaiKhoan);
        model.addAttribute("soLuongSPGioHangCT", gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("listLoaiDe", loaiDeService.findAll());
        return "/customer-template/shop";
    }


//    @PostMapping("/gio-hang/add")
//    public String addGioHang(
//    ) {
//        GioHang gioHang = new GioHang();
//        gioHang.setMaGioHang("GH" + gioHangService.genMaTuDong());
//        gioHang.setGhiChu("");
//        gioHang.setNgayTao(currentDate);
//        gioHang.setNgayTao(currentDate);
//        gioHang.setTaiKhoan(TaiKhoan.builder().id(idTaiKhoan).build());
//        gioHang.setTrangThai(0);
//        gioHangService.save(gioHang);
//        return "redirect:/shop";
//    }


    @GetMapping("/chi-tiet-san-pham/{idSanPham}/{idMauSac}")
    @ResponseBody
    public List<ChiTietSanPham> getAllbyIdSPAndIdMS(
            @PathVariable String idSanPham,
            @PathVariable String idMauSac
    ) {
        List<ChiTietSanPham> listChiTietSanPham1 = chiTietSanPhamSerivce.getAllbyIdSPAndIdMS(Long.valueOf(idSanPham), Long.valueOf(idMauSac));
        return listChiTietSanPham1;
    }

    @GetMapping("/shop-single/{id}")
    public String shopSingle(
            @PathVariable("id") String id,
            Model model
    ) {
        ChiTietSanPham ChiTietSanPham = chiTietSanPhamSerivce.getAllById(Long.valueOf(id)).get(0);
        List<ChiTietSanPham> listChiTietSanPham = chiTietSanPhamSerivce.getAllById(Long.valueOf(id));
        TaiKhoan khachHang = khachHangService.getById(idTaiKhoan);
        model.addAttribute("soLuongSPGioHangCT", gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
        model.addAttribute("chiTietSp", ChiTietSanPham);
        model.addAttribute("listChiTietSp", listChiTietSanPham);
        return "/customer-template/shop-single";
    }


    @GetMapping("/shop-single/get-so-luong")
    @ResponseBody
    public Integer getSoLuongGHCT(
    ) {
        TaiKhoan khachHang = khachHangService.getById(idTaiKhoan);
        Integer soLuong = gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId());
        return soLuong;
    }

    @GetMapping("/shop-single/check-so-luong/{idCTSP}")
    @ResponseBody
    public Integer checkSoLuongSpEndGHCT(
            @PathVariable String idCTSP
    ) {
        GioHangChiTiet gioHangChiTiet = gioHangChiTietService.fillByIdCTSP(Long.valueOf(idCTSP));
        Integer soLuongCheck = gioHangChiTiet.getSoLuong();
        return soLuongCheck;
    }

    @GetMapping("/about")
    public String about() {

        return "/customer-template/about";
    }

    @GetMapping("/thong-tin-khach-hang")
    public String info(
            Model model
    ) {
        TaiKhoan khachHang = khachHangService.getById(idTaiKhoan);
        model.addAttribute("khachHang", khachHang);
        model.addAttribute("soLuongSPGioHangCT", gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
        return "/customer-template/thong-tin-khach-hang";
    }

    @PostMapping("/thong-tin-khach-hang/update")
    public String updateInfo(
            @Valid @ModelAttribute("khachHang") TaiKhoan khachHang,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            TaiKhoan kh = khachHangService.getById(idTaiKhoan);
            model.addAttribute("soLuongSPGioHangCT", gioHangChiTietService.soLuongSPGioHangCT(kh.getGioHang().getId()));
            return "/customer-template/thong-tin-khach-hang";
        } else if (!khachHang.isValidNgaySinh()) {
            TaiKhoan kh = khachHangService.getById(idTaiKhoan);
            model.addAttribute("soLuongSPGioHangCT", gioHangChiTietService.soLuongSPGioHangCT(kh.getGioHang().getId()));
            model.addAttribute("checkNgaySinh", "Năm sinh phải lớn 1900");
            return "/customer-template/thong-tin-khach-hang";
        } else if (!khachHangService.checkEmailSua(khachHang.getId(), khachHang.getEmail())) {
            TaiKhoan kh = khachHangService.getById(idTaiKhoan);
            model.addAttribute("soLuongSPGioHangCT", gioHangChiTietService.soLuongSPGioHangCT(kh.getGioHang().getId()));
            model.addAttribute("checkEmailTrung", "Email đã được đăng ký");
            return "/customer-template/thong-tin-khach-hang";
        } else {
            khachHang.setNgaySua(new Date());
            khachHang.setVaiTro(VaiTro.builder().id(Long.valueOf(2)).build());
            khachHangService.update(khachHang);
        }
        return "redirect:/thong-tin-khach-hang";
    }

    @GetMapping("/dia-chi")
    public String diaChiKhachHang(
            Model model
    ) {
        TaiKhoan khachHang = khachHangService.getById(idTaiKhoan);
        model.addAttribute("soLuongSPGioHangCT", gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));

        return "/customer-template/dia-chi";
    }

    @GetMapping("/doi-mat-khau")
    public String doiMatKhau(
            Model model
    ) {
        TaiKhoan khachHang = khachHangService.getById(idTaiKhoan);
        model.addAttribute("soLuongSPGioHangCT", gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
        return "/customer-template/doi-mat-khau-khach-hang";
    }

    @GetMapping("/don-mua")
    public String donMua(
            Model model
    ) {
        TaiKhoan khachHang = khachHangService.getById(idTaiKhoan);
        model.addAttribute("soLuongSPGioHangCT", gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
        model.addAttribute("listAllHoaDon",hoaDonService.getAllHoaDonByTaiKhoanOrderByNgaySua(idTaiKhoan));
        model.addAttribute("listHDChoXacNhan",hoaDonService.getHoaDonByTaiKhoanByTrangThaiOrderByNgaySua(idTaiKhoan,0));
        model.addAttribute("listHDChoGiao",hoaDonService.getHoaDonByTaiKhoanByTrangThaiOrderByNgaySua(idTaiKhoan,1));
        model.addAttribute("listHDDangGiao",hoaDonService.getHoaDonByTaiKhoanByTrangThaiOrderByNgaySua(idTaiKhoan,2));
        model.addAttribute("listHDHoanThanh",hoaDonService.getHoaDonByTaiKhoanByTrangThaiOrderByNgaySua(idTaiKhoan,3));
        model.addAttribute("listHDDaHuy",hoaDonService.getHoaDonByTaiKhoanByTrangThaiOrderByNgaySua(idTaiKhoan,5));
        model.addAttribute("listHDTraHang",hoaDonService.getHoaDonByTaiKhoanByTrangThaiOrderByNgaySua(idTaiKhoan,6));
        return "/customer-template/don-mua";
    }

    @GetMapping("/don-mua/{idHoaDon}")
    public String donMuaChiTiet(
            @PathVariable("idHoaDon")Long idHoaDon,
            Model model
    ) {
        TaiKhoan khachHang = khachHangService.getById(idTaiKhoan);
        model.addAttribute("soLuongSPGioHangCT", gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
        model.addAttribute("byHoaDon",hoaDonService.findById(idHoaDon));
        return "/customer-template/don-mua-chi-tiet";
    }

    @GetMapping("/thankyou")
    public String thankYou(
            Model model
    ) {
        TaiKhoan khachHang = khachHangService.getById(idTaiKhoan);
        model.addAttribute("soLuongSPGioHangCT", gioHangChiTietService.soLuongSPGioHangCT(khachHang.getGioHang().getId()));
        return "/customer-template/thankyou";
    }


}
