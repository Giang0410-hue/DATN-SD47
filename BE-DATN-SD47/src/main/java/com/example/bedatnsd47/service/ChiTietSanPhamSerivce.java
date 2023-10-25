package com.example.bedatnsd47.service;

import com.example.bedatnsd47.entity.ChiTietSanPham;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ChiTietSanPhamSerivce {

    List<ChiTietSanPham> getAll();

    ChiTietSanPham add(ChiTietSanPham chiTietSanPham);

    ChiTietSanPham update(ChiTietSanPham chiTietSanPham);

    void remove(Long id);

    ChiTietSanPham getById(Long id);

    Page<ChiTietSanPham> getPage(Integer pageNo);

    Integer checkPageNo(Integer pageNo);

    boolean checkTenTrung(String ten);

    boolean checkTenTrungSua(String ma,String ten);


}
