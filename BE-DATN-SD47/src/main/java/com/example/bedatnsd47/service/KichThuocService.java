package com.example.bedatnsd47.service;

import com.example.bedatnsd47.entity.KichCo;
import com.example.bedatnsd47.entity.ThuongHieu;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface KichThuocService {
    List<KichCo> findAll();

    Optional<KichCo> findById(Long id);

    void deleteById(Long id);

    void saveOrUpdate(KichCo thuongHieu, Float ten);

    void update(KichCo thuongHieu, Long id, Integer trangThai, Float ten, Date ngayTao);

    KichCo findByTen(Float ten);

    public Page<KichCo> findAll(int page, int size);

    Page<KichCo> findByTenContaining(String keyword, Integer trang_thai, int page, int size);
}
