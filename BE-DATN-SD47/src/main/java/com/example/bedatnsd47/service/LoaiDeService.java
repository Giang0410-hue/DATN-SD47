package com.example.bedatnsd47.service;

import com.example.bedatnsd47.entity.LoaiDe;
import com.example.bedatnsd47.entity.ThuongHieu;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface LoaiDeService {
    List<LoaiDe> findAll();

    Optional<LoaiDe> findById(Long id);

    void deleteById(Long id);

    void saveOrUpdate(LoaiDe thuongHieu, String ten);

    void update(LoaiDe thuongHieu, Long id, Integer trangThai, String ten, Date ngayTao);

    LoaiDe findByTen(String ten);

    public Page<LoaiDe> findAll(int page, int size);

    Page<LoaiDe> findByTenContaining(String keyword, Integer trang_thai, int page, int size);
}
