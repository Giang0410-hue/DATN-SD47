package com.example.bedatnsd47.service.impl;

import com.example.bedatnsd47.entity.ThuongHieu;
import com.example.bedatnsd47.repository.ThuongHieuRepository;
import com.example.bedatnsd47.service.ThuongHieuService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ThuongHieuServiceImpl implements ThuongHieuService {

    @Autowired
    private ThuongHieuRepository thuongHieuRepository;

    @Override
    public List<ThuongHieu> findAll() {
        return null;
    }

    @Override
    public Optional<ThuongHieu> findById(Long id) {
        return thuongHieuRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    @Transactional
    public void saveOrUpdate(ThuongHieu thuongHieu, String ten) {
        try {
            thuongHieu.setTen(ten);
            thuongHieu.setNgayTao(new Date());
            thuongHieu.setNgaySua(new Date());
            thuongHieu.setTrangThai(1);
            thuongHieuRepository.save(thuongHieu);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    @Override
    public ThuongHieu findByTen(String ten) {

        return thuongHieuRepository.findByTen(ten);
    }

    @Override
    public Page<ThuongHieu> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "ngaySua"));
        return thuongHieuRepository.findAll(pageable);

    }

    @Override
    public Page<ThuongHieu> findByTenContaining(String keyword, Integer trang_thai, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "ngaySua"));
        return thuongHieuRepository.findByTenContainingAndTrangThai(keyword, trang_thai, pageable);
    }


}
