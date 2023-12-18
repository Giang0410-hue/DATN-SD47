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

    boolean checkTenTkTrungSua(Long id,String ten);

    boolean checkTenTaiKhoanTrung(String ten);

    boolean checkEmailSua(Long id,String email);

    boolean checkEmail(String email);

    public void sendEmail(TaiKhoan taiKhoan, String path,String random);

}
