package com.example.bedatnsd47.service;



import com.example.bedatnsd47.entity.HoaDonChiTiet;

import java.util.List;

public interface HoaDonChiTietService {
    List<HoaDonChiTiet> findAll();
    HoaDonChiTiet findById(Long id);
    void deleteById(Long id);
    void saveOrUpdate(HoaDonChiTiet hoaDonChiTiet);
}
