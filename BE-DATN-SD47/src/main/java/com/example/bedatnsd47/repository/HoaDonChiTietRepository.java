package com.example.bedatnsd47.repository;

import com.example.bedatnsd47.entity.HoaDon;
import com.example.bedatnsd47.entity.HoaDonChiTiet;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, Long> {
    @Query("Select hdct from HoaDonChiTiet hdct where hdct.id=:idHoaDon")
    List<HoaDonChiTiet> findByIdHoaDon(@Param("idHoaDon") Long idHoaDon);

    @Query(value = "select top 5 * from hoa_don_chi_tiet where hoa_don_id in (:listIdHoaDon) order by so_luong desc", nativeQuery = true)
    List<HoaDonChiTiet> fillAllIdHoaDonTrangThaiHoanThanh(@Param("listIdHoaDon") List<Long> listIdHoaDon);

    @Query("SELECT SUM(hd.soLuong) FROM HoaDonChiTiet hd WHERE hd.trangThai=0 and hd.hoaDon.trangThai =3 and MONTH(hd.hoaDon.ngayTao) = MONTH(:ngayTao)")
    Integer sumSanPhamHoaDonThang(@Param("ngayTao") Date ngayTao);

    @Query("select SUM(hd.soLuong) from HoaDonChiTiet hd where hd.trangThai=0 and hd.hoaDon.trangThai =3 and CAST(hd.hoaDon.ngayTao AS DATE) = :ngayTao")
    Integer sumSanPhamHoaDonNgay(@Param("ngayTao") Date ngayTao);

    @Query("select SUM(hd.soLuong) from HoaDonChiTiet hd where hd.trangThai=0 and hd.hoaDon.trangThai = 3 and CAST(hd.hoaDon.ngayTao AS DATE) BETWEEN :startDate AND :endDate")
    Integer sumSanPhamHoaDonBetween(@Param("startDate") Date startDate,
                                    @Param("endDate") Date endDate);

    @Query("SELECT CAST(hd.hoaDon.ngayTao AS DATE) AS ngay, SUM(hd.soLuong), COUNT(DISTINCT hd.hoaDon) " +
            "FROM HoaDonChiTiet hd " +
            "WHERE hd.trangThai = 0 and hd.hoaDon.trangThai =3 and CAST(hd.hoaDon.ngayTao AS DATE) BETWEEN :startDateChart AND :endDateChart " +
            "GROUP BY CAST(hd.hoaDon.ngayTao AS DATE)")
    List<Object[]> thongKeSanPhamTheoNgay(
            @Param("startDateChart") Date startDateChart,
            @Param("endDateChart") Date endDateChart
    );


    @Query("SELECT h.chiTietSanPham.sanPham.ten, SUM(h.soLuong),SUM(h.donGia)" +
            "FROM HoaDonChiTiet h " +
            "WHERE MONTH(h.hoaDon.ngayTao) = MONTH(:ngayTao) and h.trangThai = 0 and h.hoaDon.trangThai = 3" +
            "GROUP BY h.chiTietSanPham.sanPham.ten " +
            "ORDER BY SUM(h.soLuong) DESC ")
    List<Object[]> findByTongSoLuongThang(@Param("ngayTao") Date ngayTao);


    @Query("SELECT h.chiTietSanPham.sanPham.ten, SUM(h.soLuong),SUM(h.donGia)" +
            "FROM HoaDonChiTiet h " +
            "WHERE CAST(h.hoaDon.ngayTao AS DATE) = :ngayTao and h.trangThai = 0 and h.hoaDon.trangThai = 3 " +
            "GROUP BY h.chiTietSanPham.sanPham.ten " +
            "ORDER BY SUM(h.soLuong) DESC ")
    List<Object[]> findByTongSoLuongNgay(@Param("ngayTao") Date ngayTao);


    @Query("SELECT h.chiTietSanPham.sanPham.ten, SUM(h.soLuong),SUM(h.donGia)" +
            "FROM HoaDonChiTiet h " +
            "WHERE h.trangThai = 0 and h.hoaDon.trangThai = 3 and CAST(h.hoaDon.ngayTao AS DATE) BETWEEN :startDate AND :endDate " +
            "GROUP BY h.chiTietSanPham.sanPham.ten " +
            "ORDER BY SUM(h.soLuong) DESC ")
    List<Object[]> findByTongSoLuongBetween(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);

    @Query("select SUM(hd.soLuong) from HoaDonChiTiet hd where hd.trangThai=0 and hd.hoaDon.trangThai =3")
    Integer sumSanPhamHoaDonAll();

    @Query("SELECT h.chiTietSanPham.sanPham.ten, SUM(h.soLuong),SUM(h.donGia)" +
            "FROM HoaDonChiTiet h " +
            "WHERE h.trangThai = 0 and h.hoaDon.trangThai = 3" +
            "GROUP BY h.chiTietSanPham.sanPham.ten " +
            "ORDER BY SUM(h.soLuong) DESC ")
    List<Object[]> findByTongSoLuongAll();
}
