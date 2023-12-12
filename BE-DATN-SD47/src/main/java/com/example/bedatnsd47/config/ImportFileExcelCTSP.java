package com.example.bedatnsd47.config;

import com.example.bedatnsd47.entity.ChiTietSanPham;
import com.example.bedatnsd47.entity.KichCo;
import com.example.bedatnsd47.entity.LoaiDe;
import com.example.bedatnsd47.entity.MauSac;
import com.example.bedatnsd47.entity.SanPham;
import com.example.bedatnsd47.repository.ChiTietSanPhamRepository;
import com.example.bedatnsd47.repository.KichCoRepository;
import com.example.bedatnsd47.repository.LoaiDeRepository;
import com.example.bedatnsd47.repository.MauSacRepository;
import com.example.bedatnsd47.repository.SanPhamRepository;
import com.example.bedatnsd47.service.ChiTietSanPhamSerivce;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ImportFileExcelCTSP {

    Integer indexLoi = 0;

    public void ImportFile(
            String path, SanPhamRepository sanPhamRepository, MauSacRepository mauSacRepository,
            KichCoRepository kichThuocRepository, LoaiDeRepository deGiayRepository,
            ChiTietSanPhamRepository chiTietSanPhamRepository, ChiTietSanPhamSerivce chiTietSanPhamService) throws Exception {

        FileInputStream fileExcel = new FileInputStream(new File(path));
        Workbook workbook = new XSSFWorkbook(fileExcel);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = sheet.iterator();
        Row firtRow = iterator.next();
        Cell firtCell = firtRow.getCell(0);
        List<Integer> listIndex = new ArrayList<>();
        int index = 0;
        while (iterator.hasNext()) {
            index++;
            try {
                Row row = iterator.next();
                String sanPhamStr = String.valueOf(getCellValue(row.getCell(0))).trim();
                String mauSacStr = String.valueOf(getCellValue(row.getCell(1))).trim();
                String kichThuocStr = String.valueOf((int) row.getCell(2).getNumericCellValue()).trim();
                String deGiayStr = String.valueOf(getCellValue(row.getCell(3))).trim();
                String soLuongTon = String.valueOf((int) row.getCell(4).getNumericCellValue()).trim();
                String giaBan = String.valueOf((int) row.getCell(5).getNumericCellValue()).trim();

                if (mauSacStr.isEmpty() && sanPhamStr.isEmpty() && kichThuocStr.isEmpty() && deGiayStr.isEmpty()
                        && soLuongTon.isEmpty() && giaBan.isEmpty()) {
                    listIndex.add(index);
                    continue;
                }

                SanPham sanPham = sanPhamRepository.findSanPhamByTen(sanPhamStr);
                MauSac mauSac = mauSacRepository.findMauSacByTen(mauSacStr);
                KichCo kichThuoc = kichThuocRepository.findKichCoByTen(Integer.parseInt(kichThuocStr));

                LoaiDe deGiay = deGiayRepository.findDeGiayByTen(deGiayStr);
                if (sanPham == null || mauSac == null || kichThuoc == null || deGiay == null) {
                    listIndex.add(index);
                    continue;
                }
                if (Integer.parseInt(soLuongTon) <= 0 || Integer.parseInt(soLuongTon) > 99999 ||
                        Integer.parseInt(giaBan) <= 0 || Integer.parseInt(giaBan) > 1000000000) {
                    listIndex.add(index);
                    continue;
                }

                ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
                ChiTietSanPham chiTietSanPhamCheck =
                        chiTietSanPhamRepository.findChiTietSanPham(
                                sanPham.getId(),
                                mauSac.getId(),
                                deGiay.getId(),
                                kichThuoc.getId());
                if (chiTietSanPhamCheck == null) {
                    chiTietSanPham.setSanPham(sanPham);
                    chiTietSanPham.setMauSac(mauSac);
                    chiTietSanPham.setKichCo(kichThuoc);
                    chiTietSanPham.setLoaiDe(deGiay);
                    chiTietSanPham.setSoLuong(Integer.parseInt(soLuongTon));
                    chiTietSanPham.setGiaHienHanh(Long.valueOf(giaBan));
                    chiTietSanPham.setTrangThai(0);
                    chiTietSanPham.setNgayTao(new Date());
                    chiTietSanPham.setNgaySua(new Date());
                    chiTietSanPhamService.saveExcel(chiTietSanPham);
                } else {
                    chiTietSanPhamCheck.setSoLuong(chiTietSanPhamCheck.getSoLuong() + Integer.parseInt(soLuongTon));
                    chiTietSanPhamCheck.setGiaHienHanh(Long.valueOf(giaBan));
                    chiTietSanPhamService.saveExcel(chiTietSanPhamCheck);
                }
                workbook.close();

            } catch (Exception e) {
                e.printStackTrace();
                listIndex.add(index);
                continue;
            }
        }
        this.SaveFileError(path, listIndex);
        indexLoi = listIndex.size();

    }

    public Integer checkLoi() {

        return indexLoi;

    }

    private static Object getCellValue(Cell cell) {
        try {
            switch (cell.getCellType()) {
                case NUMERIC -> {
                    return cell.getNumericCellValue();
                }
                case BOOLEAN -> {
                    return cell.getBooleanCellValue();
                }
                default -> {
                    return cell.getStringCellValue();
                }
            }
        } catch (Exception e) {
            return "";
        }
    }

    public void SaveFileError(String path, List<Integer> listIndex) {
        try (FileInputStream fileExcel = new FileInputStream(new File(path))) {
            Workbook workbook = new XSSFWorkbook(fileExcel);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();

            int rowIndex = 0;
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                for (Integer index : listIndex) {
                    if (rowIndex == index) {
                        colorRowRed(workbook, currentRow);
                        break;
                    }
                }
                rowIndex++;
            }

            try (FileOutputStream fileOut = new FileOutputStream(path)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void colorRowRed(Workbook workbook, Row row) {
        CellStyle redCellStyle = createRedCellStyle(workbook);

        int lastCellNum = row.getLastCellNum();
        for (int i = 0; i < lastCellNum; i++) {
            Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellStyle(redCellStyle);
        }
    }

    private static CellStyle createRedCellStyle(Workbook workbook) {
        CellStyle redCellStyle = workbook.createCellStyle();
        redCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        redCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return redCellStyle;
    }
}
