package com.example.bedatnsd47.service;

import com.example.bedatnsd47.entity.TaiKhoan;

import java.util.List;

public interface NhanVienService {

    List<TaiKhoan> getAll();

    List<TaiKhoan> getAllDangHoatDong();

    List<TaiKhoan> getAllNgungHoatDong();


    TaiKhoan add(TaiKhoan sanPham);

    TaiKhoan update(TaiKhoan sanPham);

    void remove(Long id);

    TaiKhoan getById(Long id);

    boolean checkTenTrung(String ten);

    boolean checkTenTrungSua(String id,String ten);

}
