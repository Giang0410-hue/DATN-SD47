package com.example.bedatnsd47.service;

import com.example.bedatnsd47.entity.ChiTietSanPham;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ChiTietSanPhamSerivce {

    List<ChiTietSanPham> getAll();

    List<ChiTietSanPham> getAllDangHoatDong();

    List<ChiTietSanPham> getAllNgungHoatDong();

    List<ChiTietSanPham> add(
            List<String> listSanPham, List<String> listKichCo,
            List<String> listMauSac, List<String> listLoaiDe,
            List<String> listSoLuong, List<String> listDonGia);

    ChiTietSanPham update(ChiTietSanPham chiTietSanPham);

    void remove(Long id);

    ChiTietSanPham getById(Long id);

    boolean checkTenTrung(String ten);

    boolean checkTenTrungSua(String ma, String ten);

}
