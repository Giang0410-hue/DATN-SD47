package com.example.bedatnsd47.service;

import com.example.bedatnsd47.entity.TaiKhoan;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaiKhoanService {
    List<TaiKhoan> getAll();

    TaiKhoan add(TaiKhoan taiKhoan);

    TaiKhoan update(TaiKhoan taiKhoan);

    void remove(Long id);

    TaiKhoan getById(Long id);

    Page<TaiKhoan> getPage(Integer pageNo);

    Integer checkPageNo(Integer pageNo);

    Integer genMaTuDong();

    boolean checkTenTrung(String ten);

    boolean checkTenTaiKhoanTrung(String ten);

    boolean checkEmail(String email);

    boolean checkTenTrungSua(String ma,String ten);

}
