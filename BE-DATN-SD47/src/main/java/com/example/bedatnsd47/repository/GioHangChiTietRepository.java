package com.example.bedatnsd47.repository;

import com.example.bedatnsd47.entity.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet,Long> {

    @Query(value = "select * from gio_hang_chi_tiet where gio_hang_id = :idGioHang and trang_thai = 0",nativeQuery = true)
    List<GioHangChiTiet> getfindAllByIdGioHang(@Param("idGioHang")Long idGioHang);

    @Query(value = "SELECT COUNT(id) FROM gio_hang_chi_tiet where gio_hang_id = :idGioHang and trang_thai = 0",nativeQuery = true)
    Integer soLuongSpTrongGioHangCT(@Param("idGioHang")Long idGioHang);

    @Query(value = "select * from gio_hang_chi_tiet where gio_hang_id = :idGioHang and chi_tiet_san_pham_id = :idChiTetSP",nativeQuery = true)
    GioHangChiTiet getByGioHangChiTiet(@Param("idGioHang")Long idGioHang,@Param("idChiTetSP")Long idChiTetSP);

}