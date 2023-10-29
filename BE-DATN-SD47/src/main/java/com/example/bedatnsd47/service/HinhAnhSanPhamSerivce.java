package com.example.bedatnsd47.service;

import com.example.bedatnsd47.entity.HinhAnhSanPham;
import com.example.bedatnsd47.entity.SanPham;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface HinhAnhSanPhamSerivce {

    void saveImage(List<MultipartFile> files, SanPham sanPham);

    void saveWhenEditingImage(List<MultipartFile> files, Long id);

    List<HinhAnhSanPham> listHinhAnh(Long id);

    void deleteByID(Long id);

}
