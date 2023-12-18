package com.example.bedatnsd47.service;


import com.example.bedatnsd47.entity.LichSuHoaDon;

import java.util.List;

public interface LichSuHoaDonService {
    List<LichSuHoaDon> findAll();
    LichSuHoaDon findById(Long id);
    void deleteById(Long id);
    void saveOrUpdate(LichSuHoaDon lichSuHoaDon);
    List<LichSuHoaDon> findByIdhd(Long idhd);

    List<LichSuHoaDon> findByIdhdNgaySuaAsc(Long idhd);

}
