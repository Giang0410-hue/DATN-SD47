package com.example.bedatnsd47.repository;

import com.example.bedatnsd47.entity.HoaDon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {
    @Query("Select hd from HoaDon hd where hd.trangThai=:tt")
    List<HoaDon> findByTrangThai(@Param("tt") Integer trangThai);

    @Query("Select hd from HoaDon hd where hd.maHoaDon=:ma")
    HoaDon findByMa(@Param("ma") String ma);

    @Query("select COUNT(hd) from HoaDon hd where hd.trangThai = -1")
    Integer countHoaDonTreo();

    @Query("Select hd from HoaDon hd where hd.trangThai=:tt order by hd.ngaySua asc")
    List<HoaDon> find5ByTrangThai(@Param("tt") Integer trangThai);
}
