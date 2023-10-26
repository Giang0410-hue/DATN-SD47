package com.example.bedatnsd47.service;

import com.example.bedatnsd47.entity.TaiKhoan;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NhanVienService {

    List<TaiKhoan> getAll();

    TaiKhoan add(TaiKhoan sanPham);

    TaiKhoan update(TaiKhoan sanPham);

    void remove(Long id);

    TaiKhoan getById(Long id);

    Page<TaiKhoan> getPage(Integer pageNo);

    Integer checkPageNo(Integer pageNo);

    boolean checkTenTrung(String ten);

    boolean checkTenTrungSua(String ma,String ten);

}
