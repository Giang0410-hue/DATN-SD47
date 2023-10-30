package com.example.bedatnsd47.service.impl;

import com.example.bedatnsd47.entity.ChiTietSanPham;
import com.example.bedatnsd47.repository.ChiTietSanPhamRepository;
import com.example.bedatnsd47.service.ChiTietSanPhamSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChiTietSanPhamSerivceImpl implements ChiTietSanPhamSerivce {

    @Autowired
    private ChiTietSanPhamRepository repository;

    @Override
    public List<ChiTietSanPham> getAll() {

        Sort sort = Sort.by(Sort.Direction.DESC, "ngaySua");
        return repository.findAll(sort);

    }

    @Override
    public List<ChiTietSanPham> getAllDangHoatDong() {

        return repository.fillAllDangHoatDong();

    }

    @Override
    public List<ChiTietSanPham> getAllNgungHoatDong() {

        return repository.fillAllNgungHoatDong();

    }

    @Override
    public ChiTietSanPham add(ChiTietSanPham sanPham) {

        return repository.save(sanPham);

    }

    @Override
    public ChiTietSanPham update(ChiTietSanPham sanPham) {

        return repository.save(sanPham);

    }

    @Override
    public void remove(Long id) {

        repository.deleteById(id);

    }

    @Override
    public ChiTietSanPham getById(Long id) {

        return repository.findById(id).get();

    }


    @Override
    public boolean checkTenTrung(String ten) {

        return false;

    }

    @Override
    public boolean checkTenTrungSua(String ma, String ten) {

        return false;

    }

}
