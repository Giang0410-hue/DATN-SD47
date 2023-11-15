package com.example.bedatnsd47.repository;

import com.example.bedatnsd47.entity.ChiTietSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, Long> {

        @Query(value = "select * from chi_tiet_san_pham where trang_thai = 1", nativeQuery = true)
        List<ChiTietSanPham> fillAllDangHoatDong();

        @Query(value = "select * from chi_tiet_san_pham where trang_thai = 0", nativeQuery = true)
        List<ChiTietSanPham> fillAllNgungHoatDong();

        @Query(value = "WITH CTE AS (\n" +
                        "    SELECT\n" +
                        "        id,\n" +
                        "        san_pham_id,\n" +
                        "        ROW_NUMBER() OVER (PARTITION BY san_pham_id ORDER BY id) AS rn\n" +
                        "    FROM chi_tiet_san_pham\n" +
                        ")\n" +
                        "\n" +
                        "SELECT \n" +
                        "    cts.id,\n" +
                        "    cts.so_luong,\n" +
                        "    cts.gia_hien_hanh,\n" +
                        "    cts.trang_thai,\n" +
                        "    cts.san_pham_id,\n" +
                        "    cts.kich_co_id,\n" +
                        "    cts.mau_sac_id,\n" +
                        "    cts.loai_de_id\n" +
                        "FROM chi_tiet_san_pham cts\n" +
                        "JOIN CTE ON cts.id = CTE.id\n" +
                        "WHERE CTE.rn = 1 AND cts.trang_thai = 0", nativeQuery = true)
        List<ChiTietSanPham> fillAllCtspOneSanPham();

        @Query(value = "SELECT cts.*\n" +
                        "FROM (\n" +
                        "    SELECT *,\n" +
                        "           ROW_NUMBER() OVER (PARTITION BY san_pham_id, mau_sac_id ORDER BY mau_sac_id) AS RowAsc\n"
                        +
                        "    FROM chi_tiet_san_pham\n" +
                        "    WHERE san_pham_id = 3\n" +
                        ") cts\n" +
                        "WHERE cts.RowAsc = 1  AND cts.trang_thai = 0;", nativeQuery = true)
        List<ChiTietSanPham> fillAllChiTietSpShop(@Param("id") Long id);

        @Query(value = "select * from chi_tiet_san_pham where san_pham_id = :idSanPham and mau_sac_id = :idMauSac and trang_thai = 0", nativeQuery = true)
        List<ChiTietSanPham> fillAllChiTietSpMauSac(@Param("idSanPham") Long idSanPham,
                        @Param("idMauSac") Long idMauSac);

        @Query(value = "select * from chi_tiet_san_pham where trang_thai = 1 and so_luong>0", nativeQuery = true)
        List<ChiTietSanPham> fillAllDangHoatDongLonHon0();
}
