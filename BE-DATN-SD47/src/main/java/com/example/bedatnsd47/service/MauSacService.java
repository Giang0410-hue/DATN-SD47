package com.example.bedatnsd47.service;

import com.example.bedatnsd47.entity.MauSac;
import com.example.bedatnsd47.entity.ThuongHieu;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MauSacService {

    List<MauSac> findAll();
    Optional<MauSac> findById(Long id);
    void deleteById(Long id);
    void saveOrUpdate(MauSac mauSac,String ten);

    void update(MauSac thuongHieu, Long id, Integer trangThai, String ten, Date ngayTao);
    MauSac findByTen(String ten);

    public Page<MauSac> findAll(int page, int size);
    Page<MauSac> findByTenContaining(String keyword,Integer trang_thai,int page, int size);


    boolean checkTenTrung(String ten);

    boolean checkTenTrungSua(String ma, String ten);

    MauSac update(MauSac mauSac);

    MauSac getById(Long id);

    Integer checkPageNo(Integer pageNo);

    Page<MauSac> getPage(Integer pageNo);

    public Integer genMaTuDong();
}
