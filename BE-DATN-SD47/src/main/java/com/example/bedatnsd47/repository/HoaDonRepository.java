package com.example.bedatnsd47.repository;

import com.example.bedatnsd47.entity.HoaDon;

import java.time.LocalDate;
import java.util.Date;
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

<<<<<<< HEAD
    @Query(value = "select * from hoa_don where tai_khoan_id = :idTaiKhoan order by ngay_sua desc",nativeQuery = true)
    List<HoaDon> findAllHoaDonByTaiKhoanOrderByNgaySua(@Param("idTaiKhoan")Long idTaiKhoan);

    @Query(value = "select * from hoa_don where tai_khoan_id = :idTaiKhoan and trang_thai = :trangThai order by ngay_sua desc",nativeQuery = true)
    List<HoaDon> findAllHoaDonByTaiKhoanAndTrangThaiOrderByNgaySua(@Param("idTaiKhoan")Long idTaiKhoan,@Param("trangThai")Integer trangThai);

    @Query(value = "select hoa_don.id from hoa_don where trang_thai = 3",nativeQuery = true)
    List<Long> fillAllIdHoaDonTrangThaiHoanThanh();
=======
    @Query("select COUNT(hd) from HoaDon hd where CAST(hd.ngayTao AS DATE) = :ngayTao")
    Integer countHoaDonNgay(@Param("ngayTao") Date ngayTao);

    @Query("select SUM(hd.tongTien) from HoaDon hd where CAST(hd.ngayTao AS DATE) = :ngayTao")
    Long sumGiaTriHoaDonNgay(@Param("ngayTao") Date ngayTao);

    @Query("SELECT COUNT(hd) FROM HoaDon hd WHERE MONTH(hd.ngayTao) = MONTH(:ngayTao)")
    Integer countHoaDonThang(@Param("ngayTao") Date ngayTao);

    @Query("SELECT SUM(hd.tongTien) FROM HoaDon hd WHERE MONTH(hd.ngayTao) = MONTH(:ngayTao)")
    Long sumGiaTriHoaDonThang(@Param("ngayTao") Date ngayTao);

    @Query("select COUNT(hd) from HoaDon hd where hd.trangThai = :trangThai")
    Integer countHoaDon(@Param("trangThai") Integer trangThai);

    @Query("SELECT COUNT(hd) FROM HoaDon hd WHERE CAST(hd.ngayTao AS DATE) BETWEEN :startDate AND :endDate")
    Integer countHoaDonTrongKhoangThoiGian(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );

    @Query("SELECT CAST(hd.ngayTao AS DATE) AS ngay, COUNT(hd) AS soLuongHoaDon " +
            "FROM HoaDon hd " +
            "WHERE CAST(hd.ngayTao AS DATE) BETWEEN :startDate AND :endDate " +
            "GROUP BY CAST(hd.ngayTao AS DATE)")
    List<Object[]> thongKeHoaDonTheoNgay(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );

    @Query("SELECT COUNT(hd) FROM HoaDon hd WHERE CAST(hd.ngayTao AS DATE) BETWEEN :startDate AND :endDate ")
    Integer countHoaDonBetween(@Param("startDate") Date startDate,
                               @Param("endDate") Date endDate);

    @Query("SELECT SUM(hd.tongTien) FROM HoaDon hd WHERE CAST(hd.ngayTao AS DATE) BETWEEN :startDate AND :endDate ")
    Long sumGiaTriHoaDonBetween(@Param("startDate") Date startDate,
                                @Param("endDate") Date endDate);
>>>>>>> giangmt

}
