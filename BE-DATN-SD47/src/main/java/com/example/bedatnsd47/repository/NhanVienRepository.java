package com.example.bedatnsd47.repository;

import com.example.bedatnsd47.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanVienRepository extends JpaRepository<TaiKhoan, Long> {
    
    @Query(value = "select * from tai_khoan where trang_thai = 0 and vai_tro_id = 2",nativeQuery = true)
    List<TaiKhoan> fillAllDangHoatDong();

    @Query(value = "select * from tai_khoan where trang_thai = 1 and vai_tro_id = 2",nativeQuery = true)
    List<TaiKhoan> fillAllNgungHoatDong();

}
