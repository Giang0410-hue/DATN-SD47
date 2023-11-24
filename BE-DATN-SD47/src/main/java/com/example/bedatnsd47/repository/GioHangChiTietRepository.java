package com.example.bedatnsd47.repository;

import com.example.bedatnsd47.entity.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet,Long> {

    @Query(value = "SELECT * FROM gio_hang_chi_tiet WHERE id IN (:listId) AND gio_hang_id = :idGioHang",nativeQuery = true)
    List<GioHangChiTiet> findAllByIdGHCT(@Param("listId")List<Long> listId,@Param("idGioHang")Long idGioHang);

    @Query(value = "select * from gio_hang_chi_tiet where gio_hang_id = :idGioHang and trang_thai = 0 order by ngay_sua desc",nativeQuery = true)
    List<GioHangChiTiet> getfindAllByIdGioHang(@Param("idGioHang")Long idGioHang);

    @Query(value = "SELECT COUNT(id) FROM gio_hang_chi_tiet where gio_hang_id = :idGioHang and trang_thai = 0",nativeQuery = true)
    Integer soLuongSpTrongGioHangCT(@Param("idGioHang")Long idGioHang);

    @Query(value = "SELECT * FROM gio_hang_chi_tiet where chi_tiet_san_pham_id = :idCTSP",nativeQuery = true)
    GioHangChiTiet fillByIdCTSP(@Param("idCTSP")Long idCTSP);

    @Query(value = "select * from gio_hang_chi_tiet where gio_hang_id = :idGioHang and chi_tiet_san_pham_id = :idChiTetSP",nativeQuery = true)
    GioHangChiTiet getByGioHangChiTiet(@Param("idGioHang")Long idGioHang,@Param("idChiTetSP")Long idChiTetSP);

}
