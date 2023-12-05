package com.example.bedatnsd47.repository;

import com.example.bedatnsd47.entity.LichSuHoaDon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LichSuHoaDonRepository extends JpaRepository<LichSuHoaDon, Long> {
    @Query("select lshd from LichSuHoaDon lshd where lshd.hoaDon.id =:idhd order by lshd.ngaySua desc")
    List<LichSuHoaDon> findByIdHd(@Param("idhd") Long idhd);

    @Query(value = "select * from lich_su_hoa_don where hoa_don_id = :idhd and trang_thai in(0,1,2,3,4,5,6) order by ngay_sua asc",nativeQuery = true)
    List<LichSuHoaDon> findByIdhdNgaySuaAsc(@Param("idhd") Long idhd);

}
