package com.example.bedatnsd47.controller.user;

import com.example.bedatnsd47.entity.ChiTietSanPham;
import com.example.bedatnsd47.entity.DiaChi;
import com.example.bedatnsd47.entity.GioHang;
import com.example.bedatnsd47.entity.GioHangChiTiet;
import com.example.bedatnsd47.entity.TaiKhoan;
import com.example.bedatnsd47.service.ChiTietSanPhamSerivce;
import com.example.bedatnsd47.service.DiaChiService;
import com.example.bedatnsd47.service.GioHangChiTietService;
import com.example.bedatnsd47.service.GioHangService;
import com.example.bedatnsd47.service.KhachHangService;
import com.example.bedatnsd47.service.KichCoService;
import com.example.bedatnsd47.service.LoaiDeService;
import com.example.bedatnsd47.service.MauSacService;
import com.example.bedatnsd47.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
//@RequestMapping("/home")
public class HomeController {

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
    private VoucherService voucherService;

    @Autowired
    private GioHangChiTietService gioHangChiTietService;

    private Date currentDate = new Date();

    @GetMapping("/home")
    public String home() {

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

    @GetMapping("/cart")
    public String cart(
            Model model
    ) {
        List<GioHangChiTiet> listGioHangChiTiet = gioHangChiTietService.findAllByIdGioHang(Long.valueOf(1));
        model.addAttribute("soLuongSPGioHangCT", gioHangChiTietService.soLuongSPGioHangCT(Long.valueOf(1)));
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
        gioHangChiTietService.save(Long.valueOf(1), Long.valueOf(idChiTietSpAdd), Integer.valueOf(soLuongAdd));
        return "redirect:/shop";
    }

    @PostMapping("/gio-hang-chi-tiet/add-fast/{idChiTietSpAdd}/{soLuongAdd}")
    public String addGioHangChiTietNhanh(
            @PathVariable String idChiTietSpAdd,
            @PathVariable String soLuongAdd
    ) {
        gioHangChiTietService.save(Long.valueOf(1), Long.valueOf(idChiTietSpAdd), Integer.valueOf(soLuongAdd));
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(
            @RequestParam String options,
            Model model
    ) {
        String[] optionArray = options.split(",");
        List<String> listIdString = Arrays.asList(optionArray);
        List<GioHangChiTiet> listGioHangChiTiet = gioHangChiTietService.findAllById(listIdString);
        model.addAttribute("listGioHangChiTiet", listGioHangChiTiet);
        TaiKhoan khachHang = khachHangService.getById(Long.valueOf(4));
        List<DiaChi> diaChi = diaChiService.getAllByTaiKhoan(Long.valueOf(4));
        model.addAttribute("listVoucher",voucherService.fillAllDangDienRa());
        model.addAttribute("khachHang",khachHang);

        if(khachHang.getLstDiaChi() == null || khachHang.getLstDiaChi().size() == 0){
           model.addAttribute("checkDiaChi","DiaChiNull");
       }else {
           model.addAttribute("checkDiaChi", "DiaChi");
            model.addAttribute("listDiaChi",diaChi);
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
            @RequestParam("ghiChu") String ghiChu
    ) {
        String[] optionArray = idGioHangChiTiet.split(",");
        List<String> listIdString = Arrays.asList(optionArray);
        gioHangChiTietService.addHoaDon(listIdString, Long.valueOf(tongTien), Long.valueOf(tongTienAndSale)
                , hoVaTen, soDienThoai, tienShip, email, voucher, diaChiCuThe, ghiChu);
        return "redirect:/thankyou";
    }

    @GetMapping("/shop")
    public String shop(
            Model model
    ) {
        model.addAttribute("listChiTietSP", chiTietSanPhamSerivce.getAllCtspOneSanPham());
        model.addAttribute("listMauSac", mauSacService.findAll());
        model.addAttribute("soLuongSPGioHangCT", gioHangChiTietService.soLuongSPGioHangCT(Long.valueOf(1)));
        model.addAttribute("listKichCo", kichCoService.findAll());
        model.addAttribute("listLoaiDe", loaiDeService.findAll());
        return "/customer-template/shop";
    }

    @PostMapping("/gio-hang/add")
    public String addGioHang(
    ) {
        GioHang gioHang = new GioHang();
        gioHang.setMaGioHang("GH" + gioHangService.genMaTuDong());
        gioHang.setGhiChu("ok");
        gioHang.setNgayTao(currentDate);
        gioHang.setNgayTao(currentDate);
        gioHang.setTaiKhoan(TaiKhoan.builder().id(Long.valueOf(1)).build());
        gioHang.setTrangThai(0);
        gioHangService.save(gioHang);
        return "redirect:/shop";
    }



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
        model.addAttribute("soLuongSPGioHangCT", gioHangChiTietService.soLuongSPGioHangCT(Long.valueOf(1)));
        model.addAttribute("chiTietSp", ChiTietSanPham);
        model.addAttribute("listChiTietSp", listChiTietSanPham);
        return "/customer-template/shop-single";
    }

    @GetMapping("/about")
    public String about() {

        return "/customer-template/about";
    }

    @GetMapping("/thankyou")
    public String thankYou() {

        return "/customer-template/thankyou";
    }


}
