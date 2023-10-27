package com.example.bedatnsd47.service;

import com.example.bedatnsd47.entity.KichCo;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface KichCoService {
    List<KichCo> findAll();

    Optional<KichCo> findById(Long id);

    void deleteById(Long id);

    void saveOrUpdate(KichCo thuongHieu, Integer ten);

    public Page<KichCo> findAll(int page, int size);

    Page<KichCo> findByTenContaining(String keyword, Integer trang_thai, int page, int size);

    //tuan
    boolean checkTenTrung(Integer ten);

    boolean checkTenTrungSua(Long id, Integer ten);

    KichCo update(KichCo kichCo);

    KichCo getById(Long id);

    Integer checkPageNo(Integer pageNo);

    Page<KichCo> getPage(Integer pageNo);
}
