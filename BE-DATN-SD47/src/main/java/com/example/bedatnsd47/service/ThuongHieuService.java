package com.example.bedatnsd47.service;

import com.example.bedatnsd47.entity.ThuongHieu;

import java.util.List;
import java.util.Optional;

public interface ThuongHieuService {

    List<ThuongHieu> findAll();

    List<ThuongHieu> getAllDangHoatDong();

    List<ThuongHieu> getAllNgungHoatDong();

    void deleteById(Long id);

    ThuongHieu save(ThuongHieu thuongHieu);

    boolean checkTenTrung(String ten);

    boolean checkTenTrungSua(Long id, String ten);

    ThuongHieu update(ThuongHieu thuongHieu);

    ThuongHieu getById(Long id);

}
