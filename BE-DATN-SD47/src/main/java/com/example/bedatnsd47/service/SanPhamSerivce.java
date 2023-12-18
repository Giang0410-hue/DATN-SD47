package com.example.bedatnsd47.service;

import com.example.bedatnsd47.entity.SanPham;

import java.util.List;

public interface SanPhamSerivce {

    List<SanPham> getAll();

    List<SanPham> getAllDangHoatDong();

    List<SanPham> getAllNgungHoatDong();

    SanPham add(SanPham sanPham);

    SanPham update(SanPham sanPham);

    void remove(Long id);

    SanPham getById(Long id);

    Integer genMaTuDong();

    boolean checkTenTrung(String ten);

    boolean checkTenTrungSua(String ma, String ten);

}
