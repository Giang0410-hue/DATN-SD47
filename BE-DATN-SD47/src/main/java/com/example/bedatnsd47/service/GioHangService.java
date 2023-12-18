package com.example.bedatnsd47.service;

import com.example.bedatnsd47.entity.GioHang;

import java.util.List;

public interface GioHangService {

    List<GioHang> findAll();

    GioHang findById(Long id);

    void deleteById(Long id);

    GioHang save(GioHang gioHang);

    GioHang update(GioHang gioHang);

    Integer genMaTuDong();

    List<GioHang> findByTrangThai(Integer trangThai);

}
