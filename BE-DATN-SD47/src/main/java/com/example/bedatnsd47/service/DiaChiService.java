package com.example.bedatnsd47.service;

import com.example.bedatnsd47.entity.ChiTietSanPham;
import com.example.bedatnsd47.entity.DiaChi;
import com.example.bedatnsd47.entity.LoaiDe;

import java.util.List;

public interface DiaChiService {
    List<DiaChi> getAll();

    List<DiaChi> getAllByTaiKhoan(Long id);

    void deleteById(Long id);

    DiaChi save(DiaChi diaChi);

    boolean checkTenTrung(String ten,Long idTaiKhoan);

    boolean checkTenTrungSua(Long idDiaChi, String ten,Long idTaiKhoan);

    DiaChi update(DiaChi diaChi);

    DiaChi getById(Long id);

    List<DiaChi> getAllTrangThai(Integer trangThai);

}
