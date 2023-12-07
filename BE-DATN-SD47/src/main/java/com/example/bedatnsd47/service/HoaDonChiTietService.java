package com.example.bedatnsd47.service;


import com.example.bedatnsd47.entity.HoaDonChiTiet;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface HoaDonChiTietService {
    List<HoaDonChiTiet> findAll();

    HoaDonChiTiet findById(Long id);

    void deleteById(Long id);

    void saveOrUpdate(HoaDonChiTiet hoaDonChiTiet);

    List<HoaDonChiTiet> findByIdHoaDon(Long idHoaDon);

    List<Object[]> findByTongSoLuongBetween(
            Date startDate,
            Date endDate);

    List<Object[]> findByTongSoLuongNgay(Date ngayTao);

    List<Object[]> findByTongSoLuongThang(Date ngayTao);

    Integer sumSanPhamHoaDonThang(Date ngayTao);

    Integer sumSanPhamHoaDonNgay(Date ngayTao);

    Integer sumSanPhamHoaDonBetween(Date startDate,
                                    Date endDate);

    List<Object[]> thongKeSanPhamTheoNgay(
            Date startDateChart,
            Date endDateChart
    );
}
