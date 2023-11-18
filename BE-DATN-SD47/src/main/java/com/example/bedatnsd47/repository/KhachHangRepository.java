package com.example.bedatnsd47.repository;

import com.example.bedatnsd47.entity.SanPham;
import com.example.bedatnsd47.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KhachHangRepository extends JpaRepository<TaiKhoan,Long> {

    @Query(value = "SELECT * FROM tai_khoan WHERE vai_tro_id = 2 ORDER BY ngay_sua DESC",nativeQuery = true)
    List<TaiKhoan> fillAllKhachHang();
    @Query(value = "select * from tai_khoan where trang_thai = 0 and vai_tro_id = 2",nativeQuery = true)
    List<TaiKhoan> fillAllDangHoatDong();

    @Query(value = "select * from tai_khoan where trang_thai = 1 and vai_tro_id = 2",nativeQuery = true)
    List<TaiKhoan> fillAllNgungHoatDong();
    @Query(value = "select * from tai_khoan where ho_va_ten =N'Khách lẻ'",nativeQuery = true)
    TaiKhoan findKhachLe();

    Optional<TaiKhoan> findByEmail(String email);

}
