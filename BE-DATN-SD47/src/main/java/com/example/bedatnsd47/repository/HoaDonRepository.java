package com.example.bedatnsd47.repository;

import com.example.bedatnsd47.entity.HoaDon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Long> {
    @Query("Select hd from HoaDon hd where hd.trangThai=:tt order by hd.ngayTao asc")
    List<HoaDon> findByTrangThai(@Param("tt") Integer trangThai);

    @Query("Select hd from HoaDon hd where hd.trangThai !=-1 order by hd.ngaySua desc")
    List<HoaDon> findAllOrderByNgaySua();

    @Query("Select hd from HoaDon hd where hd.maHoaDon=:ma")
    HoaDon findByMa(@Param("ma") String ma);

    @Query("select COUNT(hd) from HoaDon hd where hd.trangThai = -1")
    Integer countHoaDonTreo();

    @Query("Select hd from HoaDon hd where hd.trangThai=:tt order by hd.ngaySua desc")
    List<HoaDon> find5ByTrangThai(@Param("tt") Integer trangThai);

    @Query(value = "select * from hoa_don where tai_khoan_id = :idTaiKhoan order by id desc",nativeQuery = true)
    List<HoaDon> findAllHoaDonByTaiKhoanOrderByNgaySua(@Param("idTaiKhoan")Long idTaiKhoan);

    @Query(value = "select * from hoa_don where tai_khoan_id = :idTaiKhoan and trang_thai = :trangThai order by ngay_sua desc",nativeQuery = true)
    List<HoaDon> findAllHoaDonByTaiKhoanAndTrangThaiOrderByNgaySua(@Param("idTaiKhoan")Long idTaiKhoan,@Param("trangThai")Integer trangThai);

}
