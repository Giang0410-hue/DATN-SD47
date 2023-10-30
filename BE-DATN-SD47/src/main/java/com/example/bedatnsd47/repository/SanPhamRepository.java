package com.example.bedatnsd47.repository;

import com.example.bedatnsd47.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {

    @Query(value = "SELECT MAX(CONVERT(INT, SUBSTRING(Ma,3,10))) from san_pham",nativeQuery = true)
    Integer index();

    @Query(value = "select * from san_pham where trang_thai = 0",nativeQuery = true)
    List<SanPham> fillAllDangHoatDong();

    @Query(value = "select * from san_pham where trang_thai = 1",nativeQuery = true)
    List<SanPham> fillAllNgungHoatDong();

}
