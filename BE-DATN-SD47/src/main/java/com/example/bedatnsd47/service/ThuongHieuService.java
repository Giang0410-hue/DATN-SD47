package com.example.bedatnsd47.service;

import com.example.bedatnsd47.entity.MauSac;
import com.example.bedatnsd47.entity.SanPham;
import com.example.bedatnsd47.entity.ThuongHieu;
import org.springframework.data.domain.Page;



import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ThuongHieuService {
    List<ThuongHieu> findAll();

    Optional<ThuongHieu> findById(Long id);

    void deleteById(Long id);

    void saveOrUpdate(ThuongHieu thuongHieu, String ten);

    void update(ThuongHieu thuongHieu, Long id, Integer trangThai, String ten, Date ngayTao);

    ThuongHieu findByTen(String ten);

    public Page<ThuongHieu> findAll(int page, int size);

    Page<ThuongHieu> findByTenContaining(String keyword, Integer trang_thai, int page, int size);

    //tuan
    boolean checkTenTrung(String ten);

    boolean checkTenTrungSua(Long id, String ten);

    ThuongHieu update(ThuongHieu thuongHieu);

    ThuongHieu getById(Long id);

    Integer checkPageNo(Integer pageNo);

    Page<ThuongHieu> getPage(Integer pageNo);
}
