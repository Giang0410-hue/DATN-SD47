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

    @Query(value = "WITH RankedHoaDonChiTiet AS (\n" +
            "  SELECT\n" +
            "    h.*,\n" +
            "    ROW_NUMBER() OVER (PARTITION BY h.chi_tiet_san_pham_id ORDER BY h.so_luong DESC) AS RowNum\n" +
            "  FROM\n" +
            "    hoa_don_chi_tiet h\n" +
            "    INNER JOIN chi_tiet_san_pham c ON h.chi_tiet_san_pham_id = c.id\n" +
            "  WHERE\n" +
            "    h.trang_thai = 0\n" +
            "    AND h.hoa_don_id IN (SELECT hd.id FROM hoa_don hd WHERE hd.trang_thai = 3)\n" +
            "    AND c.trang_thai = 0\n" +
            ")\n" +
            ", FilteredResults AS (\n" +
            "  SELECT\n" +
            "    id,\n" +
            "\tso_luong,\n" +
            "\tdon_gia,\n" +
            "\tghi_chu,\n" +
            "\tngay_tao,\n" +
            "\tngay_sua,\n" +
            "\tnguoi_tao,\n" +
            "\tnguoi_sua,\n" +
            "    hoa_don_id,\n" +
            "    chi_tiet_san_pham_id,\n" +
            "    trang_thai\n" +
            "  FROM RankedHoaDonChiTiet\n" +
            "  WHERE RowNum = 1\n" +
            ")\n" +
            "SELECT TOP 5 *\n" +
            "FROM FilteredResults\n" +
            "ORDER BY so_luong DESC",nativeQuery = true)
    List<HoaDonChiTiet> fillAllIdHoaDonTrangThaiHoanThanh(@Param("listIdHoaDon")List<Long> listIdHoaDon);

    @Query("SELECT SUM(hd.soLuong) FROM HoaDonChiTiet hd WHERE MONTH(hd.ngayTao) = MONTH(:ngayTao)")
    Integer sumSanPhamHoaDonThang(@Param("ngayTao") Date ngayTao);

    @Query("select SUM(hd.soLuong) from HoaDonChiTiet hd where CAST(hd.ngayTao AS DATE) = :ngayTao")
    Integer sumSanPhamHoaDonNgay(@Param("ngayTao") Date ngayTao);

    @Query("select SUM(hd.soLuong) from HoaDonChiTiet hd where CAST(hd.ngayTao AS DATE) BETWEEN :startDate AND :endDate")
    Integer sumSanPhamHoaDonBetween(@Param("startDate") Date startDate,
                                    @Param("endDate") Date endDate);

    @Query("SELECT CAST(hd.ngayTao AS DATE) AS ngay, SUM(hd.soLuong), COUNT(DISTINCT hd.hoaDon) " +
            "FROM HoaDonChiTiet hd " +
            "WHERE CAST(hd.ngayTao AS DATE) BETWEEN :startDateChart AND :endDateChart " +
            "GROUP BY CAST(hd.ngayTao AS DATE)")
    List<Object[]> thongKeSanPhamTheoNgay(
            @Param("startDateChart") Date startDateChart,
            @Param("endDateChart") Date endDateChart
    );

    @Query("SELECT h.chiTietSanPham.sanPham.ten, SUM(h.soLuong),SUM(h.donGia)" +
            "FROM HoaDonChiTiet h " +
            "WHERE MONTH(h.ngayTao) = MONTH(:ngayTao)" +
            "GROUP BY h.chiTietSanPham.sanPham.ten " +
            "ORDER BY SUM(h.soLuong) DESC ")
    List<Object[]> findByTongSoLuongThang(@Param("ngayTao") Date ngayTao);


    @Query("SELECT h.chiTietSanPham.sanPham.ten, SUM(h.soLuong),SUM(h.donGia)" +
            "FROM HoaDonChiTiet h " +
            "WHERE CAST(h.ngayTao AS DATE) = :ngayTao " +
            "GROUP BY h.chiTietSanPham.sanPham.ten " +
            "ORDER BY SUM(h.soLuong) DESC ")
    List<Object[]> findByTongSoLuongNgay(@Param("ngayTao") Date ngayTao);


    @Query("SELECT h.chiTietSanPham.sanPham.ten, SUM(h.soLuong),SUM(h.donGia)" +
            "FROM HoaDonChiTiet h " +
            "WHERE CAST(h.ngayTao AS DATE) BETWEEN :startDate AND :endDate " +
            "GROUP BY h.chiTietSanPham.sanPham.ten " +
            "ORDER BY SUM(h.soLuong) DESC ")
    List<Object[]> findByTongSoLuongBetween(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);
}
