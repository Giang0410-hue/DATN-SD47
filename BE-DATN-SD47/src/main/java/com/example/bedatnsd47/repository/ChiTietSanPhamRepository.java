package com.example.bedatnsd47.repository;

import com.example.bedatnsd47.entity.ChiTietSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham,Long> {

    @Query(value = "select * from chi_tiet_san_pham where trang_thai = 1",nativeQuery = true)
    List<ChiTietSanPham> fillAllDangHoatDong();

    @Query(value = "select * from chi_tiet_san_pham where trang_thai = 0",nativeQuery = true)
    List<ChiTietSanPham> fillAllNgungHoatDong();

}
