package com.example.bedatnsd47.controller;

import com.example.bedatnsd47.entity.HoaDon;
import com.example.bedatnsd47.entity.Voucher;
import com.example.bedatnsd47.repository.ChiTietSanPhamRepository;
import com.example.bedatnsd47.repository.HoaDonChiTietRepository;
import com.example.bedatnsd47.repository.HoaDonRepository;
import com.example.bedatnsd47.service.ChiTietSanPhamSerivce;
import com.example.bedatnsd47.service.HoaDonChiTietService;
import com.example.bedatnsd47.service.HoaDonService;
import com.example.bedatnsd47.service.VoucherService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/thong-ke")
public class ThongKeController {

    @Autowired
    HoaDonService hoaDonService;

    @Autowired
    HoaDonChiTietService hoaDonChiTietService;

    @Autowired
    ChiTietSanPhamSerivce chiTietSanPhamSerivce;

    @GetMapping()
    public String hienThi(
            Model model) {
        Date ngayTao1 = new Date();
        Integer countHoaDonDay = hoaDonService.countHoaDonDay(ngayTao1);
        Long sumHoaDonDay = hoaDonService.sumHoaDonDay(ngayTao1);
        Integer countHoaDonMonth = hoaDonService.countHoaDonMonth(ngayTao1);
        Long sumHoaDonMonth = hoaDonService.sumHoaDonMonth(ngayTao1);
        Integer sumSanPhamHoaDonMonth = hoaDonChiTietService.sumSanPhamHoaDonThang(ngayTao1);
        Integer sumSanPhamHoaDonDay = hoaDonChiTietService.sumSanPhamHoaDonNgay(ngayTao1);
        Integer countHoaDon0 = hoaDonService.countHoaDon(0);
        Integer countHoaDon1 = hoaDonService.countHoaDon(1);
        Integer countHoaDon2 = hoaDonService.countHoaDon(2);
        Integer countHoaDon3 = hoaDonService.countHoaDon(3);
        Integer countHoaDon5 = hoaDonService.countHoaDon(5);
        model.addAttribute("countHoaDonDay", countHoaDonDay);
        model.addAttribute("sumHoaDonDay", sumHoaDonDay);
        model.addAttribute("countHoaDonMonth", countHoaDonMonth);
        model.addAttribute("sumHoaDonMonth", sumHoaDonMonth);
        model.addAttribute("sumSanPhamHoaDonMonth", sumSanPhamHoaDonMonth);
        model.addAttribute("sumSanPhamHoaDonDay", sumSanPhamHoaDonDay);
        model.addAttribute("countHoaDon0", countHoaDon0);
        model.addAttribute("countHoaDon1", countHoaDon1);
        model.addAttribute("countHoaDon2", countHoaDon2);
        model.addAttribute("countHoaDon3", countHoaDon3);
        model.addAttribute("countHoaDon5", countHoaDon5);
        List<Object[]> result = hoaDonChiTietService.findByTongSoLuongThang(ngayTao1);
        List<Object[]> top5Ngay = hoaDonChiTietService.findByTongSoLuongNgay(ngayTao1);
        model.addAttribute("top5Thang", result);
        model.addAttribute("top5Ngay", top5Ngay);
        Integer soLuong = 10;
        List<Object[]> danhSachSapHetHang = chiTietSanPhamSerivce.danhSachHangSapHet(soLuong);
        model.addAttribute("listSapHetHang",danhSachSapHetHang);
        return "/admin-template/thong_ke/thong-ke";
    }


    @PostMapping("/count-range")
    public String countHoaDonTrongKhoangThoiGian(
            @RequestParam(name = "startDateChart", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDateChart,
            @RequestParam(name = "endDateChart", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDateChart,
            RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("startDateChart", startDateChart);
        redirectAttributes.addFlashAttribute("endDateChart", endDateChart);
        List<Object[]> thongKeSP = hoaDonChiTietService.thongKeSanPhamTheoNgay(startDateChart, endDateChart);
        redirectAttributes.addFlashAttribute("thongKeSP", thongKeSP);
        return "redirect:/admin/thong-ke";
    }

    @PostMapping("/count-between")
    public String countHoaDonBetween(
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            RedirectAttributes redirectAttributes) {
        Integer sumSanPham = hoaDonChiTietService.sumSanPhamHoaDonBetween(startDate, endDate);
        Integer countHoaDon = hoaDonService.countHoaDonBetween(startDate, endDate);
        Long sumHoaDon = hoaDonService.sumGiaTriHoaDonBetween(startDate, endDate);
        redirectAttributes.addFlashAttribute("sumSanPham", sumSanPham);
        redirectAttributes.addFlashAttribute("startDate", startDate);
        redirectAttributes.addFlashAttribute("endDate", endDate);
        redirectAttributes.addFlashAttribute("countHoaDon", countHoaDon);
        redirectAttributes.addFlashAttribute("sumHoaDon", sumHoaDon);
        List<Object[]> thongKeSanPhamBetween = hoaDonChiTietService.findByTongSoLuongBetween(startDate, endDate);
        redirectAttributes.addFlashAttribute("thongKeBetween", thongKeSanPhamBetween);
        return "redirect:/admin/thong-ke";
    }

    @PostMapping("/sapHetHang")
    public String countSapHetHang(
            @RequestParam(name = "soLuong", required = false) Integer soLuong,
            @RequestParam(name = "outputFormat", defaultValue = "table") String outputFormat,
            RedirectAttributes redirectAttributes,
            HttpServletResponse response) {

        List<Object[]> danhSachSapHetHang = chiTietSanPhamSerivce.danhSachHangSapHet(soLuong);

        redirectAttributes.addFlashAttribute("soLuong", soLuong);
        redirectAttributes.addFlashAttribute("danhSachSapHetHang", danhSachSapHetHang);

        if ("excel".equals(outputFormat)) {
            // Export to Excel
            response.setHeader("Content-Disposition", "attachment; filename=SapHetHang.xlsx");
            exportToExcel(response, danhSachSapHetHang);
            return null;  // Returning null to indicate that the response is already handled
        } else {
            return "redirect:/admin/thong-ke";  // Redirect to the table view
        }
    }

    private void exportToExcel(HttpServletResponse response, List<Object[]> listSapHetHang) {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("SapHetHang");

            // Create header row
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("STT");
            header.createCell(1).setCellValue("Sản phẩm");
            header.createCell(2).setCellValue("Kích cỡ");
            header.createCell(3).setCellValue("Màu sắc");
            header.createCell(4).setCellValue("Loại đế");
            header.createCell(5).setCellValue("Số Lượng");

            // Populate data rows
            int rowNum = 1;
            for (Object[] row : listSapHetHang) {
                Row dataRow = sheet.createRow(rowNum++);
                dataRow.createCell(0).setCellValue(rowNum - 1); // STT
                for (int i = 1; i < row.length + 1; i++) {
                    Cell cell = dataRow.createCell(i);
                    cell.setCellValue(String.valueOf(row[i - 1]));
                }
            }

            // Write the workbook to the response output stream
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.close();

        } catch (IOException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }


}