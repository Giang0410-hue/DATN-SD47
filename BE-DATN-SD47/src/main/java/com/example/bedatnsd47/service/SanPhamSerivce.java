package com.example.bedatnsd47.service;

import com.example.bedatnsd47.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SanPhamSerivce {

    List<SanPham> getAll();

    SanPham add(SanPham sanPham);

    SanPham update(SanPham sanPham, Long id);

    void remove(Long id);

    SanPham getById(Long id);

    Page<SanPham> getPage(Integer pageNo);

}
