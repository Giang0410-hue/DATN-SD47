package com.example.bedatnsd47.repository;

import com.example.bedatnsd47.entity.HoaDonChiTiet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Long> {
    @Query("Select hdct from HoaDonChiTiet hdct where hdct.id=:idHoaDon")
    List<HoaDonChiTiet> findByIdHoaDon(@Param("idHoaDon") Long idHoaDon);
}
