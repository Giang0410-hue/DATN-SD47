package com.example.bedatnsd47.repository;

import com.example.bedatnsd47.entity.SanPham;
import com.example.bedatnsd47.entity.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThuongHieuRepository extends JpaRepository<ThuongHieu,Long> {

    @Query(value = "select * from thuong_hieu where trang_thai = 0",nativeQuery = true)
    List<ThuongHieu> fillAllDangHoatDong();

    @Query(value = "select * from thuong_hieu where trang_thai = 1",nativeQuery = true)
    List<ThuongHieu> fillAllNgungHoatDong();

}
