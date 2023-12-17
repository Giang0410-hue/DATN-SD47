package com.example.bedatnsd47.service;

import com.example.bedatnsd47.entity.SanPham;
import com.example.bedatnsd47.entity.TaiKhoan;
import org.springframework.data.domain.Page;

import java.util.List;

public interface KhachHangService {
    List<TaiKhoan> getAll();

    List<TaiKhoan> getAllDangHoatDong();

    List<TaiKhoan> getAllNgungHoatDong();

    TaiKhoan add(TaiKhoan taiKhoan);

    TaiKhoan update(TaiKhoan taiKhoan);

    void remove(Long id);

    TaiKhoan getById(Long id);

    boolean checkTenTrung(String ten);

    boolean checkTenTaiKhoanTrung(String ten);

    boolean checkEmail(String email);

    boolean checkTenTkTrungSua(Long id, String ten);

    boolean checkEmailSua(Long id, String email);

    TaiKhoan findKhachLe();

    void addKhachLe();

    void sendEmail(TaiKhoan taiKhoan, String path, String random);

    void guiLieuHe(String hoTen,String email,String chuDe,String tinNhan);

}
