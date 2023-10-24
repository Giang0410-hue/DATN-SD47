package com.example.bedatnsd47.service;

import com.example.bedatnsd47.entity.SanPham;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SanPhamSerivce {

    List<SanPham> getAll();

    SanPham add(SanPham sanPham);

    SanPham update(SanPham sanPham);

    void remove(Long id);

    SanPham getById(Long id);

    Page<SanPham> getPage(Integer pageNo);

    Integer checkPageNo(Integer pageNo);

    Integer genMaTuDong();

    boolean checkTenTrung(String ten);

    boolean checkTenTrungSua(String ma,String ten);


}
