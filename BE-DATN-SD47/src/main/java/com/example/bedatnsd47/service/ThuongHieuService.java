package com.example.bedatnsd47.service;

import com.example.bedatnsd47.entity.ThuongHieu;
import org.springframework.data.domain.Page;



import java.util.List;
import java.util.Optional;

public interface ThuongHieuService {
    List<ThuongHieu> findAll();
    Optional<ThuongHieu> findById(Long id);
    void deleteById(Long id);
    void saveOrUpdate(ThuongHieu thuongHieu,String ten);
    ThuongHieu findByTen(String ten);

    public Page<ThuongHieu> findAll(int page, int size);
    Page<ThuongHieu> findByTenContaining(String keyword,Integer trang_thai,int page, int size);


}
