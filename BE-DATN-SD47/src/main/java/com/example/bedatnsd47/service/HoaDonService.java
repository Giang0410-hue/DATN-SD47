package com.example.bedatnsd47.service;

import com.example.bedatnsd47.entity.HoaDon;

import java.util.List;

import org.springframework.data.repository.query.Param;


public interface HoaDonService {
    List<HoaDon> findAll();

    HoaDon findById(Long id);

    void deleteById(Long id);

    void saveOrUpdate(HoaDon hoaDon);

    List<HoaDon> findByTrangThai(Integer trangThai);

    Integer countHoaDonTreo();

    List<HoaDon> find5ByTrangThai(Integer trangThai);

    HoaDon findByMa(String ma);

}
