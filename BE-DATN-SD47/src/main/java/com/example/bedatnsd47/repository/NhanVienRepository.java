package com.example.bedatnsd47.repository;

<<<<<<< HEAD
import com.example.bedatnsd47.entity.SanPham;
import com.example.bedatnsd47.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
=======
import com.example.bedatnsd47.entity.TaiKhoan;
>>>>>>> thienddph27448
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
<<<<<<< HEAD

@Repository
public interface NhanVienRepository extends JpaRepository<TaiKhoan, Long> {

=======
@Repository
public interface NhanVienRepository {
    @Query(value = "SELECT * FROM tai_khoan WHERE vai_tro_id = 2 ORDER BY ngay_sua DESC",nativeQuery = true)
    List<TaiKhoan> fillAllKhachHang();
>>>>>>> thienddph27448
    @Query(value = "select * from tai_khoan where trang_thai = 0 and vai_tro_id = 2",nativeQuery = true)
    List<TaiKhoan> fillAllDangHoatDong();

    @Query(value = "select * from tai_khoan where trang_thai = 1 and vai_tro_id = 2",nativeQuery = true)
    List<TaiKhoan> fillAllNgungHoatDong();
<<<<<<< HEAD

=======
>>>>>>> thienddph27448
}
