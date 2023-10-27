package com.example.bedatnsd47.repository;

import com.example.bedatnsd47.entity.MauSac;
import com.example.bedatnsd47.entity.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MauSacRepository extends JpaRepository<MauSac,Long> {
    @Query("SELECT t FROM MauSac t WHERE LOWER(t.ten) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "AND (:trang_thai IS NULL OR t.trangThai = :trang_thai)")
    Page<ThuongHieu> findByTenContainingAndTrangThai(@Param("keyword") String keyword, @Param("trang_thai") Integer trang_thai, Pageable pageable);

    @Query("SELECT t FROM MauSac t WHERE t.ten = :ten")
    MauSac findByTen(@Param("ten") String ten);

    @Query(value = "SELECT MAX(CONVERT(varchar, SUBSTRING(ma_mau,3,10))) from mau_sac",nativeQuery = true)
    Integer index();
}
