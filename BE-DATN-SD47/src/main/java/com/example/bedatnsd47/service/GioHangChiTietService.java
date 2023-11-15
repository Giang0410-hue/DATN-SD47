package com.example.bedatnsd47.service;

import com.example.bedatnsd47.entity.GioHangChiTiet;
import com.example.bedatnsd47.entity.HoaDon;
import com.example.bedatnsd47.entity.HoaDonChiTiet;

import java.util.List;

public interface GioHangChiTietService {

    List<GioHangChiTiet> findAll();

    List<GioHangChiTiet> findAllByIdGioHang(Long idGioHang);

    GioHangChiTiet fillById(Long id);

    Integer soLuongSPGioHangCT(Long idGioHang);

    List<GioHangChiTiet> findAllById(List<String> listIdString);

    HoaDonChiTiet addHoaDon(List<String> listStringIdGioHangCT, Long tongTien, Long tongTienSale,
                            String hoVaTen, String soDienThoai, String phuongXa, String quanHuyen,
                            String thanhPho, String diaChiCuThe, String ghiChu);

    void deleteById(Long id);

    GioHangChiTiet save(Long idGioHang, Long idChiTietSp, Integer soLuong);

    GioHangChiTiet update(GioHangChiTiet gioHangChiTiet);

    List<GioHangChiTiet> findByTrangThai(Integer trangThai);

}
