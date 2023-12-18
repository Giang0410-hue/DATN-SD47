package com.example.bedatnsd47.service;

import com.example.bedatnsd47.entity.GioHangChiTiet;
import com.example.bedatnsd47.entity.HoaDon;
import com.example.bedatnsd47.entity.HoaDonChiTiet;
import com.example.bedatnsd47.entity.TaiKhoan;

import java.util.List;

public interface GioHangChiTietService {

    List<GioHangChiTiet> findAll();

    List<GioHangChiTiet> findAllByIdGioHang(Long idGioHang);

    GioHangChiTiet fillById(Long id);

    GioHangChiTiet fillByIdCTSP(Long idCTSP);

    Integer soLuongSPGioHangCT(Long idGioHang);

    List<GioHangChiTiet> findAllById(List<String> listIdString, Long idGioHang);

    HoaDonChiTiet addHoaDon(List<String> listStringIdGioHangCT, Long tongTien, Long tongTienSale,
                            String hoVaTen, String soDienThoai, String tienShip,String tienGiam, String email,
                            String voucher, String diaChiCuThe, String ghiChu, TaiKhoan taiKhoan,
                            String phuongXaID, String quanHuyenID, String thanhPhoID, Long idGioHang);

    void deleteById(Long id);

    List<GioHangChiTiet> save(Long idGioHang, List<String> idChiTietSp, Integer soLuong);

    GioHangChiTiet update(GioHangChiTiet gioHangChiTiet);

    List<GioHangChiTiet> findByTrangThai(Integer trangThai);

}
