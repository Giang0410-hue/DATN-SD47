package com.example.bedatnsd47.service.impl;

import com.example.bedatnsd47.entity.SanPham;
import com.example.bedatnsd47.repository.SanPhamRepository;
import com.example.bedatnsd47.service.SanPhamSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamSerivceImpl implements SanPhamSerivce {

    @Autowired
    private SanPhamRepository repository;

    @Override
    public List<SanPham> getAll() {
        return repository.findAll();
    }

    @Override
    public SanPham add(SanPham sanPham) {
        return repository.save(sanPham);
    }

    @Override
    public SanPham update(SanPham sanPham, Long id) {
        return repository.save(sanPham);
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }

    @Override
    public SanPham getById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Page<SanPham> getPage(Integer pageNo) {
        return repository.findAll(PageRequest.of(pageNo, 5));
    }

}
