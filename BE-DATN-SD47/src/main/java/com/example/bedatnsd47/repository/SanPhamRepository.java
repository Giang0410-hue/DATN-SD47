package com.example.bedatnsd47.repository;

import com.example.bedatnsd47.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, Long> {

    @Query(value = "SELECT MAX(CONVERT(INT, SUBSTRING(Ma,3,10))) from san_pham",nativeQuery = true)
    Integer index();

    @Query(value = "select * from san_pham where trang_thai = 0 order by ngay_sua desc",nativeQuery = true)
    List<SanPham> fillAllDangHoatDong();

    @Query(value = "select * from san_pham where trang_thai = 1 order by ngay_sua desc",nativeQuery = true)
    List<SanPham> fillAllNgungHoatDong();

    @Query(value = "SELECT * FROM san_pham WHERE LOWER(ten) = LOWER(:name)",nativeQuery = true)
    SanPham findSanPhamByTen(@Param("name")String name);

}
